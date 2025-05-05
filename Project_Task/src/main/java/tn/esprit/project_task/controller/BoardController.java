package tn.esprit.project_task.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project_task.BoardResponse;
import tn.esprit.project_task.dto.BoardDTO;
import tn.esprit.project_task.entity.Board;
import tn.esprit.project_task.service.BoardImpl;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping( "/boards")
@AllArgsConstructor
public class BoardController {

    private final BoardImpl boardService;

    @GetMapping
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{id}")
    public Board getBoardById(@PathVariable String id) {
        return boardService.getBoardById(id);
    }

    @PostMapping("/create")
    public Board createBoard(@RequestBody BoardDTO board) {
        return boardService.createBoard(board);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable String id) {
        boardService.deleteBoard(id);
    }

    @PutMapping("/modify-board")
    public Board modifyBoard(@RequestBody Board board) {
        return boardService.modifyBoard(board);
    }
    @GetMapping("/project/{projectId}")
    public Board getBoardsByProject(@PathVariable String projectId) {
        return boardService.getBoardsByProject(projectId);
    }

}
