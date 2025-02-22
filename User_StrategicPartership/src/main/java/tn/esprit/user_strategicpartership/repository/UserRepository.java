package tn.esprit.user_strategicpartership.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.user_strategicpartership.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByWorkspaceId(String workspaceId);
    List<User> findAllByCollaborationId(String collaboration_id);
    List<User> findAllByGoalId(String goal_id);

}
