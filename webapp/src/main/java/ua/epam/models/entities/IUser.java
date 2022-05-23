package ua.epam.models.entities;

import ua.epam.models.Role;

public interface IUser extends Entity {
    public int getId();

    public void setId(int id);

    public String getLogin();

    public void setLogin(String login);

    public void setPassword(String password);

    public String getPassword();

    public Role getRole();

    public void setRole(Role role);

    public String getName();

    public void setName(String name);

    public String getSurname();

    public void setSurname(String surname);

    public String getEmail();

    public void setEmail(String email);
}
