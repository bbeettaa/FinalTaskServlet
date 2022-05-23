package ua.epam.controller;

/**
 * Path to view files (.jsp)
 */
public class ViewPath {
    private ViewPath() {
        throw new IllegalStateException("Utility class");
    }
    public static final String ADMIN_MENU = "WEB-INF/view/admin/admin_menu.jsp";
    public static final String USER_MENU = "WEB-INF/view/admin/user_menu.jsp";

    public static final String UPDATE_USER ="WEB-INF/view/common/update.jsp";
    public static final String LOGIN_MENU ="WEB-INF/view/common/login.jsp";

    public static final String ERROR_PAGE ="WEB-INF/view/errorPage.jsp";


    public static final String LOGIN_COMMAND ="controller?action=login";
    public static final String ALL_USERS_COMMAND ="controller?action=allUsers";
    public static final String FIND_USER_COMMAND ="controller?action=findUser";
}
