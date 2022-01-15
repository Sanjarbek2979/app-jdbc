package entity;

import lombok.*;

/**
 * @author Sanjarbek Allayev, пт 16:30. 14.01.2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client {
private Integer id;
private String name;
private boolean active;
private String phone;

}
