package com.epam.courses.jf.xml.dom;

import com.epam.courses.jf.xml.Food;
import lombok.val;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import static com.epam.courses.jf.xml.TestConsts.RESOURCES_PATH_PREFIX;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DomDemoTest {

    private static final String MENU_XML_FILE_ADDRESS = RESOURCES_PATH_PREFIX + "menu.xml";

    public static void main(String... args) throws IOException, SAXException {

        val menu = DomDemo.getFoods(new File(MENU_XML_FILE_ADDRESS))
                .collect(Collectors.toList());

        assertTrue(
                menu.contains(
                        new Food(1,
                                "Belgian Waffles",
                                "$5.95",
                                "two of our famous Belgian Waffles with plenty of real maple syrup",
                                650)));
    }
}
