package TaskService.controllers;

import TaskService.dto.CommentDTO;
import TaskService.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/task/comment")
@RequiredArgsConstructor
@Tag(name="Comments", description="Comment management API")
public class CommentController {
    private final CommentService commentService;
    @Operation(summary = "Getting a list of comments for current task by task ID")
    @GetMapping
    public List<CommentDTO> getAllCommentsForTask(Long taskId){
        return commentService.getAllCommentsByTaskId(taskId);
    }
}
