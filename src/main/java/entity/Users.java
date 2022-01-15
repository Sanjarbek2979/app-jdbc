package entity;

import lombok.*;

/**
 * @author Sanjarbek Allayev, пт 16:43. 14.01.2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Users {
    private Integer id;
    private String email;
    private String password;
    private String fullName;
    private String phone;
    private boolean active;

}

