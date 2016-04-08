package com.epam.courses.jf.strings;

import java.util.ResourceBundle;

public class Localizator {


    public static void main(String[] args) {

        ResourceBundle bundle;
        String key = "prop.key1";
        bundle = ResourceBundle.getBundle("com.epam.courses.jf.strings.AppResources");
        System.out.println(bundle.getString(key));

//        Locale defaultLocale = Locale.getDefault();
//        Locale rusLocale = new Locale("ru","RU");
//        Locale usLocale = new Locale("en", "US");
//        Locale frLocale = new Locale("fr", "FR");

//        System.out.println(defaultLocale.getDisplayCountry());
//        System.out.println(defaultLocale.getDisplayCountry(Locale.FRENCH));
//        System.out.println(frLocale.getDisplayCountry(defaultLocale));
//
//        System.out.println(usLocale.getDisplayName());
//        System.out.println(usLocale.getDisplayName(frLocale));
//        System.out.println(rusLocale.getDisplayName(frLocale));

//        System.out.println(defaultLocale.getCountry());
//        System.out.println(defaultLocale.getLanguage());
//        System.out.println(defaultLocale.getVariant());

    }
}
