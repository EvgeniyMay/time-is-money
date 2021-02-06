package com.myLearning.timeIsMoney.entity;

import com.myLearning.timeIsMoney.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String login;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany (mappedBy="user")
    private List<Mission> missions;
}
