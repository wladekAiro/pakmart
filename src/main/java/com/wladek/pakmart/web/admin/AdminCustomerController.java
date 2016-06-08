package com.wladek.pakmart.web.admin;

import com.wladek.pakmart.domain.BuyingPointCost;
import com.wladek.pakmart.domain.Customer;
import com.wladek.pakmart.domain.SellingPointCost;
import com.wladek.pakmart.domain.enumeration.PointCostStatus;
import com.wladek.pakmart.service.CustomerService;
import com.wladek.pakmart.service.PointsService;
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
    @Autowired
    PointsService pointsService;

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

    @RequestMapping(value = "/points", method = RequestMethod.GET)
    public String points( Model model) {

        BuyingPointCost setBuyingCost = pointsService.getActiveBuyingPointCost(PointCostStatus.ACTIVE);
        SellingPointCost setSellingCost = pointsService.getActiveSellingPointCost(PointCostStatus.ACTIVE);

        model.addAttribute("buyingPointCost" , new BuyingPointCost());
        model.addAttribute("sellingPointCost" , new SellingPointCost());
        model.addAttribute("setBuyingCost" , setBuyingCost);
        model.addAttribute("setSellingCost" , setSellingCost);

        return "/customer/points";
    }

    @RequestMapping(value = "/setBuying", method = RequestMethod.POST)
    public String setBuyingCost(@Valid @ModelAttribute("buyingPointCost") BuyingPointCost buyingPointCost,BindingResult result,
                                RedirectAttributes redirectAttributes , Model model) {

        if (result.hasErrors()){
            BuyingPointCost setBuyingCost = pointsService.getActiveBuyingPointCost(PointCostStatus.ACTIVE);
            SellingPointCost setSellingCost = pointsService.getActiveSellingPointCost(PointCostStatus.ACTIVE);

            model.addAttribute("buyingPointCost" , new BuyingPointCost());
            model.addAttribute("sellingPointCost" , new SellingPointCost());
            model.addAttribute("setBuyingCost" , setBuyingCost);
            model.addAttribute("setSellingCost" , setSellingCost);

            model.addAttribute("message" , true);
            model.addAttribute("content" , " Buying points form has errors, click on Buying cost button to view");

            return "/customer/points";
        }

        pointsService.setBuyingCost(buyingPointCost);

        redirectAttributes.addFlashAttribute("message" , true);
        redirectAttributes.addFlashAttribute("content" , "Buying cost set succesfully");

        return "redirect:/administrator/manage/points";
    }

    @RequestMapping(value = "/setSelling", method = RequestMethod.POST)
    public String setSellingCost(@Valid @ModelAttribute("sellingPointCost") SellingPointCost sellingPointCost,BindingResult result,
                                RedirectAttributes redirectAttributes , Model model) {

        if (result.hasErrors()){
            BuyingPointCost setBuyingCost = pointsService.getActiveBuyingPointCost(PointCostStatus.ACTIVE);
            SellingPointCost setSellingCost = pointsService.getActiveSellingPointCost(PointCostStatus.ACTIVE);

            model.addAttribute("buyingPointCost" , new BuyingPointCost());
            model.addAttribute("sellingPointCost" , sellingPointCost);
            model.addAttribute("setBuyingCost" , setBuyingCost);
            model.addAttribute("setSellingCost" , setSellingCost);

            model.addAttribute("message" , true);
            model.addAttribute("content" , " Selling points form has errors, click on Selling cost button to view");

            return "/customer/points";
        }

        pointsService.setSellingCost(sellingPointCost);

        redirectAttributes.addFlashAttribute("message" , true);
        redirectAttributes.addFlashAttribute("content" , "Selling cost set succesfully");

        return "redirect:/administrator/manage/points";
    }
}
