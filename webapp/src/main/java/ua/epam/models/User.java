package ua.epam.models;

import ua.epam.users.utils.UserValidator.tags.*;
import ua.epam.users.utils.UserValidator.tags.Number;

public class User {
    @NotNull
    @Number
    private int id;
    @NotNull
    @Login
    private String login;
    @NotNull
    @Password
    private String password;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Name
    private String name;
    @Name
    private String surname;
    @NotNull
    private Role role;

    public User() {
    }

    public User(int id, String login, String password,String email, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email=email;
        this.role = role;
    }

    public User(int id, String login, String password, String name,
                String surname,String email, Role role) {
        this(id, login, password,email, role);
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}