package com.myLearning.timeIsMoney.controller;

import com.myLearning.timeIsMoney.dto.MissionDTO;
import com.myLearning.timeIsMoney.exception.DurationLessThanZeroException;
import com.myLearning.timeIsMoney.service.ActivityService;
import com.myLearning.timeIsMoney.service.MissionService;
import com.myLearning.timeIsMoney.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public String getAllMissionsPage(Pageable pageable,
                                     Model model) {
        model.addAttribute("missions", missionService.getAllPageable(pageable));

        return "mission/allMissions";
    }

    @GetMapping("/of/{userId}")
    public String getMissionsOfUserPage(@PathVariable Long userId,
                                        Model model) {
        model.addAttribute("user", userService.getById(userId));

        return "/mission/userMissions";
    }




    @GetMapping("/create/{userId}")
    public String getCreateMissionPage(@PathVariable Long userId,
                                       Pageable pageable,
                                       Model model) {
        model.addAttribute("missionForm", new MissionDTO());
        model.addAttribute("userId", userId);
        model.addAttribute("activities", activityService.getAllPageAble(pageable));

        return "mission/createMission";
    }

    @PostMapping("/create")
    public String createMission(@ModelAttribute("missionForm") @Valid MissionDTO missionDTO,
                                BindingResult bindingResult,
                                @RequestParam Long userId,
                                Pageable pageable,
                                Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("activities", activityService.getAllPageAble(pageable));

        if(bindingResult.hasErrors()) {
            return "mission/createMission";
        }

        try {
            missionService.createMission(missionDTO);
        } catch (DurationLessThanZeroException e) {
            model.addAttribute("missionForm", new MissionDTO());
            model.addAttribute("durationLessThanZero", "Duration can't be less than zero");

            return "mission/createMission";
        }
        return "redirect:/user";
    }


    @GetMapping("/offer/{login}")
    @PreAuthorize("authentication.principal.username == #login")
    public String getOfferMissionPage(@PathVariable String login,
                                      @RequestParam Long id,
                                      Pageable pageable,
                                      Model model) {
        model.addAttribute("id", id);
        model.addAttribute("missionForm", new MissionDTO());
        model.addAttribute("activities", activityService.getAllPageAble(pageable));

        return "mission/offerMission";
    }

    @PostMapping("/offer")
    public String offerMission(@RequestParam String userLogin,
                               @ModelAttribute MissionDTO missionDTO,
                               @RequestParam Long id) {

        return "redirect:/user";
    }


    @ExceptionHandler(RuntimeException.class)
    public String handleObjectNotFoundException(RuntimeException e) {
        //ToDo
        // Log
        return "error/404";
    }
}
