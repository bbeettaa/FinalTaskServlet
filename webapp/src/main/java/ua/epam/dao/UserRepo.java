package ua.epam.dao;

import ua.epam.dao.entities.DaoUserService;
import ua.epam.models.Role;
import ua.epam.models.entities.user.IUser;

import java.util.List;
import java.util.Objects;

/**
 * @Facade to mysql connection pool
 * */

public class UserRepo {
    public boolean userIsExist(String login, String password) {
        DaoUserService dao = new DaoUserService();
        return dao.findAllUsers().stream()
                .anyMatch(u -> Objects.equals(u.getLogin(), login)
                        && Objects.equals(u.getPassword(), password));
    }

    public Role getRoleByLoginPassword(String login, String password) {
        DaoUserService dao = new DaoUserService();
        java.util.Optional<IUser> optional = dao.findAllUsers().stream()
                .filter(u -> Objects.equals(u.getLogin(), login)
                        && Objects.equals(u.getPassword(), password)).findFirst();
        return optional.map(IUser::getRole).orElse(null);
    }

    public IUser getUserByLoginPassword(String login, String password) {
        DaoUserService dao = new DaoUserService();
        java.util.Optional<IUser> optional = dao.findAllUsers().stream()
                .filter(u -> Objects.equals(u.getLogin(), login)
                        && Objects.equals(u.getPassword(), password)).findFirst();
        return optional.orElse(null);
    }

    public boolean deleteById(int id) {
        DaoUserService dao = new DaoUserService();
        return dao.deleteUserById(id);
    }

    public List<IUser> getAll() {
        DaoUserService dao = new DaoUserService();
        return dao.findAllUsers();
    }

    public boolean update(IUser user) {
        DaoUserService dao = new DaoUserService();
        return dao.update(user);
    }

    public boolean add(IUser user) {
        DaoUserService dao = new DaoUserService();
        return dao.add(user);
    }

    public IUser getById(int id) {
        DaoUserService dao = new DaoUserService();
        java.util.Optional<IUser> optional = dao.findAllUsers().stream().filter(u -> u.getId() == id).findFirst();
        return optional.orElse(null);
    }
}
