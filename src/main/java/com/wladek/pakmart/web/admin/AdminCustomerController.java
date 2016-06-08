package com.wladek.pakmart.web.admin;

import com.wladek.pakmart.domain.Customer;
import com.wladek.pakmart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by wladek on 6/8/16.
 */
@Controller
@RequestMapping(value = "/administrator/manage")
public class AdminCustomerController {
    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String index(Model model) {
        return "/customer/index";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String customers(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(value = "size", required = false, defaultValue = "10") int size, Model model) {
        Page<Customer> customerPage = customerService.findAll(page, size);

        model.addAttribute("customerPage", customerPage);
        model.addAttribute("customer", new Customer());
        model.addAttribute("pagenatedUrl", "/admin/manage/customers");

        return "/customer/customers";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public String postCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result,
                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "size", required = false, defaultValue = "10") int size, Model model,
                               RedirectAttributes redirectAttributes) {

        if (result.hasErrors()){

            Page<Customer> customerPage = customerService.findAll(page, size);

            model.addAttribute("message" , true);
            model.addAttribute("content" , "Form has errors");
            model.addAttribute("customer", customer);
            model.addAttribute("customerPage", customerPage);
            model.addAttribute("pagenatedUrl", "/admin/manage/customers");

            return "/customer/customers";
        }

        Customer newCustomer = customerService.create(customer);

        redirectAttributes.addFlashAttribute("message" , true);
        redirectAttributes.addFlashAttribute("content" , "Customer registered succesfully");

        return "redirect:/administrator/manage/customers";
    }
}
