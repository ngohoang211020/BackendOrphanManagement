package com.orphan.config.security.service;

import com.orphan.common.entity.User;
import com.orphan.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userRepository.getByEmail(email);
        if(user!=null) {
            return new UserDetailsImpl(user);
        }
        throw new UsernameNotFoundException("Không tìm thấy bất kỳ tài khoản nào có Email: "+email);
    }
}
