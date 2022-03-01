package com.orphan.common.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String password;

    @Column(length = 128, nullable = false)
    private String fullName;

    private String phone;

    private String identification;

    @Column(length = 64)
    private String image;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String email, String password, String fullName, String phone, String identification,String image) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.identification = identification;
        this.image=image;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

}
