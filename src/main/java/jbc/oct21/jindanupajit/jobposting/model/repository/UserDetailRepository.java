package jbc.oct21.jindanupajit.jobposting.model.repository;

import jbc.oct21.jindanupajit.jobposting.model.UserDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends CrudRepository<UserDetail, Long> {
    UserDetail findByUsername(String username);
    Iterable<UserDetail> findAllByUsernameContainingIgnoreCaseOrDisplayNameContainingIgnoreCase(String username, String displayName);
}
