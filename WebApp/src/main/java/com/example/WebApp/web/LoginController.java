package com.example.WebApp.web;

import com.example.WebApp.entity.Registration;
import com.example.WebApp.repository.RegistrationRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private RegistrationRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model,
                        HttpServletRequest request) {
        // Check if the user exists in the database
        Registration user = userRepository.findByEmail(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Start a session
            HttpSession session = request.getSession(true);
            // Mark session as active
            session.setAttribute("active", true);
            // Store user details in session
            session.setAttribute("email", user.getEmail());
            session.setAttribute("user", user);
            session.setAttribute("firstName", user.getFirstname());
            session.setAttribute("lastName", user.getLastname());
            // Add user details to the model
            model.addAttribute("user", user);
            // Redirect the user to the homepage
            Boolean isSuperUser = user.getSuperUser();
            if (isSuperUser) {
                return "admin/index.html";
            } else {
                return "home.html";
            }

        } else {
            // If user doesn't exist or password doesn't match, show an error message
            model.addAttribute("error", "Invalid email or password. Please try again.");
            // Return to the login page with an error message
            return "login.html";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // Invalidate the session to log the user out
        session.invalidate();
        // Redirect to the login page
        return "index.html";
    }
}
