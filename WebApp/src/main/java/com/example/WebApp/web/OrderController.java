package com.example.WebApp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    /*@Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public String getOrderDetails(Model model) {
        List<OrderDetailsDTO> orderDetails = orderService.getOrderDetails();
        model.addAttribute("orderDetails", orderDetails);
        return "order-details";
    }*/
}
