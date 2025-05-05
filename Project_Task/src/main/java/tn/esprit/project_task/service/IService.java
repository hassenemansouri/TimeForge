package tn.esprit.project_task.service;

import tn.esprit.project_task.entity.Board;
import tn.esprit.project_task.entity.Project;
import tn.esprit.project_task.entity.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IService {
    Project createProject(Project project);
    List<Project> getAllProjects();
    void deleteProject(String id);
    Optional<Project> getProjectById(String id);
    Project update( Project project);
    List<Task> getTaskProjects(String id);
    List<Project> getProjectsNotInBoard();
}
