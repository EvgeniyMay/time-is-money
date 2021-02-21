package com.myLearning.timeIsMoney.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "activity")
    private List<Mission> missions;

    @Column(nullable = false, columnDefinition="tinyint(1) default 0")
    private boolean isArchived;

}
