package com.example.sweater.service;

import com.example.sweater.model.Role;
import com.example.sweater.model.User;
import com.example.sweater.repository.UserRepository;
import freemarker.template.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailService mailService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }

    public boolean activateUser(User user){
        user.setActivationHash(null);
        user.setActive(true);
        userRepository.save(user);
        return true;
    }

    public boolean sendConfirmation(User user){
        if(StringUtils.isEmpty(user.getEmail())){
            return false;
        }
        String hash = UUID.randomUUID().toString();
        user.setActivationHash(hash);
        userRepository.save(user);

        String body = String.format("Hello, %s. \n" +
                "Follow the <a href='http:localhost:8080/activate/%s'>link</a> to activate",
                user.getUsername(),
                hash
        );
        return mailService.send(user.getEmail(), "Activate", body);
    }
    public boolean addUser(User user){
        User temp = userRepository.findByUsername(user.getUsername());
        if(temp != null){
            return false;
        }

        user.setActive(false);
        Set<Role> a = new HashSet<Role>();
        a.add(Role.USER);
        user.setRoles(a);
//        should be the same:
//        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return true;
    }
}
