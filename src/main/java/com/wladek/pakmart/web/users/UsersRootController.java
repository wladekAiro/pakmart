package com.wladek.pakmart.web.users;

import com.wladek.pakmart.domain.Item;
import com.wladek.pakmart.domain.School;
import com.wladek.pakmart.domain.Student;
import com.wladek.pakmart.domain.User;
import com.wladek.pakmart.service.ItemService;
import com.wladek.pakmart.service.StudentService;
import com.wladek.pakmart.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
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
 * Created by wladek on 5/19/16.
 */
@Controller
@RequestMapping(value = "/users")
public class UsersRootController {

    @Autowired
    StudentService studentService;

    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/home" , method = RequestMethod.GET)
    public String userRoot(Model model){

        School school = getLogInUser().getSchool();

        model.addAttribute("school" , school);

        return "/users/index";
    }

    @RequestMapping(value = "/school" , method = RequestMethod.GET)
    public String school(Model model){
        return "/users/index";
    }

    @RequestMapping(value = "/student" , method = RequestMethod.GET)
    public String students(@RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                           @RequestParam(value = "size" , required = false , defaultValue = "10") int size,
                           Model model){

        Page<Student> studentPage = studentService.findBySchool(page , size , getLogInUser().getSchool());

        model.addAttribute("student" , new Student());
        model.addAttribute("studentPage" , studentPage);
        model.addAttribute("pagenatedUrl" , "/users/student");

        return "/users/students";
    }

    @RequestMapping(value = "/student/createStudent" , method = RequestMethod.POST)
    public String postStudent(@ModelAttribute @Valid Student student , BindingResult  bindingResult ,
                              @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                              @RequestParam(value = "size" , required = false , defaultValue = "10") int size,
                              RedirectAttributes redirectAttributes, Model model){

        if(bindingResult.hasErrors()){
            Page<Student> studentPage = studentService.findBySchool(page, size, getLogInUser().getSchool());

            model.addAttribute("student" , student);
            model.addAttribute("studentPage" , studentPage);
            model.addAttribute("pagenatedUrl" , "/users/student");
            model.addAttribute("message" , true);
            model.addAttribute("content" , "form has error, click add student to view");

            return "/users/students";
        }

        if (getLogInUser().getSchool() != null){
            student.setSchool(getLogInUser().getSchool());
            studentService.create(student);

            redirectAttributes.addFlashAttribute("message" ,true);
            redirectAttributes.addFlashAttribute("content" , " Student created ");

        }else {
            redirectAttributes.addFlashAttribute("message" ,true);
            redirectAttributes.addFlashAttribute("content" , "Oops!!!, You have not been assigned a school");
        }

        return "redirect:/users/student";
    }

    @RequestMapping(value = "/item" , method = RequestMethod.GET)
    public String listItems(@RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                            @RequestParam(value = "size" , required = false , defaultValue = "10") int size,Model model){

        Page<Item> itemPage = itemService.findBySchool(getLogInUser().getSchool() , page , size);

        model.addAttribute("itemPage" , itemPage);
        model.addAttribute("pagenatedUrl" , "/users/item");
        model.addAttribute("item" , new Item());

        return "/users/items";
    }

    @RequestMapping(value = "/item/createItem" , method = RequestMethod.POST)
    public String postItem(@ModelAttribute @Valid Item item , BindingResult result , RedirectAttributes redirectAttributes,
                           @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                           @RequestParam(value = "size" , required = false , defaultValue = "10") int size,
                           Model model){

        if (result.hasErrors()){
            Page<Item> itemPage = itemService.findBySchool(getLogInUser().getSchool() , page , size);

            model.addAttribute("itemPage" , itemPage);
            model.addAttribute("pagenatedUrl" , "/users/item");
            model.addAttribute("item" , item);

            model.addAttribute("message" , true);
            model.addAttribute("content" , "form has error, click Add Item to view");

            return "/users/items";
        }

        if(getLogInUser().getSchool() == null){

            redirectAttributes.addFlashAttribute("message" , true);
            redirectAttributes.addFlashAttribute("content" , "Oops!!!, you have not been assigned a school, consult admin");

        }else{

            item.setSchool(getLogInUser().getSchool());
            Item newIem = itemService.create(item);

            redirectAttributes.addFlashAttribute("message" , true);
            redirectAttributes.addFlashAttribute("content" , newIem.getName() + " added");
        }


        return "redirect:/users/item";

    }

    private User getLogInUser(){

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userDetails.getUser();
    }
}
