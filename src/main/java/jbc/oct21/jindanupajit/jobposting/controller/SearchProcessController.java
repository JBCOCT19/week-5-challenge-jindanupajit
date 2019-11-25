package jbc.oct21.jindanupajit.jobposting.controller;

import jbc.oct21.jindanupajit.jobposting.model.Job;
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
//        HashSet<Long> jobIdCollection = new HashSet<>();
//
//        List<String> keywords = Arrays.asList(query.split(" "));
//
//
//        // Search and add each record to jobIdCollection
//        for (String eachKeyword : keywords) {
//            searchTitleAndDescription(eachKeyword).forEach(jobIdCollection::add);
//        }
//
//
//        // Now get all the objects with those ids
//        Iterable<Job> jobCollection = jobRepository.findAllByIdOrderByPostedDateDesc(jobIdCollection);

        Iterable<Job> jobCollection = jobRepository.findAllByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query,query);
        model.addAttribute("jobCollection", jobCollection);
        model.addAttribute("mode", "view");

        return "view";
    }

    private Iterable<Long> searchTitleAndDescription(String query) {
        /*
         * Get only the job.id not the whole object
         * Note the findIdby... not findAllBy (findBy)
         */
        return jobRepository.findIdByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
    }

    private Iterable<Long> searchAuthor(String query) {
        /*
         * Get only the job.id not the whole object
         * Note the findIdby... not findAllBy (findBy)
         */
        return userDetailRepository.findIdByUsernameContainingIgnoreCaseOrDisplayNameContainingIgnoreCase(query, query);
    }
}
