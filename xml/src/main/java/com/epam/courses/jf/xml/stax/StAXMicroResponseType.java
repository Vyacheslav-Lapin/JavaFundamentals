package com.epam.courses.jf.xml.stax;

public enum StAXMicroResponseType {
    START_ELEMENT,
    END_ELEMENT,
    PROCESSING_INSTRUCTION,
    CHARACTERS,
    COMMENT,
    SPACE,
    START_DOCUMENT,
    END_DOCUMENT,
    ENTITY_REFERENCE,
    ATTRIBUTE,
    DTD,
    CDATA,
    NAMESPACE,
    NOTATION_DECLARATION,
    ENTITY_DECLARATION;

    public static StAXMicroResponseType from(int id) {
        return values()[id - 1];
    }
}
