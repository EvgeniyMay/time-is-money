package com.myLearning.timeIsMoney.controller;

import com.myLearning.timeIsMoney.dto.MissionDTO;
import com.myLearning.timeIsMoney.service.ActivityService;
import com.myLearning.timeIsMoney.service.MissionService;
import com.myLearning.timeIsMoney.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mission")
public class MissionController {

    private final MissionService missionService;
    private final UserService userService;
    private final ActivityService activityService;

    @Autowired
    public MissionController(MissionService missionService, UserService userService, ActivityService activityService) {
        this.missionService = missionService;
        this.userService = userService;
        this.activityService = activityService;
    }

    @GetMapping
    public String getAllMissionsPage(Model model) {
        model.addAttribute("missions", missionService.getAll());

        return "mission/allMissions";
    }

    @PostMapping("/pass")
    public String passMission(@RequestParam Long missionId){
        missionService.passMission(missionId);

        return "redirect:/mission";
    }

    @PostMapping("/complete")
    public String completeMission(@RequestParam Long missionId){
        missionService.completeMission(missionId);

        return "redirect:/mission";
    }

    @GetMapping("?={sortSystem}")
    public String getAllMissionsSortedPage(Model model, @PathVariable String sortSystem) {

        model.addAttribute("missions", missionService.getAll());

        return "mission/allMissions";
    }

    @GetMapping("/create/{userId}")
    public String getCreateMissionPage(@PathVariable Long userId, Model model) {
        model.addAttribute("missionForm", new MissionDTO());
        model.addAttribute("userId", userId);
        model.addAttribute("activities", activityService.getAll());

        return "mission/createMission";
    }

    @PostMapping("/create")
    public String postCreateMissionPage(@RequestParam Long userId,
                                        @ModelAttribute MissionDTO missionDTO) {
        missionService.createMission(userId, missionDTO);

        return "redirect:/user";
    }
}
