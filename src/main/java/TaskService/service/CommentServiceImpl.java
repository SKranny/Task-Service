package TaskService.service;

import TaskService.dto.CommentDTO;
import TaskService.mappers.CommentMapper;
import TaskService.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    @Override
    public List<CommentDTO> getAllCommentsByTaskId(Long taskId) {
        return commentRepository.findAllByTaskId(taskId)
                .map(comments -> comments.stream()
                        .map(commentMapper::toCommentDTO)
                        .collect(Collectors.toList()))
                .orElseThrow(()->new RuntimeException("Error! Task not found!"));
    }
}
