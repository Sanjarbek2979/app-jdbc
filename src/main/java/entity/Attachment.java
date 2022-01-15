package entity;

import lombok.*;

/**
 * @author Sanjarbek Allayev, пт 16:23. 14.01.2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Attachment {
    private Integer id;
    private String name;
    private Double size;
    private String contentType;

}
