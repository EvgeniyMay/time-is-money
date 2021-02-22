package com.myLearning.timeIsMoney.controller;

import com.myLearning.timeIsMoney.dto.ActivityDTO;
import com.myLearning.timeIsMoney.dto.MissionDTO;
import com.myLearning.timeIsMoney.entity.UserDetailsImpl;
import com.myLearning.timeIsMoney.enums.MissionState;
import com.myLearning.timeIsMoney.enums.Role;
import com.myLearning.timeIsMoney.exception.DurationLessThanZeroException;
import com.myLearning.timeIsMoney.service.ActivityService;
import com.myLearning.timeIsMoney.service.MissionService;
import com.myLearning.timeIsMoney.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

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

    @GetMapping("/active")
    public String getActiveMissionsPage(Model model,
                                        @PageableDefault(sort = {"id"}, size = 7)
                                        Pageable pageable) {
        model.addAttribute("missionsPage", missionService.getPageableByState(MissionState.ACTIVE, pageable));

        return "mission/activeMission";
    }
    @GetMapping("/completed")
    public String getCompletedMissionsPage(Model model,
                                        @PageableDefault(sort = {"id"}, size = 7)
                                        Pageable pageable) {
        model.addAttribute("missionsPage", missionService.getPageableByState(MissionState.COMPLETED, pageable));

        return "mission/completedMission";
    }
    @GetMapping("/passed")
    public String getPassedMissionsPage(Model model,
                                        @PageableDefault(sort = {"id"}, size = 7)
                                        Pageable pageable) {
        model.addAttribute("missionsPage", missionService.getPageableByState(MissionState.PASSED, pageable));

        return "mission/passedMission";
    }
    @GetMapping("/offered")
    public String getOfferedMissionsPage(Model model,
                                        @PageableDefault(sort = {"id"}, size = 7)
                                        Pageable pageable) {
        model.addAttribute("missionsPage", missionService.getPageableByState(MissionState.OFFERED, pageable));

        return "mission/offeredMission";
    }

    @GetMapping("/create")
    public String getCreateMissionPage(Model model) {
        model.addAttribute("missionForm", new MissionDTO());
        model.addAttribute("usersAndActivities", missionService.getUsersAndActivities());

        return "mission/createMission";
    }
    @PostMapping("/create")
    public String createMission(@ModelAttribute("missionForm") @Valid MissionDTO missionDTO,
                                BindingResult bindingResult,
                                Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("usersAndActivities", missionService.getUsersAndActivities());
            return "mission/createMission";
        }

        try {
            missionService.createMission(missionDTO, MissionState.ACTIVE);
        } catch (DurationLessThanZeroException e) {
            model.addAttribute("missionForm", new MissionDTO());
            model.addAttribute("usersAndActivities", missionService.getUsersAndActivities());
            model.addAttribute("error", "Duration can't be less than zero");

            return "mission/createMission";
        }
        return "redirect:/mission/active";
    }

    @GetMapping("/offer")
    public String getOfferMissionPage(Model model) {
        model.addAttribute("activities", activityService.getActive());
        model.addAttribute("missionForm", new MissionDTO());

        return "mission/offerMission";
    }
    @PostMapping("/offer")
    public String offerMission(@ModelAttribute("missionForm") @Valid MissionDTO missionDTO,
                               BindingResult bindingResult,
                               Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("activities", activityService.getActive());

            return "mission/offerMission";
        }

        UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        missionDTO.setUserId(userDetails.getId());
        try {
            missionService.createMission(missionDTO, MissionState.OFFERED);
        } catch (DurationLessThanZeroException e) {
            model.addAttribute("activities", activityService.getActive());
            model.addAttribute("missionForm", new MissionDTO());
            model.addAttribute("error", "Duration can't be less than zero");

            return "mission/offerMission";
        }

        return "redirect:/user/profile";
    }

    @PostMapping("/pass")
    public String passMission(@RequestParam String missionId){
        missionService.passMissionById(Long.parseLong(missionId));

        return "redirect:/user/profile";
    }

    @PostMapping("/cancel")
    public String cancelMission(@RequestParam String missionId) {
        missionService.deleteById(Long.parseLong(missionId));

        return "redirect:/user/profile";
    }

    @PostMapping("/delete")
    public String deleteMission(@RequestParam String missionId) {
        missionService.deleteById(Long.parseLong(missionId));

        return "redirect:/mission/offered";
    }

    @PostMapping("/accept")
    public String acceptMission(@RequestParam String missionId) {
        missionService.acceptById(Long.parseLong(missionId));

        return "redirect:/mission/offered";
    }


}
