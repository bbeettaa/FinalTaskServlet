package ua.epam.users.utils.UserValidator;


import ua.epam.users.utils.UserValidator.tags.*;
import ua.epam.users.utils.UserValidator.tags.Number;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    public static boolean validate(Object object) {
        boolean isValid = false;
        try {
            isValid = isCorrectPassword(object) && isCorrectName(object)
                    && isNotNull(object) && isCorrectLogin(object)
                    && isNumber(object) && isCorrectEmail(object);
        } catch (IllegalAccessException e) {

        }
        return isValid;
    }

    private static boolean isNumber(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Number.class)) {
                field.setAccessible(true);
                String value = (String) field.get(object);
                if (value != null &&
                        (value.length() > 0) &&
                        value.matches("[+]?\\d+"))
                    throw new IllegalArgumentException("Field '" + field.getName()
                            + "' in class" + field.getClass().getName() + " not a number");
            }
        }
        return true;
    }

    private static boolean isCorrectPassword(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Password.class)) {
                field.setAccessible(true);
                String value = (String) field.get(object);
                Matcher matcher = Pattern.compile(Password.regEx)
                        .matcher(value);
                if (!matcher.find()) {
                    throw new IllegalArgumentException("password '" + value
                            + " incorrect");
                }
            }
        }
        return true;
    }

    private static boolean isCorrectName(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Name.class)) {
                field.setAccessible(true);
                String value = (String) field.get(object);
                Matcher matcher = Pattern.compile(Name.regEx)
                        .matcher(value);
                if (!matcher.find())
                    throw new IllegalArgumentException("Name or Surname '" + value
                            + " incorrect");
            }
        }
        return true;
    }

    private static boolean isCorrectLogin(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Login.class)) {
                field.setAccessible(true);
                String value = (String) field.get(object);
                Matcher matcher = Pattern.compile(Login.regEx)
                        .matcher(value);
                if (!matcher.find())
                    throw new IllegalArgumentException("Login '" + value
                            + " incorrect");
            }
        }
        return true;
    }

    private static boolean isNotNull(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getFields()) {
            if (field.isAnnotationPresent(NotNull.class)) {
                if (Objects.isNull(field.get(object)))
                    throw new IllegalArgumentException("Field '" + field.getName()
                            + "' in class" + field.getClass().getName() + " is empty");
            }
        }
        return true;
    }

    private static boolean isCorrectEmail(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Email.class)) {
                field.setAccessible(true);
                String value = (String) field.get(object);
                Matcher matcher = Pattern.compile(Email.regEx)
                        .matcher(value);
                if (!matcher.find())
                    throw new IllegalArgumentException("Login '" + value
                            + " incorrect");
            }
        }
        return true;
    }


}