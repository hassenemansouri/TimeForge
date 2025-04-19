package tn.esprit.workspace_workflow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.workspace_workflow.entity.Calendar;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CalendarRepository extends MongoRepository<Calendar, String> {
    List<Calendar> findByMonth(LocalDate month);

}
