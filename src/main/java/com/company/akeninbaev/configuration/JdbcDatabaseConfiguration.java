package com.company.akeninbaev.configuration;

import com.company.akeninbaev.exception.ApplicationException;
import com.company.akeninbaev.model.Meme;
import com.company.akeninbaev.model.MemeReview;
import com.company.akeninbaev.model.User;
import com.company.akeninbaev.model.UserInteraction;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class JdbcDatabaseConfiguration implements DatabaseConfiguration{
    private final ConnectionSource connectionSource;
    public JdbcDatabaseConfiguration(String jdbcConnectionString) {
        try {
            connectionSource = new JdbcConnectionSource(jdbcConnectionString);
            TableUtils.createTableIfNotExists(connectionSource, User.class);
            TableUtils.createTableIfNotExists(connectionSource, Meme.class);
            TableUtils.createTableIfNotExists(connectionSource, MemeReview.class);
            TableUtils.createTableIfNotExists(connectionSource, UserInteraction.class);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throw new ApplicationException("Can't initialize database connection", throwable);
        }
    }
    @Override
    public ConnectionSource connectionSource() {
            return connectionSource;
        }
}
