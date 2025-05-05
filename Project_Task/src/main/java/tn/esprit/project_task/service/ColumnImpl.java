package tn.esprit.project_task.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.project_task.dto.ColumnDTO;
import tn.esprit.project_task.entity.Board;
import tn.esprit.project_task.entity.Column;
import tn.esprit.project_task.entity.Task;
import tn.esprit.project_task.repository.ColumnRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ColumnImpl implements IColumnService {

    private ColumnRepository columnRepository;

    public List<Column> getAllColumns() {
        return columnRepository.findAll();
    }

    public Optional<Column> getColumnById(String id) {
        return columnRepository.findById(id);
    }

    public Column createColumn(ColumnDTO column) {
        Column c = Column.builder().name(column.getName()).order(column.getOrder()).build();
        return columnRepository.save(c);
    }

    public void deleteColumn(String id) {
        columnRepository.deleteById(id);
    }

    public Column modifyColumn(Column column) {
        return columnRepository.save(column);
    }

    @Override
    public void saveListColumn(List<Column> listColumn) {


        columnRepository.saveAll(listColumn);
    }

    @Override
    public List<Column> getAllColumnsByIdBoard(String idBoard) {
        return columnRepository.findByBoard(idBoard);
    }

    @Override
    public Column removeColumnFromBoard(Column column) {
        column.setBoard(null);
        return columnRepository.save(column);
    }
@Override
    public Column addTaskToColumn(Column column) {
        Column oldColumn = columnRepository.findById(column.get_id()).orElse(null);
        if (oldColumn == null) {
            throw new RuntimeException("Colonne introuvable");
        }

        List<Task> existingTasks = oldColumn.getTasks() != null ? oldColumn.getTasks() : new ArrayList<>();
        List<Task> newTasks = column.getTasks();

        // Créer un Set avec les IDs déjà présents
        Set<String> existingIds = existingTasks.stream()
                .map(Task::get_id)
                .collect(Collectors.toSet());

        // Ajouter uniquement les nouvelles tâches qui ont un id différent
        for (Task task : newTasks) {
            if (!existingIds.contains(task.get_id())) {
                existingTasks.add(task);
            }
        }

        oldColumn.setTasks(existingTasks);
        return columnRepository.save(oldColumn);
    }

    @Override
    public Column moveTaskToColumn(String taskId, String fromColumnId, String toColumnId) {
        Column fromColumn = columnRepository.findById(fromColumnId).orElse(null);
        Column toColumn = columnRepository.findById(toColumnId).orElse(null);

        List<Task> fromTasks = fromColumn.getTasks() != null ? fromColumn.getTasks() : new ArrayList<>();
        List<Task> toTasks = toColumn.getTasks() != null ? toColumn.getTasks() : new ArrayList<>();

        // Chercher la tâche à déplacer
        Task taskToMove = fromTasks.stream()
                .filter(task -> task.get_id().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Tâche introuvable dans la colonne source"));

        // Supprimer la tâche de la colonne source
        fromTasks.removeIf(task -> task.get_id().equals(taskId));

        // Ajouter à la colonne destination si elle n'existe pas déjà
        boolean existsInTarget = toTasks.stream().anyMatch(task -> task.get_id().equals(taskId));
        if (!existsInTarget) {
            // Optionnel : mettre à jour l'ID de colonne dans la tâche
            taskToMove.setColumnId(toColumnId);
            toTasks.add(taskToMove);
        }

        fromColumn.setTasks(fromTasks);
        toColumn.setTasks(toTasks);

        // Sauvegarder les deux colonnes
        columnRepository.save(fromColumn);
        return columnRepository.save(toColumn);
    }



}

