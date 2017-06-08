package com.epam.courses.jf.xml.stax;

import com.epam.courses.jf.xml.Food;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.epam.courses.jf.xml.stax.StAXMicroResponseType.from;

public class StAXMenuParser {

    static List<Food> getFoods(XMLStreamReader reader) throws XMLStreamException {
        List<Food> menu = new ArrayList<>();
        Food food = null;
        String elementName = null;
        while (reader.hasNext()) {
            // определение типа "прочтённого" элемента (тега)
            switch (from(reader.next())) {
                case START_ELEMENT:
                    elementName = reader.getLocalName();
                    if (elementName.equals("food"))
                        food = new Food(Integer.parseInt(reader.getAttributeValue(null, "id")));
                    break;
                case CHARACTERS:
                    setField(food, elementName, reader.getText().trim());
                    break;
                case END_ELEMENT:
                    if (reader.getLocalName().equals("food"))
                        menu.add(food);
            }
        }
        return menu;
    }

    static void setField(Food food, String elementName, String text) {
        if (!text.isEmpty())
            switch (elementName) {
                case "name":
                    food.setName(text);
                    break;
                case "price":
                    food.setPrice(text);
                    break;
                case "description":
                    food.setDescription(text);
                    break;
                case "calories":
                    food.setCalories(Integer.parseInt(text));
            }
    }
}
