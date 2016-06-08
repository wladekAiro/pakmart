package com.wladek.pakmart.web;

import com.wladek.pakmart.domain.User;
import com.wladek.pakmart.domain.enumeration.UserRole;
import com.wladek.pakmart.service.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wladek on 7/6/15.
 */
@Controller
public class PageDirectorController {

    @RequestMapping("/url_processor")
    public String redirect(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userDetails.getUser();

        if (user.getUserRole().equals(UserRole.ADMIN)){
            return "redirect:/admin/home" ;
        }

        if (user.getUserRole().equals(UserRole.USER)){
            return "redirect:/administrator/manage/home" ;
        }

        return "redirect:/";

    }
}
