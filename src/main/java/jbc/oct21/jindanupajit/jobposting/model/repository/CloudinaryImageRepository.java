package jbc.oct21.jindanupajit.jobposting.model.repository;

import jbc.oct21.jindanupajit.jobposting.model.CloudinaryImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudinaryImageRepository extends CrudRepository<CloudinaryImage, Long> {
}
