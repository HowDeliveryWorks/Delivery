package Delivery;

import Delivery.model.*;
import Delivery.services.ApplicationMailer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

/**
 * Created by LevelNone on 21.04.2017.
 */
public class ApplicationMailerTest {
    @Test
    public void test()
    {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DeliveryApplication.class);
        ApplicationMailer mailer = (ApplicationMailer) ctx.getBean("mailService");
        Customer vasya = new Customer(UUID.randomUUID(), "vasya", "howdeliveryworks@gmail.com");
        ArrayList<String> ingredients = new ArrayList<>();
        Collections.addAll(ingredients, "Mayo", "Iceberg Lettuce", "Red Onion", "Chess", "Tomato", "Pickles", "BBQ sauce");
        Burger burger = new Burger(UUID.randomUUID(), "New York", MeatType.Beef, Roasting.Medium, BreadType.WihiteBread, false, ingredients, 400, 210, "burger-1.png");
        ArrayList<String> ing2 = new ArrayList<>();
        Collections.addAll(ing2, "Iceberg Lettuce", "Sauce Salsa", "Secret Sauce", "Chess Viola");
        Burger burger2 = new Burger(UUID.randomUUID(), "Boston", MeatType.Duck, Roasting.WellDone, BreadType.WihiteBread, true, ing2, 360, 265, "burger-5.png");
        ArrayList<Burger> burgers = new ArrayList<>();
        Collections.addAll(burgers, burger, burger2);
        Order order = new Order(0, vasya, "+380670000000","Kek Street 1", OrderPaymentMethod.Cash,"", burgers);
        String text = "Dear " + order.getCustomer().getName()
                + ", thank you for placing order. Your order number is "
                + order.getNumber()
                + " with the order price of "
                + order.getOrderPrice();
        mailer.sendMail("howdeliveryworks@gmail.com", "Test Subject", text);
        /*text = "We have a new order. Order info:  /n"
                + order.toString();
        mailer.sendMail("howdeliveryworks@gmail.com", "Test Subject", text);*/
        //mailer.sendPreConfiguredMail("Exception occurred everywhere.. where are you ????");
    }

}
