package io.pujaDeshmukh.resumeportal;

import io.pujaDeshmukh.resumeportal.models.Job;
import io.pujaDeshmukh.resumeportal.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    UserProfileRepository userProfileRepository;
    @GetMapping("/")
    public String home(){
        Optional<UserProfile> profile1 = userProfileRepository.findByUserName("Albert");
        profile1.orElseThrow(() -> new RuntimeException("Not found : "));
        UserProfile profile = profile1.get();
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
        profile.getJobs().clear();
        profile.getJobs().add(job1);
        profile.getJobs().add(job2);
        userProfileRepository.save(profile);
        return "profile";
    }
    @GetMapping("/edit")
    public String edit(Model model,Principal principal){
        String userId = principal.getName();
        model.addAttribute("userId", userId);
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: "+userId));
        UserProfile userProfile = userProfileOptional.get();
        model.addAttribute("userProfile", userProfile);
        return "profile-edit";
    }

    @PostMapping("/edit")
    public String postEdit(Principal principal,Model model, @ModelAttribute UserProfile userProfile){
        String userName = principal.getName();
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found :"+userName));
        UserProfile savedUserProfile = userProfileOptional.get();
        userProfile.setId(savedUserProfile.getId());
        userProfile.setUserName(userName);
        userProfileRepository.save(userProfile);
        return "redirect:/view/"+userName;
    }

    @GetMapping("/view/{userId}")
    public String userView(@PathVariable String userId, Model model){
       Optional<UserProfile> userProfileOptional =  userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found : "+userId));

       model.addAttribute("userId",userId);
       UserProfile userProfile = userProfileOptional.get();
       model.addAttribute("userProfile",userProfile);

        System.out.println(userProfile.getJobs());
        return "profile-templates/" + userProfile.getTheme()+"/index";
    }
}
