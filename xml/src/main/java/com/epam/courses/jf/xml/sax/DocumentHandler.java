package com.epam.courses.jf.xml.sax;

import org.xml.sax.AttributeList;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * @deprecated This interface has been replaced by the SAX2
 *             {@link org.xml.sax.ContentHandler ContentHandler}
 *             interface, which includes Namespace support.
 */
public interface DocumentHandler {
    void setDocumentLocator(Locator locator);
    void startDocument() throws SAXException;
    void endDocument() throws SAXException;
    void startElement(String name, AttributeList atts) throws SAXException;
    void endElement(String name) throws SAXException;
    void characters(char ch[], int start, int length) throws SAXException;
    void ignorableWhitespace(char ch[], int start, int length) throws SAXException;
    void processingInstruction(String target, String data) throws SAXException;
}
