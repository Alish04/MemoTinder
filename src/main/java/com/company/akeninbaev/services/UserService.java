package com.company.akeninbaev.services;

import com.company.akeninbaev.model.User;
import com.j256.ormlite.dao.Dao;

public class UserService extends AbstractService<User, Integer> {
    public UserService(Dao<User, Integer> dao) {
        super(dao);
    }
}
