package com.company.akeninbaev;

import com.company.akeninbaev.configuration.DatabaseConfiguration;
import com.company.akeninbaev.configuration.JdbcDatabaseConfiguration;
import com.company.akeninbaev.controller.*;
import com.company.akeninbaev.json.UserDeserializer;
import com.company.akeninbaev.model.*;
import com.company.akeninbaev.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.j256.ormlite.dao.DaoManager;
import io.javalin.Javalin;
import io.javalin.core.security.Role;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.UnauthorizedResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.core.security.SecurityUtil.roles;

public class Main {
    public static Role getRole(Context context, Service<User, Integer> userService) {
        if (context.basicAuthCredentialsExist()) {
            String login = context.basicAuthCredentials().getUsername();
            String password = context.basicAuthCredentials().getPassword();
            User actor = userService.findByColumnUnique("login", login);
            if (BCrypt.checkpw(password, actor.getPassword())) {
                return actor.getRole();
            } else {
                throw new UnauthorizedResponse();
            }
        } else {
            throw new UnauthorizedResponse();
        }
    }
    public static void main(String[] args) throws SQLException {
        DatabaseConfiguration databaseConfiguration = new JdbcDatabaseConfiguration("jdbc:sqlite::memory:");
        Service<User, Integer> userService = new UserService(DaoManager.createDao(databaseConfiguration.connectionSource(), User.class));
        Service<Meme, Integer> memeService = new MemeService(DaoManager.createDao(databaseConfiguration.connectionSource(), Meme.class));
        Service<MemeReview, Integer> memeReviewService = new MemeReviewService(DaoManager.createDao(databaseConfiguration.connectionSource(), MemeReview.class));
        Service<UserInteraction, Integer> userInteractionService = new UserInteractionService(DaoManager.createDao(databaseConfiguration.connectionSource(), UserInteraction.class));

        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(
                        new SimpleModule()
                                .addDeserializer(User.class, new UserDeserializer())
                );

        Controller<User, Integer> userController = new UserController(userService, objectMapper);
        Controller<Meme, Integer> memeController = new MemeController(memeService, objectMapper, userService);
        Controller<MemeReview, Integer> memeReviewController = new MemeReviewController(memeReviewService, objectMapper, userService, memeService);
        Controller<UserInteraction, Integer> userInteractionController = new UserInteractionController(userInteractionService, objectMapper, userService);

        Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.enableCorsForAllOrigins();
            javalinConfig.defaultContentType = "application/json";
            javalinConfig.prefer405over404 = true;
            javalinConfig.accessManager((handler, context, set) -> {
                Role userRole = getRole(context, userService);
                if (set.contains(userRole)) {
                    handler.handle(context);
                } else {
                    throw new ForbiddenResponse();
                }
            });
        });

        app.routes(() -> {
            path("users", () -> {
                get(userController::getAll, roles(UserRole.ADMIN));
                post(userController::postOne);
                path(":id", () -> {
                    get((ctx) -> userController.getOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON, UserRole.ADMIN));
                    patch((ctx) -> userController.patchOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON, UserRole.ADMIN));
                    delete((ctx) -> userController.deleteOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON, UserRole.ADMIN));
                });
            });

            path("memes", () -> {
                get(memeController::getAll);
                post(memeController::postOne);
                path(":id", () -> {
                    get((ctx) -> memeController.getOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON, UserRole.ADMIN));
                    patch((ctx) -> memeController.patchOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON));
                    delete((ctx) -> memeController.deleteOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON));
                });
            });

            path("memeReviews", () -> {
                get(memeReviewController::getAll);
                path(":userId/:memeId", () -> post(memeReviewController::postOne));
                path(":id", () -> {
                    get((ctx) -> memeReviewController.getOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON, UserRole.ADMIN));
                    patch((ctx) -> memeReviewController.patchOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON));
                    delete((ctx) -> memeReviewController.deleteOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON));
                });
            });

            path("userInteractions", () -> {
                get(userInteractionController::getAll);
                path(":sourceId/:targetId", () -> post(userInteractionController::postOne));
                path(":id", () -> {
                    get((ctx) -> userInteractionController.getOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON, UserRole.ADMIN));
                    patch((ctx) -> userInteractionController.patchOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON));
                    delete((ctx) -> userInteractionController.deleteOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON));
                });
            });
        });
        app.start(8080);
    }
}
