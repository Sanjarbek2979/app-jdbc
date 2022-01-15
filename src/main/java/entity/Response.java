package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sanjarbek Allayev, пт 10:53. 14.01.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private boolean success;
    private String message;
}
