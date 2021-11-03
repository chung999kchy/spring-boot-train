package com.example.ex9.service.impl;

import com.example.ex9.model.entity.RoleName;
import com.example.ex9.model.entity.impl.Role;
import com.example.ex9.model.entity.impl.User;
import com.example.ex9.model.jwt.UserPrinciple;
import com.example.ex9.repository.RoleRepository;
import com.example.ex9.repository.UserRepository;
import com.example.ex9.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

/**
 * @author CHUNG
 * @version 1.0
 * @since 10/10/2021 - 18:50
 */

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User saveUser(User user) {
        if(!exitsByEmail(user.getEmail()) && !exitsByUsername(user.getUsername())) {
            return userRepository.save(user);
        }
        throw new IllegalStateException("Cannot sign up!");
    }

    @Override
    public boolean exitsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean exitsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void addRoleToUser(User user, RoleName roleName) {
        Role role = roleRepository.findByName(roleName).orElseThrow(
                () -> new EntityNotFoundException("Fail! -> Cause: User Role not find.")
        );
        user.getRoles().add(role);
    }

    @Override
    public void addRolesToUser(User user, Set<String> strRoles) {
        strRoles.forEach(role -> {
            switch(role) {
                case "admin":
                    addRoleToUser(user, RoleName.ROLE_ADMIN);
                    break;
                case "pm":
                    addRoleToUser(user, RoleName.ROLE_PM);
                    break;
                default:
                    addRoleToUser(user, RoleName.ROLE_USER);
            }
        });
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found with -> username or email : " + username)
                );

        return UserPrinciple.build(user);
    }

}
