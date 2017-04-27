package Delivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by LevelNone on 27.04.2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    private String name;
    private String email;
    private String message;

    @Override
    public String toString()
    {
        return "Customer " + name + " from " + email + " has certain feedback about service: " + message;
    }
}
