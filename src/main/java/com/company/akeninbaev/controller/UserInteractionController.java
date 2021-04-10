package com.company.akeninbaev.controller;

import com.company.akeninbaev.model.*;
import com.company.akeninbaev.services.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;

import java.time.LocalDate;

public class UserInteractionController extends AuthorizedController<UserInteraction, Integer> {
    private final Service<User, Integer> userService;

    public UserInteractionController(Service<UserInteraction, Integer> service, ObjectMapper objectMapper, Service<User, Integer> userService) {
        super(service, objectMapper, UserInteraction.class);
        this.userService = userService;
    }

    @Override
    public Service<User, Integer> userService() {
        return userService;
    }

    @Override
    public void postOne(Context context) {
        if (isAuthorized(context)) {
            UserInteraction userInteraction = extractUserInteraction(context);
            getService().save(userInteraction);
        } else {
            throw new ForbiddenResponse();
        }
    }


    @Override
    boolean isAuthorized(User user, Context context) {
        if (user.getRole() == UserRole.COMMON) {
            return true;
        }
        if (context.method().equals("POST")) {
            User actor = actor(context);
            int sourceId = context.pathParam("sourceId", Integer.class).get();
            int targetId = context.pathParam("targetId", Integer.class).get();
            User target = userService.findById(targetId);
            User source = userService.findById(sourceId);
            return actor.equals(source);
        } else {
            int id = context.pathParam("id", Integer.class).get();
            UserInteraction userInteraction = getService().findById(id);
            return actor(context).equals(userInteraction.getTarget());
        }
    }

    private User extractTarget(Context context) {
        int targetId = context.pathParam("targetId", Integer.class).get();
        return userService.findById(targetId);
    }

    private User extractSource(Context context) {
        int sourceId = context.pathParam("sourceId", Integer.class).get();
        return userService.findById(sourceId);
    }

    private UserInteraction extractUserInteraction(Context context) {
        int id = context.pathParam("id", Integer.class).get();
        User source = extractSource(context);
        User target = extractTarget(context);
        Boolean reaction = extractUserInteraction(context).getReaction();
        LocalDate date = extractUserInteraction(context).getDate();
        return new UserInteraction(id, source, target, reaction, date);
    }
}
