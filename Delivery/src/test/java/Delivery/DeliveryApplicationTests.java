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

        ing.clear();
        Collections.addAll(ing,"Mayo", "Iceberg Lettuce", "Tomato", "BBQ sauce", "Chess", "Bacon", "Pickles", "Onion Rings");
        Burger burger1 = new Burger(UUID.randomUUID(), "The Burger", MeatType.Chicken, Roasting.WellDone, BreadType.WihiteBread, false, ing, 390, 290);
        burgers.add(burger1);

        ing.clear();
        Collections.addAll(ing,"Iceberg Lettuce", "Sauce Salsa", "Secret Sauce", "Chess Viola");
        Burger burger2 = new Burger(UUID.randomUUID(), "Boston", MeatType.Duck, Roasting.WellDone, BreadType.WihiteBread, false, ing, 360, 265);
        burgers.add(burger2);

        ing.clear();
        Collections.addAll(ing,"Berry Sauce", "Iceberg Lettuce", "Chess Dar Blue", "Caramelized Apples");
        Burger burger3 = new Burger(UUID.randomUUID(), "Carolina", MeatType.Duck, Roasting.WellDone, BreadType.WihiteBread, false, ing, 320, 225);
        burgers.add(burger3);

        ing.clear();
        Collections.addAll(ing,"Onion", "Mozzarella", "Bulgarian Pepper", "BBQ sauce", "Iceberg Lettuce", "Tomato", "Eggplant", "Zucchini");
        Burger burger4 = new Burger(UUID.randomUUID(), "Florida", MeatType.Beef, Roasting.Medium, BreadType.BlackBread, false, ing, 360, 165);
        burgers.add(burger4);

        ing.clear();
        Collections.addAll(ing,"Iceberg Lettuce", "Tomato", "Mustard", "Red Onion", "Mayo", "Chess Gauda", "Catchup");
        Burger burger5 = new Burger(UUID.randomUUID(), "Williams", MeatType.Beef, Roasting.MediumWell, BreadType.WihiteBread, true, ing, 310, 160);
        burgers.add(burger5);

        ing.clear();
        Collections.addAll(ing,"Tomato", "Mozzarella", "Iceberg Lettuce", "Sauce Pesto");
        Burger burger6 = new Burger(UUID.randomUUID(), "Kentucky", MeatType.Chicken, Roasting.WellDone, BreadType.WihiteBread, false, ing, 340, 170);
        burgers.add(burger6);
        dao.insert(burgers);

        List<Burger> res = dao.findAll();
        System.out.print(res);
	}

}
