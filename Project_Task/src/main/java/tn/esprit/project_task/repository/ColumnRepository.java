package tn.esprit.project_task.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project_task.entity.Column;

import java.util.List;

@Repository

public interface ColumnRepository extends MongoRepository<Column, String> {

    List<Column> findByBoard(String idBoard);
}
