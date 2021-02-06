package com.myLearning.timeIsMoney.entity;

import com.myLearning.timeIsMoney.enums.MissionState;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (optional=false)
    @JoinColumn (name="user_id")
    private User user;

    @ManyToOne (optional=false)
    @JoinColumn (name="activity_id")
    private Activity activity;

    @Enumerated(value = EnumType.STRING)
    private MissionState state;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}
