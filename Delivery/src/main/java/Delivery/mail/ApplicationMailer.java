package Delivery.mail;

/**
 * Created by LevelNone on 21.04.2017.
 */
import Delivery.entity.Ingredient;
import Delivery.enums.BurgerType;
import Delivery.enums.Roasting;
import Delivery.model.BurgerInfo;
import Delivery.model.CartLineInfo;
import Delivery.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service("mailService")
public class ApplicationMailer
{
    @Autowired
    private MailSender mailSender;

    @Autowired
    private SimpleMailMessage preConfiguredMessage;

    /**
     * This method will send compose and send the message
     * */
    public void sendMail(String to, String subject, String body)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("<howdeliveryworks@gmail.com>");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        new Thread().start();
        mailSender.send(message);
    }

    public String customerText(Order order)
    {
        StringBuilder sb = new StringBuilder(); // more efficient when u work with StringBuilder rather than String itself
        sb.append("Hey ");
        sb.append(order.getName());
        sb.append("! It seems like you made a new order!");
        sb.append(System.getProperty("line.separator"));
        sb.append("Order's number: ");
        sb.append(order.getId());
        sb.append(System.getProperty("line.separator")); // new line
        sb.append(System.getProperty("line.separator"));
        sb.append("Address: ");
        sb.append(order.getAddress());
        sb.append(System.getProperty("line.separator"));
        sb.append("Phone number: ");
        sb.append(order.getPhone());
        sb.append(System.getProperty("line.separator"));
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String dateText = date.format(formatter);
        sb.append("Date: ");
        sb.append(dateText);
        sb.append(System.getProperty("line.separator"));
        sb.append("Comment:");
        sb.append(System.getProperty("line.separator"));
        sb.append(order.getComment());
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("Your order:");
        sb.append(System.getProperty("line.separator"));
        ApplicationMailer.appendCartLineInfo(sb,order);
        sb.append("For the total of: ");
        sb.append(order.getBurgers().stream().mapToDouble(CartLineInfo::getAmount).sum());
        sb.append(" UAH");
        return sb.toString();
    }

    public String ownerText(Order order)
    {
        StringBuilder sb = new StringBuilder(); // more efficient when u work with StringBuilder rather than String itself
        sb.append("Order's number: ");
        sb.append(order.getId());
        sb.append(System.getProperty("line.separator")); // new line
        sb.append("Address: ");
        sb.append(order.getAddress());
        sb.append(System.getProperty("line.separator"));
        sb.append("Phone number: ");
        sb.append(order.getPhone());
        sb.append(System.getProperty("line.separator"));
        sb.append("Comment:");
        sb.append(System.getProperty("line.separator"));
        sb.append(order.getComment());
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("Order list:");
        sb.append(System.getProperty("line.separator"));
        ApplicationMailer.appendCartLineInfo(sb,order);
        sb.append("For the total of: ");
        sb.append(order.getBurgers().stream().mapToDouble(CartLineInfo::getAmount).sum());
        sb.append(" UAH");

        return sb.toString();
    }

    private static void appendCartLineInfo(StringBuilder sb, Order order)
    {
        for (CartLineInfo cli: order.getBurgers()) {
            BurgerInfo burgerInfo = cli.getBurgerInfo();
            sb.append("x");
            sb.append(cli.getQuantity());
            sb.append(" ");
            sb.append(burgerInfo.getName());
            if(burgerInfo.getBurgerType() == BurgerType.Custom)
            {
                sb.append(":");
                sb.append(System.getProperty("line.separator"));
                if(burgerInfo.getSpicy())
                {
                    sb.append("\t- Spicy");
                    sb.append(System.getProperty("line.separator"));
                }
                sb.append("\t- ");
                sb.append(burgerInfo.getMeat().getName());
                if (burgerInfo.getRoasting() != Roasting.None)
                {
                    sb.append(System.getProperty("line.separator"));
                    sb.append("\t- ");
                    sb.append("Roasting: ");
                    sb.append(burgerInfo.getRoasting().toString());
                }
                sb.append(System.getProperty("line.separator"));
                sb.append("\t- ");
                sb.append(burgerInfo.getBreadType().getName());
                for (Ingredient i : burgerInfo.getIngredients())
                {
                    sb.append(System.getProperty("line.separator"));
                    sb.append("\t- ");
                    sb.append(i.getName());
                }
            }
            sb.append(System.getProperty("line.separator"));
        }
    }

    /**
     * This method will send a pre-configured message
     * */
    public void sendPreConfiguredMail(String message)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
}