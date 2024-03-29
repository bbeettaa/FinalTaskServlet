package ua.epam.utils.user.validator;


import ua.epam.AppContext;
import ua.epam.utils.user.validator.tags.*;
import ua.epam.utils.user.validator.tags.Number;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private UserValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean validate(Object object) {
        try {
            correctPassword(object);
            correctName(object);
            notNull(object) ;
            correctLogin(object);
            correctEmail(object);
            number(object);
        } catch (Exception e) {
            AppContext.LOGGER.error(e.getMessage());
            return false;
        }
        return true;
    }

    private static void number(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Number.class)) {
                String value =  field.get(object).toString();
                if (value == null ||
                        (value.length() <= 0) ||
                        !value.matches("[+]?\\d+"))
                    throw new IllegalArgumentException("Field '" + field.getName()
                            + "' in class '" + field.getClass().getName() + "' not a number");

            }
        }
    }

    private static void correctPassword(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Password.class)) {
                String value = (String) field.get(object);
                Matcher matcher = Pattern.compile(Password.regEx)
                        .matcher(value);
                if (!matcher.find()) {
                    throw new IllegalArgumentException("cannot validate password: " + value );
                }
            }
        }
    }

    private static void correctName(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Name.class)) {
                String value = (String) field.get(object);
                Matcher matcher = Pattern.compile(Name.regEx)
                        .matcher(value);
                if (!matcher.find())
                    throw new IllegalArgumentException("cannot validate Name or Surname by regex (\""+Name.regEx+"\"): " + value );
            }
        }
    }

    private static void correctLogin(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Login.class)) {
                String value = (String) field.get(object);
                Matcher matcher = Pattern.compile(Login.regEx)
                        .matcher(value);
                if (!matcher.find())
                    throw new IllegalArgumentException("cannot validate Logout: " + value );
            }
        }
    }

    private static void notNull(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getFields()) {
            if (field.isAnnotationPresent(NotNull.class) && (Objects.isNull(field.get(object))))
                    throw new IllegalArgumentException("Field '" + field.getName()
                            + "' in class" + field.getClass().getName() + " is empty");
            }
    }

    private static void correctEmail(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Email.class)) {
                String value = (String) field.get(object);
                Matcher matcher = Pattern.compile(Email.regEx)
                        .matcher(value);
                if (!matcher.find())
                    throw new IllegalArgumentException("cannot validate email: " + value );
            }
        }
    }


}