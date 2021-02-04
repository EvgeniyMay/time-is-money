package com.myLearning.timeIsMoney.entity;

import com.myLearning.timeIsMoney.enums.MissionState;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public MissionState getState() {
        return state;
    }
    public void setState(MissionState state) {
        this.state = state;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
