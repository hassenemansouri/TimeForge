package tn.esprit.project_task.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.project_task.BoardResponse;
import tn.esprit.project_task.dto.BoardDTO;
import tn.esprit.project_task.entity.Board;
import tn.esprit.project_task.exception.BoardNotFoundException;
import tn.esprit.project_task.repository.BoardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class BoardImpl implements IServiceBoard{
    private BoardRepository boardRepository;

    public Board getBoardById(String boardId) {
        Optional<Board> board = boardRepository.findById(boardId);
        return board.orElseThrow(() -> new BoardNotFoundException(boardId));
    }

    public List<Board> getAllBoards() {
        List<Board> boardCollection = new ArrayList<>();
        boardRepository.findAll().forEach(boardCollection::add);
        return boardCollection;
    }


    @Override
    public Board createBoard(BoardDTO boardDTO) {
        Board board = Board.builder()
                .title(boardDTO.getTitle())
                .description(boardDTO.getDescription())
                .project(boardDTO.getProject())
                .build();
        return boardRepository.save(board);
    }

    @Override
    public Board modifyBoard(Board updatedBoard) {
            return boardRepository.save(updatedBoard);
    }
    @Override
    public void deleteBoard(String boardId) {
        boardRepository.deleteById(boardId);
    }
    @Override
    public Board getBoardsByProject(String projectId) {
            return boardRepository.findByProject(projectId);
        }

}

