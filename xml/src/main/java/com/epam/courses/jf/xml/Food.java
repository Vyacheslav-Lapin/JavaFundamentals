package com.epam.courses.jf.xml;

import javax.xml.bind.annotation.*;
import java.util.Objects;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

@XmlRootElement
@XmlAccessorType(FIELD)
@XmlType(name = "Food", propOrder = {"name", "price", "description", "calories"})
public class Food {

    @XmlAttribute(required = true)
    private final int id;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private String price;

    @XmlElement(required = true)
    private String description;

    @XmlElement(required = true)
    private int calories;


    public Food() {
        this(0);
    }

    public Food(int id) {
        this(id, null, null, null, 0);
    }

    public Food(int id, String name, String price, String description, int calories) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.calories = calories;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }


    @Override
    public boolean equals(Object o) {
        return this == o
                || !(o == null || getClass() != o.getClass())
                && id == ((Food) o).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Food{" + "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
