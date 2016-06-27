package model;

import lombok.*;

@Value
public class Gun {
    private final int id;
    private final String name;
    private final double caliber;
}
