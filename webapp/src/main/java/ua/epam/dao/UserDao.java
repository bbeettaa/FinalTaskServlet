package ua.epam.dao;

import ua.epam.models.Role;
import ua.epam.models.entities.IUser;

import java.util.List;
import java.util.Objects;

public class UserDao {
    public boolean userIsExist(String login, String password) {
        DaoUserService dao = new DaoUserService();
        return dao.findAllUsers().stream()
                .anyMatch(u -> Objects.equals(u.getLogin(), login)
                        && Objects.equals(u.getPassword(), password));
    }

    public Role getRoleByLoginPassword(String login, String password) {
        DaoUserService dao = new DaoUserService();
        return dao.findAllUsers().stream()
                .filter(u -> Objects.equals(u.getLogin(), login)
                        && Objects.equals(u.getPassword(), password)).findFirst().get().getRole();
    }

    public IUser getUserByLoginPassword(String login, String password) {
        DaoUserService dao = new DaoUserService();
        return dao.findAllUsers().stream()
                .filter(u -> Objects.equals(u.getLogin(), login)
                        && Objects.equals(u.getPassword(), password)).findFirst().get();
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

    public IUser getById(int id){
        DaoUserService dao = new DaoUserService();
        return dao.findUserById(id);
    }
}
