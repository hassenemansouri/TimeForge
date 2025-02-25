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

    public Project addProject(Project project) {

        return projectRepository.save(project);

    }

    public List<Project> findAllProjects() {

        return projectRepository.findAll();
    }

    public void deleteProject(String projet_id) {
        projectRepository.deleteById(projet_id);

    }
    public Project modifyProject(Project project) {

        return projectRepository.save(project);
    }

    //@Override
    //public List<Project> getAllByTitleProject(String title) {
     //   return projectRepository.findAllByTitleProject(title);
    //}
    //public Optional<Project> findProjectById(String projet_id) {
       // return projectRepository.findById(projet_id);
    //}
    public List<Project> findProjectsCreatedAfter(LocalDate date) {
        return projectRepository.findByCreatedAtAfter(date);
    }
    public boolean existsById(String projectId) {
        return projectRepository.existsById(projectId);
    }

    @Override
    public List<Project> findProjectContainingIgnoreCase(String keyword) {
        return projectRepository.findByTitleContainingIgnoreCase(keyword);
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
