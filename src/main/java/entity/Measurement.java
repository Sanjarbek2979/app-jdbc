package entity;

import lombok.*;

/**
 * @author Sanjarbek Allayev, пт 16:32. 14.01.2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Measurement {
    private Integer id;
    private String name;
    private boolean active;
}
