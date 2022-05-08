package com.example.demo.utils;

import lombok.extern.log4j.Log4j2;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.context.i18n.LocaleContextHolder;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@Log4j2
public class MessageUtils {
    private final static String BASE_NAME = "messages";

    public static String getMessage(String code, Locale language, Object... args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BASE_NAME +"_vi");
        String message;
        try {
            message = resourceBundle.getString(code);
            message = MessageFormat.format(message, args);
        } catch (Exception ex) {
            log.debug(ex.getMessage(), ex);
            message = code;
        }
        return MessageFormatter.arrayFormat(message, args).getMessage();
    }

    public static String getMessage(String code) {
        return getMessage(code, LocaleContextHolder.getLocale(), null);
    }

    public static String getMessage(String code, Object... args) {
        return getMessage(code, LocaleContextHolder.getLocale(), args);
    }

}
