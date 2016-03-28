package com.epam.courses.jf.xml.sax;

import com.epam.courses.jf.xml.Food;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuSaxHandler extends DefaultHandler {

    public static final String MENU_XML = "src/main/resources/menu.xml";

    private List<Food> foodList = new ArrayList<>();
    private Food food;
    private StringBuilder text;

    public List<Food> getFoodList() {
        return foodList;
    }

    public void startDocument() throws SAXException {
        System.out.println("Parsing started.");
    }

    public void endDocument() throws SAXException {
        System.out.println("Parsing ended.");
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        System.out.println("startElement -> " + "uri: "
                + uri + ", localName: " + localName + ", qName: " + qName);

        text = new StringBuilder();
        if (qName.equals("food"))
            food = new Food(Integer.parseInt(attributes.getValue("id")));
    }

    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "name":
                food.setName(text.toString());
                break;
            case "price":
                food.setPrice(text.toString());
                break;
            case "description":
                food.setDescription(text.toString().trim());
                break;
            case "calories":
                food.setCalories(Integer.parseInt(text.toString()));
                break;
            case "food":
                foodList.add(food);
        }
    }

    public void warning(SAXParseException exception) {
        System.err.println("WARNING: line " + exception.getLineNumber() + ": " + exception.getMessage());
    }

    public void error(SAXParseException exception) {
        System.err.println("ERROR: line " + exception.getLineNumber() + ": " + exception.getMessage());
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        System.err.println("FATAL: line " + exception.getLineNumber() + ": "
                + exception.getMessage());
        throw (exception);
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        XMLReader reader = XMLReaderFactory.createXMLReader();
        MenuSaxHandler handler = new MenuSaxHandler();
        reader.setContentHandler(handler);
        reader.parse(new InputSource(MENU_XML));

        List<Food> menu = handler.getFoodList();

        System.out.println(menu);

    }
}
