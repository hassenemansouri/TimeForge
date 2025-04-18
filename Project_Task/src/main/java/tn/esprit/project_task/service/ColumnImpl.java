package tn.esprit.project_task.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.project_task.dto.ColumnDTO;
import tn.esprit.project_task.entity.Board;
import tn.esprit.project_task.entity.Column;
import tn.esprit.project_task.repository.ColumnRepository;

import java.util.List;
import java.util.Optional;
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


}

