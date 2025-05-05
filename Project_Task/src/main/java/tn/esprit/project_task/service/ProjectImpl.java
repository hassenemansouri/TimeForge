package tn.esprit.project_task.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.project_task.FullProjectResponse;
import tn.esprit.project_task.client.UserClient;
import tn.esprit.project_task.entity.Board;
import tn.esprit.project_task.entity.Project;
import tn.esprit.project_task.entity.Task;
import tn.esprit.project_task.repository.BoardRepository;
import tn.esprit.project_task.repository.ProjectRepository;
import java.util.*;
import java.util.stream.Collectors;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectImpl implements IService{

    private final BoardRepository boardRepository;
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
    public Project update(Project project) {

        return projectRepository.save(project);
    }



    @Override
    public List<Task> getTaskProjects(String id) {
        Project project = getProjectById(id).get();
        return project.getTasks();
    }

    @Override
    public List<Project> getProjectsNotInBoard() {

        List<Project> projects = projectRepository.findAll();
        List<Board> boradList =boardRepository.findAll();
        List<String> idBorjectsInBorad = new ArrayList<>();
        if(!boradList.isEmpty() && boradList!=null){
            boradList.forEach(board -> {
                idBorjectsInBorad.add(board.getProject().getProject_id());
            });
        }


        return projects.stream()
                .filter(project -> !idBorjectsInBorad.contains(project.getProject_id()))
                .collect(Collectors.toList());
    }


    public FullProjectResponse findProjectsWithUsers(String projet_id) {
        var project = projectRepository.findById(projet_id)
                .orElse(Project.builder()
                        .title ( "" )
                        .description ( "" )
                        .build());

        var users = userClient.fundAllUsersByProjects(projet_id);

        return FullProjectResponse.builder()
                .title (project.getTitle ())
                .description ( project.getDescription () )
                .users ( users )
                .build ();
    }
    public Map<String, Object> getDashboardStats() {
        List<Project> projects = projectRepository.findAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        // Statistiques mensuelles : nombre de projets créés par mois
        Map<String, Long> projectsPerMonth = projects.stream()
                .collect( Collectors.groupingBy(
                        p -> sdf.format(p.getStartDate()),
                        Collectors.counting()
                ));

        // Moyenne des membres par projet (par mois)
        Map<String, Double> averageMembersPerMonth = projects.stream()
                .collect(Collectors.groupingBy(
                        p -> sdf.format(p.getStartDate()),
                        Collectors.averagingInt(p -> p.getMembers() != null ? p.getMembers().size() : 0)
                ));

        // Répartition des projets par taille (nombre de membres)
        Map<String, Long> projectsBySize = projects.stream()
                .collect(Collectors.groupingBy(
                        p -> {
                            int size = p.getMembers() != null ? p.getMembers().size() : 0;
                            if (size <= 3) return "Petit (<=3)";
                            else if (size <= 6) return "Moyen (4-6)";
                            else return "Grand (>6)";
                        },
                        Collectors.counting()
                ));

        Map<String, Object> stats = new HashMap<> ();
        stats.put("projectsPerMonth", projectsPerMonth);
        stats.put("averageMembersPerMonth", averageMembersPerMonth);
        stats.put("projectsBySize", projectsBySize);
        stats.put("totalProjects", projects.size());

        return stats;
    }



}