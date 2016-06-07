package com.wladek.pakmart.web.admin;

import com.wladek.pakmart.domain.School;
import com.wladek.pakmart.domain.User;
import com.wladek.pakmart.domain.enumeration.UserRole;
import com.wladek.pakmart.domain.enumeration.UserState;
import com.wladek.pakmart.service.SchoolService;
import com.wladek.pakmart.service.UserService;
import com.wladek.pakmart.web.front.support.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by wladek on 5/19/16.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminRootController {
    @Autowired
    UserValidator userValidator;

    @Autowired
    UserService userService;

    @Autowired
    SchoolService schoolService;

    @RequestMapping(value = "/home" , method = RequestMethod.GET)
    public String adminRoot(){

        return "/admin/index";
    }

    @RequestMapping(value = "/users" , method = RequestMethod.GET)
    public String listUsers(Model model){

        List<User> users = userService.findByRole(UserRole.USER);

        model.addAttribute("users" , users);
        model.addAttribute("user" , new User());
        model.addAttribute("view" , false);

        return "/admin/users/index";
    }

    @RequestMapping(value = "/users/createUser" , method = RequestMethod.POST)
    public String postUser(@ModelAttribute @Valid User user, BindingResult result ,
                           RedirectAttributes redirectAttributes , Model model){

        userValidator.validateNewUser(user, result);

        if(result.hasErrors()) {

            List<User> users = userService.findByRole(UserRole.USER);

            model.addAttribute("message" , true);
            model.addAttribute("content" , "Form has errors");
            model.addAttribute("users" , users);
            model.addAttribute("user" , user);

            return "/admin/users/index";
        }

        user.setPassword("pass");
        user.setUserRole(UserRole.USER);
        user.setUserState(UserState.IN_ACTIVE);
        User newUser = userService.addNewUser(user);

        redirectAttributes.addAttribute("message" , true);
        redirectAttributes.addFlashAttribute("content" , newUser.getName() + " added");

        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/school" , method = RequestMethod.GET)
    public String listSchool(@RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                             @RequestParam(value = "size" , required = false , defaultValue = "10") int size,Model model){

        Page<School> schoolPage = schoolService.getAll(page, size);

        model.addAttribute("schoolPage" , schoolPage);
        model.addAttribute("school" , new School());
        model.addAttribute("pagenatedUrl" , "/admin/schools");

        return "/admin/users/schools";
    }

    @RequestMapping(value = "/school/createSchool" , method = RequestMethod.POST)
    public String postSchool(@ModelAttribute @Valid School school , BindingResult result, RedirectAttributes redirectAttributes,
                             @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                             @RequestParam(value = "size" , required = false , defaultValue = "10") int size,Model model){

        if (result.hasErrors()){

            Page<School> schoolPage = schoolService.getAll(page, size);

            model.addAttribute("schoolPage" , schoolPage);
            model.addAttribute("school" , school);
            model.addAttribute("message" , true);
            model.addAttribute("content" , "Form has errors, Click add school button to view");
            model.addAttribute("pagenatedUrl" , "/admin/schools");

            return "/admin/users/schools";
        }

        schoolService.create(school);
        redirectAttributes.addFlashAttribute("message" , true);
        redirectAttributes.addFlashAttribute("content" , " School created ");
        return "redirect:/admin/school";
    }


    @RequestMapping(value = "/user/{id}/{view}" , method = RequestMethod.GET)
    public String viewUser(@PathVariable("id") Long id ,@PathVariable("view") boolean view, Model model){

        User user = userService.findById(id);

        List<School> schools = schoolService.getAll();

        List<User> users = userService.findByRole(UserRole.USER);

        model.addAttribute("users" , users);
        model.addAttribute("schools" , schools);
        model.addAttribute("user" , user);
        model.addAttribute("view" , view);

        return "/admin/users/index";
    }

    @RequestMapping(value = "/users/setschool" , method = RequestMethod.POST)
    public String setSchool(@ModelAttribute("user") User user, Model model){

        userService.setSchool(user);

        return "redirect:/admin/user/"+user.getId()+"/"+true;
    }

    @RequestMapping(value = "/activate/{id}" , method = RequestMethod.GET)
    public String activateUser(@PathVariable("id") Long id , RedirectAttributes redirectAttributes){
        User user = userService.activateUser(id);

        redirectAttributes.addFlashAttribute("message" , true);
        redirectAttributes.addFlashAttribute("content" , user.getName()+" activated");

        return "redirect:/admin/user/"+user.getId()+"/"+true;
    }

    @RequestMapping(value = "/deactivate/{id}" , method = RequestMethod.GET)
    public String deActivateUser(@PathVariable("id") Long id , RedirectAttributes redirectAttributes){
        User user = userService.deActivateUser(id);

        redirectAttributes.addFlashAttribute("message" , true);
        redirectAttributes.addFlashAttribute("content" , user.getName()+" de-activated");

        return "redirect:/admin/user/"+user.getId()+"/"+true;
    }
}
