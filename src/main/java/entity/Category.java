package entity;

import lombok.*;

/**
 * @author Sanjarbek Allayev, пт 9:25. 14.01.2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
    private Integer id;
    private String name;
    private boolean active;



}
