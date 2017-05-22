package Delivery.entity;

import lombok.Data;

import java.util.Set;

/**
 * Created by igor on 21.05.17.
 */
@Data
public class Role {
    private Long id;
    private String name;
    private Set<User> users;
}
