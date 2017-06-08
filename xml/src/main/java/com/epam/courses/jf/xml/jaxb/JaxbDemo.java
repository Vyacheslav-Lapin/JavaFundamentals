package com.epam.courses.jf.xml.jaxb;

import com.epam.courses.jf.xml.Food;
import lombok.val;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class JaxbDemo {

    public static final String FOOD_XML = "stud.xml";

    public static void main(String... args) throws JAXBException, FileNotFoundException {

        val food = new Food(123, "nnn", "333", "ddd", 234);

        marshal(food);
        System.out.println("XML-файл создан!");

        System.out.println(food.equals(unmarshal(FOOD_XML)));
    }

    private static Food unmarshal(String pathname) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Food.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Food) jaxbUnmarshaller.unmarshal(new File(pathname));
    }

    private static void marshal(Food food) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(Food.class);
        Marshaller m = context.createMarshaller();
        m.marshal(food, new FileOutputStream(FOOD_XML));
    }
}
