package com.company.akeninbaev.controller;

import com.company.akeninbaev.model.Meme;
import com.company.akeninbaev.model.MemeReview;
import com.company.akeninbaev.model.User;
import com.company.akeninbaev.model.UserRole;
import com.company.akeninbaev.services.MemeReviewService;
import com.company.akeninbaev.services.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MemeReviewController extends AuthorizedController<MemeReview, Integer> {
    private final Service<User, Integer> userService;
    private final Service<Meme, Integer> memeService;

    public MemeReviewController(Service<MemeReview, Integer> service, ObjectMapper objectMapper, Service<User, Integer> userService, Service<Meme, Integer> memeService) {
        super(service, objectMapper, MemeReview.class);
        this.userService = userService;
        this.memeService = memeService;
    }

    @Override
    public Service<User, Integer> userService() {
        return userService;
    }

    @Override
    public void postOne(Context context) {
        if (isAuthorized(context)) {
            MemeReview memeReview = extractMemeReview(context);
            getService().save(memeReview);
        } else {
            throw new ForbiddenResponse();
        }
    }


    @Override
    boolean isAuthorized(User user, Context context) {
        if (user.getRole() == UserRole.ADMIN) {
            return true;
        }
        if (context.method().equals("POST")) {
            User actor = actor(context);
            int memeId = context.pathParam("memeId", Integer.class).get();
            int userId = context.pathParam("userId", Integer.class).get();
            Meme meme = memeService.findById(memeId);
            User owner = userService.findById(userId);
            return actor.equals(owner);
        } else {
            int id = context.pathParam("id", Integer.class).get();
            MemeReview memeReview = getService().findById(id);
            return actor(context).equals(memeReview.getUser());
        }
    }

    private Meme extractMeme(Context context) {
        int memeId = context.pathParam("memeId", Integer.class).get();
        return memeService.findById(memeId);
    }

    private User extractUser(Context context) {
        int userId = context.pathParam("userId", Integer.class).get();
        return userService.findById(userId);
    }

    private MemeReview extractMemeReview(Context context) {
        int id = context.pathParam("id", Integer.class).get();
        Meme meme = extractMeme(context);
        User user = extractUser(context);
        String rating = extractMemeReview(context).getRating();
        LocalDate date = extractMemeReview(context).getDate();
        return new MemeReview(id, user, meme, rating, date);
    }
}

