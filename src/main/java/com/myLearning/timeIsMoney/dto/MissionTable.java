package com.myLearning.timeIsMoney.dto;

import com.myLearning.timeIsMoney.entity.Mission;

import java.util.List;
import java.util.stream.Collectors;

public class MissionTable {

    private List<Mission> missions;

    public List<Mission> sortByUser() {
        return missions.stream().sorted((m1, m2) ->
            m1.getUser().getLogin().compareTo(m2.getUser().getLogin())
        ).collect(Collectors.toList());
    }

    public List<Mission> sortByActivity() {
        return missions.stream().sorted((m1, m2) ->
            m1.getActivity().getName().compareTo(m1.getActivity().getName())
        ).collect(Collectors.toList());
    }

    public List<Mission> getMissions() {
        return missions;
    }
    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }
}
