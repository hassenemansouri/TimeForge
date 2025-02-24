package tn.esprit.project_task.service;

import tn.esprit.project_task.entity.Project;

import java.util.List;

public interface IService {
    Project addProject(Project project);
    List<Project> findAllProjects();
    void deleteProject(String projet_id);
    Project modifyProject(Project project);
    List<Project> getAllByTitleProject(String title) ;

}
