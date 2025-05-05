package tn.esprit.project_task.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project_task.dto.ColumnDTO;
import tn.esprit.project_task.entity.Column;
import tn.esprit.project_task.service.ColumnImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/columns")
@AllArgsConstructor
public class ColumnController {

    private ColumnImpl columnService;

    @GetMapping
    public List<Column> getAllColumns() {
        return columnService.getAllColumns();
    }

    @GetMapping("/{id}")
    public Optional<Column> getColumnById(@PathVariable String id) {
        return columnService.getColumnById(id);
    }

    @PostMapping("/create")
    public Column createColumn(@RequestBody ColumnDTO column) {
        return columnService.createColumn(column);
    }

    @DeleteMapping("/{id}")
    public void deleteColumn(@PathVariable String id) {
        columnService.deleteColumn(id);
    }

    @PutMapping("/modify-column")
    public Column modifyColumn(@RequestBody Column column) {
        return columnService.modifyColumn(column);
    }
    @PostMapping("/createListColumn")
    public void createListColumn(@RequestBody List <Column> column) {
         columnService.saveListColumn(column);
    }


    @GetMapping("/getAll/{id}")
    public List<Column> getColumnByIdBoard(@PathVariable String id) {
        return columnService.getAllColumnsByIdBoard(id);
    }
    @PutMapping("/deleteBoard")
    public Column removeBoardFromColomn(@RequestBody Column column) {
        return columnService.removeColumnFromBoard(column);


    }

    @PutMapping("/addTaskToColumn")
    public Column addTaskToColumn(@RequestBody Column column) {
        return columnService.addTaskToColumn(column);
    }

    @PutMapping("/move-task")
    public Column moveTask(
            @RequestParam String taskId,
            @RequestParam String fromColumnId,
            @RequestParam String toColumnId
    ) {
        return columnService.moveTaskToColumn(taskId, fromColumnId, toColumnId);
    }

}
