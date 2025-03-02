package tn.esprit.user_strategicpartership.service;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.user_strategicpartership.entity.User;
import tn.esprit.user_strategicpartership.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private  UserRepository userRepository;  // Injection avec @Autowired


    public User addUser(User user) {
      return   userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<User> findUsersByWorkspace(String workspaceId) {
        return userRepository.findAllByWorkspaceId(workspaceId);
    }

    public List<User> findUsersByGoal(String goalId) {
        return userRepository.findAllByGoalId(goalId);
    }




    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }


    public List<User> findUsersCollab(String collaborationId) {
        return userRepository.findAllByCollaborationId (collaborationId);
    }

    public List<User> findUsersByProjects(String projectId) {
        return userRepository.findAllByProjectId(projectId);
    }


}
