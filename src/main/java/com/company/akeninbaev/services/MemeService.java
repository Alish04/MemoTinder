package com.company.akeninbaev.services;


import com.company.akeninbaev.model.Meme;
import com.j256.ormlite.dao.Dao;

public class MemeService extends AbstractService<Meme, Integer> {
    public MemeService(Dao<Meme, Integer> dao) {
        super(dao);
    }
}
