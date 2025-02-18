package tn.esprit.workspace;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public interface WorkspaceRepository extends MongoRepository<Workspace, String> {
    List<Workspace> findByManagerId(String managerId);
}