package com.myLearning.timeIsMoney.controller;

import com.myLearning.timeIsMoney.dto.MissionDTO;
import com.myLearning.timeIsMoney.enums.Role;
import com.myLearning.timeIsMoney.service.ActivityService;
import com.myLearning.timeIsMoney.service.MissionService;
import com.myLearning.timeIsMoney.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/of/{userId}")
    public String getMissionsOfUserPage(@PathVariable Long userId,
                                        Model model) {
        model.addAttribute("user", userService.getById(userId));

        return "/mission/userMissions";
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

    @GetMapping("/create/{userId}")
    public String getCreateMissionPage(@PathVariable Long userId,
                                       Model model) {
        model.addAttribute("missionForm", new MissionDTO());
        model.addAttribute("userId", userId);
        model.addAttribute("activities", activityService.getAll());

        return "mission/createMission";
    }

    @PostMapping("/create")
    public String createMission(@RequestParam Long userId,
                                @ModelAttribute MissionDTO missionDTO) {
        missionService.createMission(userId, missionDTO, Role.ADMIN);

        return "redirect:/user";
    }

    @GetMapping("/offer/{login}")
    @PreAuthorize("authentication.principal.username == #login")
    public String getOfferMissionPage(@PathVariable String login,
                                      Model model) {
        model.addAttribute("missionForm", new MissionDTO());
        model.addAttribute("activities", activityService.getAll());

        return "mission/offerMission";
    }

    @PostMapping("/offer")
    public String offerMission(@RequestParam String userLogin,
                               @ModelAttribute MissionDTO missionDTO) {
        missionService.offerMission(userLogin, missionDTO, Role.USER);

        return "redirect:/user";
    }
}
