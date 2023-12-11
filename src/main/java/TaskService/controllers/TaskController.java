package TaskService.controllers;

import TaskService.dto.request.CreateTaskRequest;
import TaskService.dto.TaskDTO;
import TaskService.dto.request.UpdateTaskRequest;
import TaskService.dto.security.TokenAuthentication;
import TaskService.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/task")
@RequiredArgsConstructor
@Tag(name="Task-service", description="Task management API")
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Creating a new task")
    @PostMapping("/create")
    public void createTask(CreateTaskRequest createTaskRequest, TokenAuthentication authentication){
        taskService.createTask(createTaskRequest, authentication.getTokenData());
    }

    @Operation(summary = "Editing task by UpdateTaskRequest")
    @PutMapping("/edit")
    public TaskDTO editTask(UpdateTaskRequest updateTaskRequest){
        return taskService.editTask(updateTaskRequest);
    }
    @Operation(summary = "Deleting the task by task ID")
    @DeleteMapping
    public void deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
    }
    @Operation(summary = "Getting a list of tasks by executor`s ID")
    @GetMapping
    public Page<TaskDTO> getAllTasksByExecutorId(
            @PathVariable Long id,
            @Valid @Min(0) @RequestParam(name = "page", defaultValue = "0", required = false)Integer page,
            @Valid @Min(0) @RequestParam(name = "limit", defaultValue = "20", required = false)Integer limit){
        return taskService.getAllTasksByExecutorId(id, page, limit);
    }

    @Operation(summary = "Getting a list of tasks by author`s ID")
    @GetMapping
    public Page<TaskDTO> getAllTasksByAuthorId(
            @PathVariable Long id,
            @Valid @Min(0) @RequestParam(name = "page", defaultValue = "0", required = false)Integer page,
            @Valid @Min(0) @RequestParam(name = "limit", defaultValue = "20", required = false)Integer limit){
        return taskService.getAllTasksByAuthorId(id, page, limit);
    }
    @Operation(summary = "Changing the status of tasks by task`s ID")
    @PutMapping("/status")
    public TaskDTO changeStatusOfTask(@PathVariable Long taskId, UpdateTaskRequest updateTaskRequest){
        return taskService.changeStatus(taskId,updateTaskRequest);
    }

    @Operation(summary = "Assigning the executor for the task by task`s ID")
    @PutMapping("/assign")
    public TaskDTO assignExecutorForTask(@PathVariable Long taskId, @PathVariable Long executorId){
        return taskService.assignExecutor(taskId,executorId);
    }

}
