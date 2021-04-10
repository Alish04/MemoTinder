package com.company.akeninbaev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import io.javalin.core.security.Role;

import java.time.LocalDate;
import java.util.Objects;

@DatabaseTable
public class User implements Model<Integer> {

    @DatabaseField(id = true)
    private Integer id;

    @DatabaseField
    private String firstName;

    @DatabaseField
    private String lastName;

    @DatabaseField
    private String sex;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private LocalDate birthday;

    @DatabaseField
    private String country;

    @DatabaseField
    private String city;

    @DatabaseField
    private String phone;

    @DatabaseField
    private UserRole role;

    @DatabaseField
    private String login;

    @DatabaseField
    private String password;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @JsonIgnore
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(Integer id, String firstName, String lastName, String sex, LocalDate birthday, String country, String city, String phone, UserRole role, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthday = birthday;
        this.country = country;
        this.city = city;
        this.phone = phone;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(sex, user.sex) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(country, user.country) &&
                Objects.equals(city, user.city) &&
                Objects.equals(phone, user.phone) &&
                role == user.role &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, sex, birthday, country, city, phone, role, login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
