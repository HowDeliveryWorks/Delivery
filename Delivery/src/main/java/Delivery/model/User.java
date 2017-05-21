package Delivery.model;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

/**
 * Created by igor on 21.05.17.
 */
@Data
public class User {
    private UUID id;
    private String username;
    private String password;
    private String passwordConfirm;
    private Set<Role> roles;
}
