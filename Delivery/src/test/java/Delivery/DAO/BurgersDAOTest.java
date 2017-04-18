package Delivery.DAO;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by igor on 15.04.17.
 */
public class BurgersDAOTest {

    @Autowired
    private BurgersDAO dao;

    @Test
    public void findByName() throws Exception {
        System.out.print(dao.findAll());
    }

}