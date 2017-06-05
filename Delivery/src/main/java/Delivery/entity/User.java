package Delivery.entity;

import Delivery.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Set;
import java.util.UUID;

/**
 * Created by igor on 21.05.17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String email;
    private String password;
    private String passwordConfirm;
    private Set<Role> roles;
}
