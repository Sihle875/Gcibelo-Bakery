package com.example.WebApp.web;

import com.example.WebApp.entity.PasswordResetToken;
import com.example.WebApp.entity.Registration;
import com.example.WebApp.service.EmailService;
import com.example.WebApp.service.PasswordResetTokenService;
import com.example.WebApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class PasswordResetController {
    @Autowired
    private PasswordResetTokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgotten_password.html";
    }

    @PostMapping("/resetpassword")
    public String showResetPasswordForm(@RequestParam("email") String email, Model model) {
        Registration user = userService.findByEmail(email);
        //System.out.println("The user with email "+ email + "does not exist");
        if (user == null) {
            // User not found
            System.out.println("The user with email "+ email + " does not exist");
            return "redirect:/forgot-password?error=userNotFound";
        }
        // Assuming you have a method to generate a password reset token
        PasswordResetToken token = tokenService.createPasswordResetToken(user);
        System.out.println("Your token is: " + token.getToken());
        emailService.sendPasswordResetEmail(user.getEmail(), token.getToken());
        // Add the model object to the view
        model.addAttribute("token", token);
        // Redirect to reset_password.html*/
        return "reset_password.html";
    }


    @PostMapping("/reset-password-simple")
    public String processResetPassword(@RequestParam("token") String token,
                                       @RequestParam("password") String password,
                                       @RequestParam(value = "confirmPassword", required = false) String confirmPassword) {
        PasswordResetToken resetToken = tokenService.findByToken(token);
        if (resetToken == null || resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            // Token is invalid or expired
            System.out.println("Token is invalid or expired");
            return "redirect:/forgot-password?error";
        }
        Registration user = resetToken.getUser();
        System.out.println(user);
        if (confirmPassword != null && !password.equals(confirmPassword)) {
            // Passwords don't match
            return "redirect:/reset-password?token=" + token + "&error=passwordMismatch";
        }
        user.setPassword(password);
        userService.save(user);
        System.out.println("Success");
        // Delete the token from the database after resetting the password
        tokenService.delete(resetToken);
        System.out.println("token deleted");
        emailService.passwordResetSuccessEmail(user.getEmail());
        return "index.html";
    }

}
