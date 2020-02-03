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

import java.util.*;
import java.util.stream.Collectors;

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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        for(String key : form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        user.setUsername(username);
        userRepository.save(user);
    }

    public void updateProfile(User user, String email, String password) {
        if(user.getEmail() != email) {
            user.setEmail(email);
        }
        if(!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }
        userRepository.save(user);
    }
}
