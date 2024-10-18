package com.example.WebApp.service;

import com.example.WebApp.entity.CartItem;
import com.example.WebApp.entity.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendRegistrationEmail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Registration Confirmation");
        message.setText("Thank you for registering with our bakery. We look forward to serving you!");
        emailSender.send(message);
    }

    public void deleteEmail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Account Deletion Confirmation");
        message.setText("We are are sad to see you go. \n\nBye!!");
        emailSender.send(message);
    }

    public void sendPasswordResetEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("Dear User,\n\n"
                + "You have requested to reset your password. To proceed with the password reset, please use the following token:\n\n"
                + token + "\n\n"
                + "If you did not request this password reset, please ignore this email.\n\n"
                + "Thank you,\nThe Bakery Team");
        emailSender.send(message);
    }

    public void passwordResetSuccessEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Successful");
        message.setText("Dear User,\n\n"
                + "Your password has been changed successfully."
                + "Thank you,\nThe Bakery Team");
        message.setFrom("Gcibelo Bakery");
        emailSender.send(message);
    }

    public void orderDetails(List<CartItem> items, Registration user, double totalPrice) {
        SimpleMailMessage message = new SimpleMailMessage();
        String email = user.getEmail();
        System.out.println(email);
        message.setTo(email);
        message.setSubject("Order Details");
        String text ="";
        for(CartItem cart: items) {
            text += "\n\nCake name: " + cart.getProductName() +
                    "\nSize: " + cart.getSize() +
                    "\nPrice: R"+ cart.getPrice();
        }
        message.setText("Dear "+user.getFirstname() +",\n\n"
                + "Your order has been placed successfully.\n\nHere is the list of cakes you ordered:\n\n"
                + text + "\nThe total price is R" + totalPrice
                + "\n\nYou will receive an email regarding payment methods"
                + "\n\nThank you for shopping with us!!!");

        emailSender.send(message);
    }

    public void orderDetailsCustomer(List<CartItem> items, Registration user, double totalPrice) {
        SimpleMailMessage message = new SimpleMailMessage();
        String email = "gcibelobakery@gmail.com";
        System.out.println(email);
        message.setTo(email);
        message.setSubject("Customer Order Details");
        String text ="";
        for(CartItem cart: items) {
            text += "\n\nCake name: " + cart.getProductName() +
                    "\nSize: " + cart.getSize() +
                    "\nPrice: R"+ cart.getPrice();
        }
        message.setText("The customer "+user.getFirstname() +" "+ user.getLastname() +",\n\n"
                + "Has successfully placed an order.\n\nHere is the list of cakes ordered:\n\n"
                + text + "\nThe total price is R" + totalPrice);

        emailSender.send(message);
    }
}

