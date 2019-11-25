package jbc.oct21.jindanupajit.jobposting.controller;

import jbc.oct21.jindanupajit.jobposting.model.Job;
import jbc.oct21.jindanupajit.jobposting.model.repository.JobRepository;
import jbc.oct21.jindanupajit.jobposting.util.Casting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobRepository jobRepository;


    @GetMapping("/view/all")
    public String viewAll(Model model){
        Iterable<Job> jobCollection = jobRepository.findAll();

        model.addAttribute("jobCollection", jobCollection);
        model.addAttribute("mode", "view");
        return "view";
    }
    @GetMapping("/view/{id}")
    public String viewId(Model model, @PathVariable("id") String idString){
        long id = Casting.Long.from(idString);

        Optional<Job> jobOptional = jobRepository.findById(id);

        if (!jobOptional.isPresent())
            return "redirect:/job/view/all";

        model.addAttribute("job", jobOptional.get());
        model.addAttribute("mode", "view");
        return "view";
    }

    @GetMapping("/view/{id}/editable")
    public String viewIdEditable (Model model, @PathVariable("id") String idString) {
        long id = Casting.Long.from(idString);

        Optional<Job> jobOptional = jobRepository.findById(id);

        model.addAttribute("job", jobOptional.orElse(new Job()));
        model.addAttribute("mode", "edit");
        return "view";
    }

}
