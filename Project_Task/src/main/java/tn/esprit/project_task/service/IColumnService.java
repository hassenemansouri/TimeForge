package tn.esprit.project_task.service;

import tn.esprit.project_task.dto.ColumnDTO;
import tn.esprit.project_task.entity.Column;

import java.util.List;
import java.util.Optional;

public interface IColumnService {
    Column createColumn(ColumnDTO column);
    List<Column> getAllColumns();
    void deleteColumn(String id);
    Optional<Column> getColumnById(String id);
    Column modifyColumn(Column column);
    void saveListColumn(List<Column> listColumn);
    List<Column> getAllColumnsByIdBoard(String idBoard);
    Column removeColumnFromBoard(Column column);
}
