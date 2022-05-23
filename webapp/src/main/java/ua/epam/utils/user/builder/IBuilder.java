package ua.epam.utils.user.builder;

import javax.servlet.http.HttpServletRequest;

public interface IBuilder {
    Object build(HttpServletRequest req);
}
