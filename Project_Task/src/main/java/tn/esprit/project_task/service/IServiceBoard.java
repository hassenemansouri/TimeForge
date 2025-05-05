package tn.esprit.project_task.service;

import tn.esprit.project_task.BoardResponse;
import tn.esprit.project_task.dto.BoardDTO;
import tn.esprit.project_task.entity.Board;
import tn.esprit.project_task.entity.Task;

import java.util.List;
import java.util.Optional;

public interface IServiceBoard {
    Board getBoardById(String id);
    Board modifyBoard(Board board);
    Board createBoard(BoardDTO board);
    void deleteBoard(String id);
    Board getBoardsByProject(String projectId);


}
