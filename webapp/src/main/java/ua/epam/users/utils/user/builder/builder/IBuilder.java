package ua.epam.users.utils.user.builder.builder;

import ua.epam.models.IUser;

public interface IBuilder {

    IBuilder buildId(String value) throws IllegalArgumentException;
    IBuilder buildLogin(String value);
    IBuilder buildPassword(String value);
    IBuilder buildName(String value);
    IBuilder buildSurname(String value);
    IBuilder buildEmail(String value);
    IBuilder buildRole(String value);
    IBuilder reset();
    IUser getResult();
}
