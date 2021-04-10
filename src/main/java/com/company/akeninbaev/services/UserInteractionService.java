package com.company.akeninbaev.services;

import com.company.akeninbaev.model.UserInteraction;
import com.j256.ormlite.dao.Dao;

public class UserInteractionService extends AbstractService<UserInteraction, Integer> {
    public UserInteractionService(Dao<UserInteraction, Integer> dao) {
        super(dao);
    }
}
