package TaskService.service;

import TaskService.dto.CommentDTO;
import TaskService.dto.request.CreateTaskRequest;
import TaskService.dto.TaskDTO;
import TaskService.dto.request.UpdateTaskRequest;
import TaskService.dto.security.TokenData;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {
    void createTask(CreateTaskRequest createTaskRequest, TokenData tokenData);

    TaskDTO editTask(UpdateTaskRequest updateTaskRequest);

    Page<TaskDTO> getAllTasksByExecutorId(Long id, Integer page, Integer limit);

    Page<TaskDTO> getAllTasksByAuthorId(Long id, Integer page, Integer limit);

    TaskDTO changeStatus(Long taskId, UpdateTaskRequest updateTaskRequest);

    void deleteTask(Long taskId);
    TaskDTO assignExecutor(Long taskId, Long executorId);

}
