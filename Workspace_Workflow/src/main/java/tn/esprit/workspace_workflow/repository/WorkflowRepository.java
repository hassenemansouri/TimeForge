package tn.esprit.workspace_workflow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.workspace_workflow.entity.Workflow;

@Repository
public interface WorkflowRepository extends MongoRepository<Workflow, String> {
}
