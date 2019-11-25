package jbc.oct21.jindanupajit.jobposting.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Job {

    @Id
    @SequenceGenerator(name = "Job", sequenceName = "JobId", initialValue = 10001, allocationSize = 1)
    @GeneratedValue(generator = "Job")
    private long id;

    private String title;

    @Lob
    private String description;

    private LocalDate postedDate;

    @OneToOne (
            fetch = FetchType.EAGER
    )
    private UserDetail author;

    private String phone;

    @OneToOne (
            fetch = FetchType.LAZY
    )
    private CloudinaryImage cloudinaryImage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDate postedDate) {
        this.postedDate = postedDate;
    }

    public UserDetail getAuthor() {
        return author;
    }

    public void setAuthor(UserDetail author) {
        this.author = author;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CloudinaryImage getCloudinaryImage() {
        return cloudinaryImage;
    }

    public void setCloudinaryImage(CloudinaryImage cloudinaryImage) {
        this.cloudinaryImage = cloudinaryImage;
    }
}
