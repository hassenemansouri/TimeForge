package tn.esprit.project_task.service;

import tn.esprit.project_task.entity.Project;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IService {
    Project createProject(Project project);
    List<Project> getAllProjects();
    void deleteProject(String projet_id);
    Optional<Project> getProjectById(String projet_id);


}
