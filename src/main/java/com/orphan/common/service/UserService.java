package com.orphan.common.service;

import com.orphan.common.entity.Role;
import com.orphan.common.entity.User;
import com.orphan.common.repository.RoleRepository;
import com.orphan.common.repository.UserRepository;
import com.orphan.enums.ERole;
import com.orphan.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public User save(User user) {
        boolean isUpdatingUser = (user.getId() != null);
        if (isUpdatingUser) {
            User existingUser = userRepository.findById(user.getId()).get();
            if (user.getPassword().isEmpty() && user.getPassword()==null) {
                user.setPassword(existingUser.getPassword());
            } else {
                encodePassword(user);
            }
            if (user.getImage()==null && user.getImage().isEmpty()) {
                user.setImage(existingUser.getImage());
            }
        }
        return userRepository.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        try {
            return userRepository.findById(id).get();
        } catch (Exception e) {
            throw new UserNotFoundException("Không tìm thấy bất kỳ tài khoản nào có Id = " + id);
        }
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public boolean existsByIdentification(String identification){
        return userRepository.existsByIdentification(identification);
    }

    public Role findByName(ERole roleName){
        return roleRepository.findByName(roleName);
    }

    public void saveRole(Role role){
        roleRepository.save(role);
    }

    public void saveAllRole(Set<Role> list) {
        roleRepository.saveAll(list);
    }


}
