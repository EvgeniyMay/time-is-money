package com.myLearning.timeIsMoney.controller;

import com.myLearning.timeIsMoney.dto.ActivityDTO;
import com.myLearning.timeIsMoney.exception.ActivityAlreadyExistException;
import com.myLearning.timeIsMoney.exception.ObjectNotFoundException;
import com.myLearning.timeIsMoney.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public String getAllActivitiesPage(Model model) {
        model.addAttribute("activities", activityService.getAll());

        return "activity/allActivities";
    }


    @GetMapping("/add")
    public String getCreateActivityPage(Model model) {
        model.addAttribute("activityForm", new ActivityDTO());

        return "activity/createActivity";
    }

    @PostMapping("/add")
    public String createActivity(@ModelAttribute ActivityDTO activityDTO) {
        activityService.create(activityDTO);

        return "redirect:/activity";
    }


    @GetMapping("/edit/{id}")
    public String getEditActivityPage(Model model, @PathVariable Long id) {
        model.addAttribute("activity", activityService.getById(id));

        return "activity/editActivity";
    }

    @PostMapping("/edit")
    public String editActivity(@ModelAttribute ActivityDTO activityDTO) {
        activityService.update(activityDTO);

        return "redirect:/activity";
    }


    @GetMapping("/delete/{activityId}")
    public String getDeleteActivityPage(Model model, @PathVariable Long activityId) {
        model.addAttribute("activity", activityService.getById(activityId));

        return "activity/deleteActivity";
    }

    @PostMapping("/delete")
    public String deleteActivity(@RequestParam Long activityId) {
        activityService.deleteById(activityId);

        return "redirect:/activity";
    }


    @ExceptionHandler(ObjectNotFoundException.class)
    public String handleObjectNotFoundException(ObjectNotFoundException e) {
        //ToDo
        // Log
        return "error/404";
    }

    @ExceptionHandler(ActivityAlreadyExistException.class)
    public String handleRuntimeException(ActivityAlreadyExistException e,
                                         Model model) {
        model.addAttribute("activities", activityService.getAll());
        model.addAttribute("activityExistMessage", e.getMessage());

        return "activity/allActivities";
    }
}
