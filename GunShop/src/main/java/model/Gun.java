package model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Gun {
    private final int id;
    private final String name;
    private final double caliber;
}
