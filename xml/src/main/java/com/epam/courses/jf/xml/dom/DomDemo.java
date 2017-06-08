package com.epam.courses.jf.xml.dom;

import com.epam.courses.jf.xml.Food;
import com.hegel.core.functions.ExceptionalFunction;
import com.hegel.core.functions.ExceptionalSupplier;
import lombok.SneakyThrows;
import lombok.val;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class DomDemo {

    private static final Function<File, Document> DOCUMENT_EXTRACTOR =

            // Deprecated - создание DOM-анализатора Xerces напрямую
//            ExceptionalFunction.toUncheckedFunction(file -> {
//                DOMParser parser = new DOMParser();
//                try (FileInputStream fileInputStream = new FileInputStream(file)) {
//                    parser.parse(new InputSource(fileInputStream));
//                    return parser.getDocument();
//                }
//            });

            ExceptionalFunction.toUncheckedFunction(
                    ExceptionalSupplier.getOrThrowUnchecked(
                            DocumentBuilderFactory.newInstance()::newDocumentBuilder
                    )::parse);

    @SneakyThrows
    public static Stream<Food> getFoods(File file) {
        Document document = DOCUMENT_EXTRACTOR.apply(file);
        val root = document.getDocumentElement();
        return getElements(root.getElementsByTagName("food"))
                .map(foodElement -> new Food().id(Integer.parseInt(foodElement.getAttribute("id")))
                        .name(getSingleChild(foodElement, "name").getTextContent().trim())
                        .description(getSingleChild(foodElement, "description").getTextContent().trim())
                        .price(getSingleChild(foodElement, "price").getTextContent().trim())
                        .calories(Integer.parseInt(getSingleChild(foodElement, "calories").getTextContent().trim())));
    }

    private static Stream<Element> getElements(NodeList nodeList) {

        val length = nodeList.getLength();
        val iterator = new Iterator<Element>() {
            private int i = 0;
            @Override
            public boolean hasNext() {
                return i < length;
            }
            @Override
            public Element next() {
                return (Element) nodeList.item(i++);
            }
        };

        return StreamSupport.stream(Spliterators.spliterator(iterator, length, Spliterator.ORDERED), false);
    }

    private static Element getSingleChild(Element element, String childName) {
        NodeList nodeList = element.getElementsByTagName(childName);
        return (Element) nodeList.item(0);
    }

}
