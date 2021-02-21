package com.myLearning.timeIsMoney.controller;

import com.myLearning.timeIsMoney.dto.ActivityDTO;
import com.myLearning.timeIsMoney.entity.Activity;
import com.myLearning.timeIsMoney.exception.ActivityAlreadyExistException;
import com.myLearning.timeIsMoney.exception.ObjectNotFoundException;
import com.myLearning.timeIsMoney.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/active")
    public String getActiveActivityPage(Model model,
                                       @PageableDefault(sort = {"name"}, size = 7)
                                       Pageable pageable) {
        model.addAttribute("activitiesPage", activityService.getPageAbleByStatue(true, pageable));

        return "activity/activeActivity";
    }

    @GetMapping("/archived")
    public String getArchivedActivityPage(Model model,
                                       @PageableDefault(sort = {"name"}, size = 7)
                                       Pageable pageable) {
        model.addAttribute("activitiesPage", activityService.getPageAbleByStatue(false, pageable));

        return "activity/activeActivity";
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
    public String editActivity(@ModelAttribute Activity activity) {
        activityService.update(activity);

        return "redirect:/activity";
    }


    @GetMapping("/delete/{activityId}")
    public String getDeleteActivityPage(Model model, @PathVariable Long activityId) {
        model.addAttribute("activity", activityService.getById(activityId));

        return "activity/deleteActivity";
    }




    @ExceptionHandler(ObjectNotFoundException.class)
    public String handleObjectNotFoundException(ObjectNotFoundException e) {
        return "error/404";
    }

    @ExceptionHandler(ActivityAlreadyExistException.class)
    public String handleRuntimeException(ActivityAlreadyExistException e,
                                         Model model,
                                         Pageable pageable) {
        model.addAttribute("activities", activityService.getPageAbleByStatue(true, pageable));
        model.addAttribute("activityExistMessage", e.getMessage());

        return "activity/allActivities";
    }
}
