package tn.esprit.project_task.service;

import tn.esprit.project_task.entity.Project;

import java.util.List;

public interface IService {
    void addProject(Project project);
    List<Project> findAllProjects();
}
