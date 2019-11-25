package jbc.oct21.jindanupajit.jobposting.model.repository;

import jbc.oct21.jindanupajit.jobposting.model.Job;
import jbc.oct21.jindanupajit.jobposting.model.UserDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {

    Iterable<Job> findAllByIdOrderByPostedDateDesc(Iterable<Long> allId);
    Iterable<Long> findIdByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
    Iterable<Long> findIdByAuthor(Iterable<UserDetail> allAuthor);
    Iterable<Job> findAllByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
}
