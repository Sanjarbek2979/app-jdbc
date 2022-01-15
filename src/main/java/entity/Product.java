package entity;

import lombok.*;

/**
 * @author Sanjarbek Allayev, пт 16:34. 14.01.2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    private Integer id;
    private String name;
    private boolean active;
    private Integer categoryId;
    private Integer measurementId;
    private Integer attachmentId;
}
