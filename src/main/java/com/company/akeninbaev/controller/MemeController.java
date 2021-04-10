package com.company.akeninbaev.controller;

import com.company.akeninbaev.model.Meme;
import com.company.akeninbaev.model.User;
import com.company.akeninbaev.model.UserRole;
import com.company.akeninbaev.services.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;

import java.util.Random;

public class MemeController extends AuthorizedController<Meme, Integer> {
    private final Service<User, Integer> userService;

    public MemeController(Service<Meme, Integer> service, ObjectMapper objectMapper, Service<User, Integer> userService) {
        super(service, objectMapper, Meme.class);
        this.userService = userService;
    }


    @Override
    public Service<User, Integer> userService() {
        return userService;
    }

    @Override
    public boolean isAuthorized(User user, Context context) {
        if (context.method().equals("GET")) {
            return true;
        } else {
            return user.getRole() == UserRole.ADMIN;
        }
    }

    @Override
    public void getOne(Context context, Integer id) {
        Random rand = new Random();
        int memes;
        for(int i = 0; i < 20; i++) {
            memes = rand.nextInt(100);
            super.getOne(context, memes);
        }
    }
}

