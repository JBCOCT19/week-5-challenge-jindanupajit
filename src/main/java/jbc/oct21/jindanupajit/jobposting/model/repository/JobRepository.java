package jbc.oct21.jindanupajit.jobposting.model.repository;

import jbc.oct21.jindanupajit.jobposting.model.Job;
import jbc.oct21.jindanupajit.jobposting.model.UserDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {

    Iterable<Job> findAllByIdOrderByPostedDateDesc(Iterable<Long> iterable);
    Iterable<Job> findAllByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
    Iterable<Job> findAllByAuthor(UserDetail author);

}
