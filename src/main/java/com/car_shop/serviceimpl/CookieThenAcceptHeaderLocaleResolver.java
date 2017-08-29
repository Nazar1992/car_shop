package com.car_shop.serviceimpl;

import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class CookieThenAcceptHeaderLocaleResolver extends CookieLocaleResolver
{
    private final AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
    @Override
    protected Locale determineDefaultLocale(HttpServletRequest request)
    {
        Locale currentLocal = new Locale(acceptHeaderLocaleResolver.resolveLocale(request).getLanguage());
        if(currentLocal.equals("ua"))
            return new Locale("ua");
        return new Locale("en");
    }
}
