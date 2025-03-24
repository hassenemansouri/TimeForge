package tn.esprit.project_task.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.project_task.FullProjectResponse;
import tn.esprit.project_task.client.UserClient;
import tn.esprit.project_task.entity.Project;
import tn.esprit.project_task.repository.ProjectRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectImpl implements IService{

    private ProjectRepository projectRepository;
    private UserClient userClient;
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(String id) {

        return projectRepository.findById(id);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(String id) {
        projectRepository.deleteById(id);
    }
    public Project modifyProject(Project project) {

        return projectRepository.save(project);
    }


    public FullProjectResponse findProjectsWithUsers(String projet_id) {
        var project = projectRepository.findById(projet_id)
                .orElse(Project.builder()
                        .title ( "" )
                        .description ( "" )
                        .build());

        var users = userClient.fundAllUsersByProjects(projet_id); // Corrigé ici

        return FullProjectResponse.builder()
                .title (project.getTitle ())
                .description ( project.getDescription () )
                .users ( users )
                .build ();
    }

}
