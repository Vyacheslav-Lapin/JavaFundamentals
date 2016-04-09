package com.epam.courses.jf.strings;

import java.util.ListResourceBundle;
import java.util.ResourceBundle;

public class AppResources extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                { "prop.key1", "value01" },
                { "prop.key2", "value02" },
        };
    }

    public static void main(String[] args) {
        ResourceBundle bundle;
        String key = "prop.key1";
        bundle = ResourceBundle.getBundle("com.epam.courses.jf.strings.AppResources");
        System.out.println(bundle.getString(key));
    }
}
