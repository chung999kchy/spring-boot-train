package com.example.ex9.service;

import com.example.ex9.model.entity.RoleName;
import com.example.ex9.model.entity.impl.User;
import java.util.Set;

/**
 * @author CHUNG
 * @version 1.0
 * @since 10/10/2021 - 18:51
 */
public interface UserService {
    User saveUser(User user);
    boolean exitsByUsername(String username);
    boolean exitsByEmail(String email);
    void addRoleToUser(User user, RoleName roleName);
    void addRolesToUser(User user, Set<String> strRoles);
}
