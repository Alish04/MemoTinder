package com.company.akeninbaev.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDate;
import java.util.Objects;

@DatabaseTable
public class MemeReview implements Model<Integer> {
    @DatabaseField(id = true)
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User user;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Meme meme;

    @DatabaseField
    private String rating;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private LocalDate date;

    public MemeReview(int id, User user, Meme meme, String rating, LocalDate date) {
        this.id = id;
        this.user = user;
        this.meme = meme;
        this.rating = rating;
        this.date = date;
    }

    public MemeReview() {
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Meme getMeme() {
        return meme;
    }

    public void setMeme(Meme meme) {
        this.meme = meme;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemeReview that = (MemeReview) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(meme, that.meme) &&
                Objects.equals(rating, that.rating) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, meme, rating, date);
    }

    @Override
    public String toString() {
        return "MemeReview{" +
                "user=" + user +
                ", meme=" + meme +
                ", rating='" + rating + '\'' +
                ", date=" + date +
                '}';
    }
}
