package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }
    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String results(Model model,@RequestParam String searchTerm, @RequestParam String searchType){
        ArrayList<HashMap<String,String>> jobs;
        if (searchType.equals("all")){
            jobs = JobData.findByValue(searchTerm);
        } else {
            jobs = JobData.findByColumnAndValue(searchType,searchTerm);
        }
        String results = jobs.size() + " Result(s)";
        model.addAttribute("columns",ListController.columnChoices); //radio buttons
        model.addAttribute("jobs",jobs);
        model.addAttribute("results",results);
        return "search";
    }
}