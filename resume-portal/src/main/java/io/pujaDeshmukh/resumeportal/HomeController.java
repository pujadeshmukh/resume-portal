package io.pujaDeshmukh.resumeportal;

import io.pujaDeshmukh.resumeportal.models.Job;
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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    UserProfileRepository userProfileRepository;
    @GetMapping("/")
    public String home(){
        UserProfile profile = new UserProfile();
        //Job
        Job job1 = new Job();
        job1.setCompany("company 1");
        job1.setDesignation("designation");
        job1.setId(1);
        job1.setStartDate(LocalDate.of(2022,01,01));
        job1.setEndDate(LocalDate.of(2022,12,31));
        Job job2 = new Job();
        job2.setCompany("company 2");
        job2.setDesignation("designation 2");
        job2.setId(2);
        job2.setStartDate(LocalDate.of(2021,01,01));
        job2.setEndDate(LocalDate.of(2021,12,31));
        profile.setJobs(Arrays.asList(job1,job2));

        //User
        profile.setId(1);
        profile.setUserName("Albert");
        profile.setDesignation("scientist");
        profile.setFirstName("Albert");
        profile.setLastName("Einstein");
        profile.setTheme(1);

        userProfileRepository.save(profile);
        return "profile";
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

        System.out.println(userProfile.getJobs());
        return "profile-templates/" + userProfile.getId()+"/index";
    }
}
