package com.company.akeninbaev.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDate;
import java.util.Objects;

@DatabaseTable
public class UserInteraction implements Model<Integer> {
    @DatabaseField(id = true)
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User source;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User target;

    @DatabaseField
    private boolean reaction;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private LocalDate date;

    public UserInteraction(int id, User source, User target, boolean reaction, LocalDate date) {
        this.source = source;
        this.target = target;
        this.reaction = reaction;
        this.date = date;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public UserInteraction() {
    }

    public User getSource() {
        return source;
    }

    public void setSource(User source) {
        this.source = source;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public boolean getReaction() {
        return reaction;
    }

    public void setReaction(boolean reaction) {
        this.reaction = reaction;
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
        UserInteraction that = (UserInteraction) o;
        return Objects.equals(source, that.source) &&
                Objects.equals(target, that.target) &&
                Objects.equals(reaction, that.reaction) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target, reaction, date);
    }

    @Override
    public String toString() {
        return "UserInteraction{" +
                "source=" + source +
                ", target=" + target +
                ", reaction='" + reaction + '\'' +
                ", date=" + date +
                '}';
    }
}
