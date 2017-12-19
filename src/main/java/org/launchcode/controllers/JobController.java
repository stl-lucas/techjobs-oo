package org.launchcode.controllers;

import org.launchcode.models.Employer;
import org.launchcode.models.Job;
import org.launchcode.models.JobField;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(@RequestParam int id, Model model) {

        // TODO #1 - get the Job with the given ID and pass it into the view
        model.addAttribute("job", JobData.getInstance().findById(id));
        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addJob(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addJob(@ModelAttribute @Valid JobForm jobForm, Errors errors, Model model) {

        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.

        if (errors.hasErrors()) {
            return "new-job";
        }
        Job newJob = new Job(jobForm.getName(), jobData.getEmployers().findById(jobForm.getEmployerId()), jobData.getLocations().findById(jobForm.getLocationId()), jobData.getPositionTypes().findById(jobForm.getPositionTypeId()), jobData.getCoreCompetencies().findById(jobForm.getCoreCompetencyId()));
        JobData.getInstance().add(newJob);

    return "redirect:?id=" + newJob.getId();

    }
}
