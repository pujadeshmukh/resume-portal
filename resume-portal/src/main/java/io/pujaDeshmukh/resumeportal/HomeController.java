package io.pujaDeshmukh.resumeportal;

import io.pujaDeshmukh.resumeportal.models.MyUserDetails;
import io.pujaDeshmukh.resumeportal.models.USERINFO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(HttpServletRequest req){
        HttpSession session = req.getSession();
        String name = req.getParameter("name");
        session.setAttribute("name",name);
        return "index";
    }
    @GetMapping("/edit")
    public String edit(){
        return "edit page";
    }

    @GetMapping("/view/{userId}")
    public String userView(@PathVariable String userId, Model model){
       model.addAttribute("userId",userId);
        return "profile-templates/2/index";
    }
}
