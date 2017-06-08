package com.epam.courses.jf.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * Cleaned org.xml.sax.ContentHandler
 */
public interface ContentHandler {
    void setDocumentLocator(Locator locator);
    void startDocument() throws SAXException;
    void endDocument() throws SAXException;
    void startPrefixMapping(String prefix, String uri) throws SAXException;
    void endPrefixMapping(String prefix) throws SAXException;
    void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException;
    void endElement(String uri, String localName, String qName) throws SAXException;
    void characters(char ch[], int start, int length) throws SAXException;
    void ignorableWhitespace(char ch[], int start, int length) throws SAXException;
    void processingInstruction(String target, String data) throws SAXException;
    void skippedEntity(String name) throws SAXException;
}
