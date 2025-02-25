package tn.esprit.project_task.service;

import tn.esprit.project_task.entity.Project;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IService {
    Project addProject(Project project);
    List<Project> findAllProjects();
    void deleteProject(String projet_id);
    Project modifyProject(Project project);
    List<Project> getAllByTitleProject(String title) ;
    Optional<Project> findProjectById(String projet_id);
    List<Project> findProjectsCreatedAfter(LocalDate date);
    boolean existsById(String projectId);
    List<Project> findProjectContainingIgnoreCase(String keyword);


}
