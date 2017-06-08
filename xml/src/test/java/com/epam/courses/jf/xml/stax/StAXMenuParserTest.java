package com.epam.courses.jf.xml.stax;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLInputFactory;
import java.io.FileInputStream;
import java.io.InputStream;

import static com.epam.courses.jf.xml.TestConsts.RESOURCES_PATH_PREFIX;

class StAXMenuParserTest {

    private static final String MENU_XML = RESOURCES_PATH_PREFIX + "menu.xml";

    @Test
    @SneakyThrows
    void process() {
        try (InputStream input = new FileInputStream(MENU_XML)) {
            System.out.println(
                    StAXMenuParser.getFoods(
                            XMLInputFactory.newInstance()
                                    .createXMLStreamReader(input)));
        }
    }

//    @Test
//    void setField() {
//    }

}