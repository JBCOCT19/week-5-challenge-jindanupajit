package jbc.oct21.jindanupajit.jobposting.controller;

import jbc.oct21.jindanupajit.jobposting.model.Job;
import jbc.oct21.jindanupajit.jobposting.model.UserDetail;
import jbc.oct21.jindanupajit.jobposting.model.repository.JobRepository;
import jbc.oct21.jindanupajit.jobposting.model.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class SearchProcessController {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    UserDetailRepository userDetailRepository;

    @GetMapping("/search")
    public String search(Model model, @RequestParam("q") String query) {
        // Contain only interested job.id
        HashSet<Job> jobCollection = new HashSet<>();

        List<String> keywords = Arrays.asList(query.split(" "));

        // Search each scenario and add each record to jobIdCollection
        for (String eachKeyword : keywords) {
            searchTitleAndDescription(eachKeyword).forEach(jobCollection::add);
            searchAuthor(eachKeyword).forEach(jobCollection::add);
        }

        model.addAttribute("jobCollection", jobCollection);
        model.addAttribute("mode", "view");

        return "view";
    }

    private Iterable<Job> searchTitleAndDescription(String query) {

        return jobRepository.findAllByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
    }



    private Iterable<Job> searchAuthor(String query) {

        HashSet<Job> jobCollection = new HashSet<>();
        Iterable<UserDetail> userCollection = userDetailRepository.findAllByUsernameContainingIgnoreCaseOrDisplayNameContainingIgnoreCase(query, query);
        userCollection.forEach((user) -> jobRepository.findAllByAuthor(user).forEach(jobCollection::add));

        return jobCollection;
    }
}
