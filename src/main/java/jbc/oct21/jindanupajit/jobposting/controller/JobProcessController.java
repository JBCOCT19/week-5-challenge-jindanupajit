package jbc.oct21.jindanupajit.jobposting.controller;

import jbc.oct21.jindanupajit.jobposting.component.CloudinaryImageUploader;
import jbc.oct21.jindanupajit.jobposting.component.Context;
import jbc.oct21.jindanupajit.jobposting.model.CloudinaryImage;
import jbc.oct21.jindanupajit.jobposting.model.Job;
import jbc.oct21.jindanupajit.jobposting.model.UserDetail;
import jbc.oct21.jindanupajit.jobposting.model.repository.CloudinaryImageRepository;
import jbc.oct21.jindanupajit.jobposting.model.repository.JobRepository;
import jbc.oct21.jindanupajit.jobposting.util.Casting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/job/process")
public class JobProcessController {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    CloudinaryImageRepository cloudinaryImageRepository;

    @Autowired
    CloudinaryImageUploader cloudinaryImageUploader;

    @PostMapping("/edit")
    public String edit(@ModelAttribute Job job, @RequestParam MultipartFile imageFile) {



        UserDetail authenticatedUser = Context.getAuthenticatedUser();

        if (authenticatedUser == null) {
            // Not logged in
            return "redirect:/";
        }

        // TODO: Security Breach!
        /** If advisory inject job.id of someone else's post
         *  with job.author of advisory, it can bypass security check.
         *
         *  Unauthorized database mutation can occur
         *
         *  Must pull job.author from database for validation.
         *  Do not rely on client side data integrity!
         */

        if (    (job.getId() != 0)
                &&(authenticatedUser.getId() != job.getAuthor().getId()) ) {
            // Someone else's post
            return "redirect:/";
        }
        else {
            // Transient object
            job.setAuthor(Context.getAuthenticatedUser());
        }

        // Update the postedDate to now
        job.setPostedDate(LocalDate.now());

        jobRepository.save(job);

        if (!imageFile.isEmpty()) {
            CloudinaryImage cloudinaryImage =
                    cloudinaryImageUploader.upload("JobImage",
                            String.format("job-image-%09d",job.getId()), imageFile);
            if (cloudinaryImage != null) {
                try {
                    cloudinaryImageRepository.save(cloudinaryImage);
                    job.setCloudinaryImage(cloudinaryImage);
                    jobRepository.save(job);
                } catch (Exception e) {
                    System.out.println("*********** JobController *************");
                    System.out.println("User ID "+job.getId());
                    System.out.println("Image ID "+cloudinaryImage.getId());
                    e.printStackTrace();
                    System.out.println("***************************************");
                }
            }
        }


        return "redirect:/job/view/"+job.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String idString) {

        long id = Casting.Long.from(idString);

        UserDetail authenticatedUser = Context.getAuthenticatedUser();

        if (authenticatedUser == null) {
            // Not logged in
            return "redirect:/";
        }

        Optional<Job> jobOptional = jobRepository.findById(id);

        if (!jobOptional.isPresent()) {
            // No such job with 'id'
            return "redirect:/";
        }

        Job job = jobOptional.get();

        if (authenticatedUser.getId() != job.getAuthor().getId()) {
            // Someone else's post
            return "redirect:/";
        }

        jobRepository.deleteById(id);

        return "redirect:/job/view/all";
    }
}
