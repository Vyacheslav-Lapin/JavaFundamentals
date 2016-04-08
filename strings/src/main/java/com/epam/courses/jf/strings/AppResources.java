package com.epam.courses.jf.strings;

import java.util.ListResourceBundle;

public class AppResources extends ListResourceBundle {

    @Override
    public Object[][] getContents() {
        return new Object[][] {
                { "prop.key1", "value01" },
                { "prop.key2", "value02" },
        };
    }
}
