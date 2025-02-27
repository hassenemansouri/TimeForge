package tn.esprit.user_strategicpartership.service;

import tn.esprit.user_strategicpartership.entity.User;

import java.util.List;

public interface IUser {
    void addUser(User user);
    List<User> findAllUsers();
    List<User> findUsersByWorkspace(String workspaceId);
    List<User> findUsersByGoal(String goalId);

    public void deleteUser(String id);

}
