package TaskService.service;

import TaskService.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getAllCommentsByTaskId(Long taskId);
}
