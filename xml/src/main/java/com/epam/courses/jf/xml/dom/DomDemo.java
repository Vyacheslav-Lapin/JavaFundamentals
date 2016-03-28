package com.epam.courses.jf.xml.dom;

import com.epam.courses.jf.xml.Food;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomDemo {

    public static final String MENU_XML = "src/main/resources/menu.xml";

    public static void main(String... args) throws IOException, SAXException {

        Element root = getDocument(MENU_XML).getDocumentElement();

        List<Food> menu = new ArrayList<>();

        NodeList foodNodes = root.getElementsByTagName("food");
        for (int i = 0; i < foodNodes.getLength(); i++)
            menu.add(toFood((Element) foodNodes.item(i)));

        System.out.println(menu);
    }

    private static Document getDocument(String xmlFileName) throws SAXException, IOException {
        DOMParser parser = new DOMParser(); //создание DOM-анализатора (Xerces)
        parser.parse(xmlFileName);
        return parser.getDocument();
    }

    private static Element getElementByTagName(Element element, String childName){
        return (Element) element.getElementsByTagName(childName).item(0);
    }

    private static Food toFood(Element foodElement) {
        int id = Integer.parseInt(foodElement.getAttribute("id"));
        String name = getElementByTagName(foodElement, "name").getTextContent().trim();
        String price = getElementByTagName(foodElement, "price").getTextContent();
        int calories = Integer.parseInt(getElementByTagName(foodElement, "calories").getTextContent().trim());
        String description = getElementByTagName(foodElement, "description").getTextContent().trim();

        return new Food(id, name, price, description, calories);
    }

}
