package io.pujaDeshmukh.resumeportal;

import io.pujaDeshmukh.resumeportal.models.MyUserDetails;
import io.pujaDeshmukh.resumeportal.models.USERINFO;
import io.pujaDeshmukh.resumeportal.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    UserProfileRepository userProfileRepository;
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
       Optional<UserProfile> userProfileOptional =  userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found : "+userId));

       model.addAttribute("userId",userId);
       UserProfile userProfile = userProfileOptional.get();
       model.addAttribute("userProfile",userProfile);

        return "profile-templates/" + userProfile.getId()+"/index";
    }
}
