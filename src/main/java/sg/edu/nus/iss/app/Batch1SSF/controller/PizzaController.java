package sg.edu.nus.iss.app.Batch1SSF.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.app.Batch1SSF.model.Delivery;
import sg.edu.nus.iss.app.Batch1SSF.model.Order;
import sg.edu.nus.iss.app.Batch1SSF.model.Pizza;
import sg.edu.nus.iss.app.Batch1SSF.service.PizzaService;

@Controller
@RequestMapping
public class PizzaController {
    
    @Autowired
    private PizzaService pizzaSvc;

    @GetMapping (path={"/", "/index.html"})
    public String pizzaOrderForm (Model m, HttpSession s) {
        s.invalidate();
        Pizza pizza = new Pizza();

        m.addAttribute("pizza", pizza);

        return "index";
    }

    @PostMapping (path="/pizza")
    public String userOrder (Model m, HttpSession s, @ModelAttribute @Valid Pizza pizza, BindingResult binding) {
        if (binding.hasErrors()) {
            return "index";
        }
        List<ObjectError> errors = pizzaSvc.validatePizzaOrder(pizza);
        if (!errors.isEmpty()) {
            for (ObjectError e : errors)
                binding.addError(e);
            return "index";
        }
        
        s.setAttribute("pizza", pizza);
        m.addAttribute("delivery", new Delivery());
        return "details";
    }

    @PostMapping (path="/pizza/order")
    public String cfmOrder (Model m, HttpSession s, @ModelAttribute @Valid Delivery delivery, BindingResult b) {
        if (b.hasErrors()) {
            return "details";
        }

        Pizza pizza = (Pizza) s.getAttribute("pizza");
        Order o = pizzaSvc.savePizzaOrder(pizza, delivery);
        m.addAttribute("order", o);
        return "order";
    }

    // method to call own controllers
    @GetMapping (path="/pizza/details/{orderId}")
    public String getOrderDetails (Model m, @PathVariable String orderId) {
        Optional<Order> o = pizzaSvc.getOrderDetails(orderId);
        m.addAttribute("order", o.get());
        return "order";
    }
}
