package Delivery.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

/**
 * Created by LevelNone on 20.04.2017.
 */
@Data
@NoArgsConstructor
public class Customer {
    @Id
    private UUID id;
    private String Name;
    private String email;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Customer(UUID id, String name, String email) {
        this.id = id;
        Name = name;
        this.email = email;
    }
}
