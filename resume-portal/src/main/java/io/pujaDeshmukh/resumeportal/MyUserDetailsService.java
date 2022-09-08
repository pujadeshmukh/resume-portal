package io.pujaDeshmukh.resumeportal;

import io.pujaDeshmukh.resumeportal.models.MyUserDetails;
import io.pujaDeshmukh.resumeportal.models.USERINFO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository ;

    @Override
    public  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<USERINFO> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found :"+username));
        return user.map(MyUserDetails:: new).get();
    }

}
