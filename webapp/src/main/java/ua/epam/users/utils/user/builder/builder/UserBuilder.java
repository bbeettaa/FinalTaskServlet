package ua.epam.users.utils.user.builder.builder;

import ua.epam.models.IUser;
import ua.epam.models.Role;
import ua.epam.models.User;
import ua.epam.users.utils.user.validator.UserValidator;

public class UserBuilder implements IBuilder {
    IUser result;
    public UserBuilder(){
        reset();
    }

    @Override
    public IBuilder buildId(String value) throws IllegalArgumentException {
        if(value != null && (value.length() > 0) && value.matches("[+]?\\d+"))
            result.setId(Integer.parseInt(value));
        else throw new IllegalArgumentException("id = '"+value+"' not number");
        return this;
    }

    @Override
    public IBuilder buildLogin(String value) {
        this.result.setLogin(value);
        return this;
    }

    @Override
    public IBuilder buildPassword(String value) {
        this.result.setPassword(value);
        return this;
    }

    @Override
    public IBuilder buildName(String value) {
        this.result.setName(value);
        return this;
    }

    @Override
    public IBuilder buildSurname(String value) {
        this.result.setSurname(value);
        return this;
    }

    @Override
    public IBuilder buildEmail(String value) {
        this.result.setEmail(value);
        return this;
    }

    @Override
    public IBuilder buildRole(String value) {
        this.result.setRole(Enum
                .valueOf(Role.UNREGISTERED.getDeclaringClass(),
                        value.toUpperCase()));
        return this;
    }

    @Override
    public IBuilder reset() {
        result = new User();
        return this;
    }

    @Override
    public IUser getResult() {
        if(UserValidator.validate(result)) {
            IUser result = this.result;
            reset();
            return result;
        }
        throw new IllegalArgumentException("class '"+result+"' cannot validate");
    }
}
