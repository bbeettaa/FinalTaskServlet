package ua.epam.controller.commands;

import ua.epam.controller.commands.admin.*;
import ua.epam.controller.commands.common.LoginCommand;
import ua.epam.controller.commands.common.LogoutCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static CommandFactory factory = new CommandFactory();
    private static final Map<String, ICommand> commands = new HashMap<>();

    private CommandFactory() {}

    /**
     * Singleton.
     */
    public static CommandFactory commandFactory() {
        if (factory == null) factory = new CommandFactory();
        return factory;
    }

    static {
        // common commands
        // commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("login", new LoginCommand());
        commands.put("redirect", null);

        // admin commands
        //commands.put("main", new MainCommand());
        commands.put("addUser", new AddUserCommand());
        commands.put("deleteUser", new DeleteUserCommand());
        commands.put("updateUser", new UpdateUserCommand());
        commands.put("findUser", new FindUserCommand());
        commands.put("allUsers", new ShowAllUsersCommand());
        commands.put("updateUserPage", new GetUpdatePageCommand());

        // client commands
/*        commands.put("account", new AccountCommand());
        commands.put("personal_data", new PersonalDataCommand());
        commands.put("user_profile", new UserProfileCommand());
        commands.put("transactions", new TransactionCommand());
        commands.put("save_profile", new SaveUserProfileCommand());*/
    }

    public ICommand getCommand(HttpServletRequest request) {
        String action = request.getParameter("action");
        return commands.get(action);
    }
}
