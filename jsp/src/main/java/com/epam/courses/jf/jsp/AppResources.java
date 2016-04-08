package com.epam.courses.jf.jsp;

import java.util.ListResourceBundle;

public class AppResources extends ListResourceBundle {

    @Override
    public Object[][] getContents() {
        return new Object[][] {
                { "local.message", "\u041F\u0440\u0438\u0432\u0435\u0442." },
                { "local.locbutton.name.en", "\u0430\u043D\u0433\u043B" },
                { "local.locbutton.name.ru", "\u0440\u0443\u0441" }
        };
    }
}
