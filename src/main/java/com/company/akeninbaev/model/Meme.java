package com.company.akeninbaev.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDate;
import java.util.Objects;

@DatabaseTable
public class Meme implements Model<Integer>{
    @DatabaseField(id = true)
    private int id;

    @DatabaseField
    private String link;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private LocalDate date;

    public Meme(int id, String link, LocalDate date) {
        this.id = id;
        this.link = link;
        this.date = date;
    }

    public Meme() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
        Meme meme = (Meme) o;
        return id == meme.id &&
                Objects.equals(link, meme.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link);
    }

    @Override
    public String toString() {
        return "Meme{" +
                "id=" + id +
                ", link='" + link + '\'' +
                '}';
    }
}
