package ua.epam.models;

import ua.epam.users.utils.user.validator.tags.*;
import ua.epam.users.utils.user.validator.tags.Number;

import java.util.StringJoiner;

public class User implements IUser {
    @NotNull
    @Number
    private int id;
    @NotNull
    @Login
    private String login;
    @NotNull
    @Password
    private String password;
   // @NotNull
   // @Email
    private String email;
   // @NotNull
   // @Name
    private String name;
    //@Name
    private String surname;
    @NotNull
    private Role role;



    public User() {
    }

    public User(int id, String login, String password, String name,
                String surname, String email, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Role getRole() {
        return role;
    }

    @Override
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "User {", "}")
                .add("id: " + id)
                .add("login: " + login)
                .add("password: " + password)
                .add("name: " + name)
                .add("surname: " + surname)
                .add("email: " + email)
                .add("role: " + role.toString()).toString();

    }
}