package tn.esprit.user_strategicpartership.repository;

import java.util.Arrays;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.user_strategicpartership.entity.User;
import org.springframework.data.mongodb.repository.Query;
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

    @Query("{ $or: [ " +
        "{ 'name': { $regex: '^?0', $options: 'i' } }, " +  // Starts with
        "{ 'name': { $regex: '.*?0.*', $options: 'i' } }, " +  // Contains
        "{ 'email': { $regex: '^?0', $options: 'i' } }, " +  // Starts with
        "{ 'email': { $regex: '.*?0.*', $options: 'i' } } " +  // Contains
        "] }")
    List<User> findBySearchQuery(String query);

    List<User> findByIdIn(List<String> ids);

    List<User> findByNameIn(List<String> names);
    Optional<User> findById(String id);
}
