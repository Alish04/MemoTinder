package com.company.akeninbaev.controller;

import com.company.akeninbaev.model.Model;
import io.javalin.http.Context;

public interface Controller<T extends Model<U>, U> {

    void getOne(Context context, U id);
    void getAll(Context context);
    void postOne(Context context);
    void patchOne(Context context, U id);
    void deleteOne(Context context, U id);
}
