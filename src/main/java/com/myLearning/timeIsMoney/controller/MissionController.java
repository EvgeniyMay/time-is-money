package com.myLearning.timeIsMoney.controller;

import com.myLearning.timeIsMoney.dto.MissionDTO;
import com.myLearning.timeIsMoney.exception.DurationLessThanZeroException;
import com.myLearning.timeIsMoney.service.ActivityService;
import com.myLearning.timeIsMoney.service.MissionService;
import com.myLearning.timeIsMoney.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String createMission(@ModelAttribute("missionForm") @Valid MissionDTO missionDTO,
                                BindingResult bindingResult,
                                @RequestParam Long userId,
                                Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("activities", activityService.getAll());

        if(bindingResult.hasErrors()) {
            return "mission/createMission";
        }

        try {
            missionService.createMission(userId, missionDTO);
        } catch (DurationLessThanZeroException e) {
            model.addAttribute("missionForm", new MissionDTO());
            //ToDo
            // Localize
            model.addAttribute("durationLessThanZero", "Duration can't be less than zero");

            return "mission/createMission";
        }
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
        missionService.offerMission(userLogin, missionDTO);

        return "redirect:/user";
    }


    @ExceptionHandler(RuntimeException.class)
    public String handleObjectNotFoundException(RuntimeException e) {
        //ToDo
        // Log
        return "error/404";
    }
}
