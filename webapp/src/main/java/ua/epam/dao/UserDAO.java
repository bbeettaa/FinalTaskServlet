package ua.epam.dao;

import ua.epam.models.IUser;
import ua.epam.models.Role;
import ua.epam.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final List<IUser> store = new ArrayList<>();

    public List<IUser> getAll(){
        return store;
    }

    public void deleteById(int id) {
        IUser result = new User();
        result.setId(-1);

        store.remove(store.stream().filter(user -> user.getId() == id).findFirst().get());
    }

    public IUser getById(int id) {

        IUser result = new User();
        result.setId(-1);

        for (IUser user : store) {
            if (user.getId() == id) {
                result = user;
            }
        }

        return result;
    }

    public IUser getUserByLoginPassword(final String login, final String password) {

        IUser result = new User();
        result.setId(-1);

        for (IUser user : store) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user;
            }
        }

        return result;
    }

    public boolean add(final IUser user) {

        for (IUser u : store) {
            if (u.getLogin().equals(user.getLogin()) && u.getPassword().equals(user.getPassword())) {
                return false;
            }
        }

        return store.add(user);
    }

    public Role getRoleByLoginPassword(final String login, final String password) {
        Role result = Role.UNREGISTERED;

        for (IUser user : store) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user.getRole();
            }
        }

        return result;
    }

    public boolean userIsExist(final String login, final String password) {

        boolean result = false;

        for (IUser user : store) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = true;
                break;
            }
        }

        return result;
    }
}