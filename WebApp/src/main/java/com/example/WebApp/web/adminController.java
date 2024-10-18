package com.example.WebApp.web;

import com.example.WebApp.entity.OrderDetailsDTO;
import com.example.WebApp.entity.Registration;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin") // Prefix for all mapping URLs in this controller
public class adminController {

    @GetMapping("/index")
    public String adminDashboard(Model model, HttpSession session) {
        // Logic to render the admin dashboard page9
        Registration user = (Registration) session.getAttribute("user");
        model.addAttribute("user", user);
        return "admin/index"; // Assuming "admin/index.html" is a Thymeleaf template
    }

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/tables")
    public String adminTables(Model model) {
        String jpql = "SELECT new com.example.WebApp.entity.OrderDetailsDTO(" +
                "   CONCAT(r.firstname, ' ', r.lastname) AS customerName, " +
                "   co.id AS orderId, " +
                "   ci.productName AS productName, " +
                "   CONCAT(ci.size, ' (', co.quantity, ')') AS productSize, " +
                "   co.orderDate AS orderDate, " +
                "   co.deliveryDate AS deliveryDate) " +
                "FROM CustomerOrder co " +
                "JOIN co.customer r " +
                "JOIN co.items ci";

        TypedQuery<OrderDetailsDTO> query = entityManager.createQuery(jpql, OrderDetailsDTO.class);
        List<OrderDetailsDTO> orderDetailsList = query.getResultList();

        model.addAttribute("orderDetailsList", orderDetailsList);

        return "admin/tables";
    }

    @GetMapping("/Unmade_Orders")//Unmade_Orders
    public String Unmade_Orders() {
        // Logic to render the admin tables page
        return "admin/Unmade_Orders"; // Assuming "admin/tables.html" is a Thymeleaf template
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalidate the session
        }
        return "redirect:/"; // Redirect to the home page or another appropriate destination
}
}

