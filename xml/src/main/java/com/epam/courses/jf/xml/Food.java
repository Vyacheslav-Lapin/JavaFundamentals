package com.epam.courses.jf.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.*;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

@XmlRootElement
@XmlAccessorType(FIELD)
@XmlType(name = "Food", propOrder = {"name", "price", "description", "calories"})
@Data
@Accessors(chain = true, fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @XmlAttribute(required = true)
    private int id;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private String price;

    @XmlElement(required = true)
    private String description;

    @XmlElement(required = true)
    private int calories;
}
