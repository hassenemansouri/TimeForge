package tn.esprit.user_strategicpartership.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.user_strategicpartership.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByWorkspaceId(String workspaceId);
    List<User> findAllByCollaborationId(String collaboration_id);
    List<User> findAllByGoalId(String goal_id);
    List<User> findAllByProjectId(String project_id);

    Optional<User> findByName(String name);
    boolean existsByName(String name);
    boolean existsByEmail(String email);


    Optional<User> findByEmail(String email);
    Optional<User> findByResetToken(String resetToken);
}
