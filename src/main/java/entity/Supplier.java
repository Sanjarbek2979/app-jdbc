package entity;

import lombok.*;

/**
 * @author Sanjarbek Allayev, пт 16:37. 14.01.2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Supplier {
    private Integer id;
    private String name;
    private boolean active;


}
