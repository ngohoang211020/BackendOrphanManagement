package com.orphan.database;

import com.orphan.common.entity.Role;
import com.orphan.common.service.UserService;
import com.orphan.enums.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class RoleDatabase {
    @Autowired
    private UserService userService;

    @Bean
    CommandLineRunner initDatabase(){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Role roleAdmin  = new Role(1, ERole.ROLE_ADMIN);
                Role roleMan=new Role(2, ERole.ROLE_MANAGER);

                Set<Role> list=new HashSet<>();
                list.add(roleAdmin); list.add(roleMan);
                userService.saveAllRole(list);
            }
        };
    }
}
