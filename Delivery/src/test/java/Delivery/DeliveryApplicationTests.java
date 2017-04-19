package Delivery;

import Delivery.DAO.BurgersDAO;
import Delivery.model.BreadType;
import Delivery.model.Burger;
import Delivery.model.MeatType;
import Delivery.model.Roasting;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeliveryApplicationTests {

    @Autowired
    private BurgersDAO dao;

	@Test
	public void contextLoads() {
	    dao.deleteAll();
	    ArrayList<Burger> burgers = new ArrayList<>();

	    ArrayList<String> ing = new ArrayList<>();
	    Collections.addAll(ing,"Mayo", "Iceberg Lettuce", "Red Onion", "Chess", "Tomato", "Pickles", "BBQ sauce");
        Burger burger = new Burger(UUID.randomUUID(), "New York", MeatType.Beef, Roasting.Medium, BreadType.WihiteBread, false, ing, 400, 210);
        burgers.add(burger);

        ArrayList<String> ing1 = new ArrayList<>();
        Collections.addAll(ing1,"Mayo", "Iceberg Lettuce", "Tomato", "BBQ sauce", "Chess", "Bacon", "Pickles", "Onion Rings");
        Burger burger1 = new Burger(UUID.randomUUID(), "The Burger", MeatType.Chicken, Roasting.WellDone, BreadType.WihiteBread, false, ing1, 390, 290);
        burgers.add(burger1);

        ArrayList<String> ing2 = new ArrayList<>();
        Collections.addAll(ing2,"Iceberg Lettuce", "Sauce Salsa", "Secret Sauce", "Chess Viola");
        Burger burger2 = new Burger(UUID.randomUUID(), "Boston", MeatType.Duck, Roasting.WellDone, BreadType.WihiteBread, true, ing2, 360, 265);
        burgers.add(burger2);

        ArrayList<String> ing3 = new ArrayList<>();
        Collections.addAll(ing3,"Berry Sauce", "Iceberg Lettuce", "Chess Dar Blue", "Caramelized Apples");
        Burger burger3 = new Burger(UUID.randomUUID(), "Carolina", MeatType.Duck, Roasting.WellDone, BreadType.WihiteBread, false, ing3, 320, 225);
        burgers.add(burger3);

        ArrayList<String> ing4 = new ArrayList<>();
        Collections.addAll(ing4,"Onion", "Mozzarella", "Bulgarian Pepper", "BBQ sauce", "Iceberg Lettuce", "Tomato", "Eggplant", "Zucchini");
        Burger burger4 = new Burger(UUID.randomUUID(), "Florida", MeatType.Beef, Roasting.Medium, BreadType.BlackBread, false, ing4, 360, 165);
        burgers.add(burger4);

        ArrayList<String> ing5 = new ArrayList<>();
        Collections.addAll(ing5,"Iceberg Lettuce", "Tomato", "Mustard", "Red Onion", "Mayo", "Chess Gauda", "Catchup");
        Burger burger5 = new Burger(UUID.randomUUID(), "Williams", MeatType.Beef, Roasting.MediumWell, BreadType.WihiteBread, true, ing5, 310, 160);
        burgers.add(burger5);

        ArrayList<String> ing6 = new ArrayList<>();
        Collections.addAll(ing6,"Tomato", "Mozzarella", "Iceberg Lettuce", "Sauce Pesto");
        Burger burger6 = new Burger(UUID.randomUUID(), "Kentucky", MeatType.Chicken, Roasting.WellDone, BreadType.WihiteBread, false, ing6, 340, 170);
        burgers.add(burger6);
        dao.insert(burgers);

        List<Burger> res = dao.findAll();
        System.out.print(res);
	}

}
