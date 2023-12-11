package TaskService.service;

import TaskService.constants.TaskExecutionPriority;
import TaskService.constants.TaskExecutionStatus;
import TaskService.dto.request.CreateTaskRequest;
import TaskService.dto.TaskDTO;
import TaskService.dto.request.UpdateTaskRequest;
import TaskService.dto.person.PersonDTO;
import TaskService.dto.security.TokenData;
import TaskService.mappers.TaskMapper;
import TaskService.models.Task;
import TaskService.repository.TaskRepository;
import TaskService.service.feign.PersonService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final PersonService personService;

    @Override
    public void createTask(CreateTaskRequest createTaskRequest, TokenData tokenData) {
        PersonDTO user = personService.getPersonDTOByUsername(tokenData.getUserName());
        Task task = new Task().builder()
                .title(createTaskRequest.getTitle())
                .description(createTaskRequest.getDescription())
                .priority(getCorrectPriority(createTaskRequest.getPriority()))
                .authorId(user.getId())
                .executorId(createTaskRequest.getExecutorId())
                .status(TaskExecutionStatus.WAITING)
                .build();
        taskRepository.save(task);
    }

    @Override
    public TaskDTO editTask(UpdateTaskRequest updateTaskRequest) {
        Task task = taskRepository.findById(updateTaskRequest.getId())
                .orElseThrow(() -> new RuntimeException("Error! Task not found!"));
        task.setTitle(updateTaskRequest.getTitle());
        task.setDescription(updateTaskRequest.getDescription());
        taskRepository.save(task);
        return taskMapper.toTaksDTO(task);
    }

    @Override
    public Page<TaskDTO> getAllTasksByExecutorId(Long id, Integer page, Integer limit) {
        return new PageImpl<>(taskRepository.findAllByExecutorId(id, PageRequest.of(page,limit))
                .map(tasks -> tasks.stream()
                        .map(taskMapper::toTaksDTO)
                        .toList())
                .orElseThrow(()-> new RuntimeException("Error! Executor not found!")));
    }
    @Override
    public Page<TaskDTO> getAllTasksByAuthorId(Long id, Integer page, Integer limit) {
        return new PageImpl<>(taskRepository.findAllByAuthorId(id, PageRequest.of(page,limit))
                .map(tasks -> tasks.stream()
                        .map(taskMapper::toTaksDTO)
                        .toList())
                .orElseThrow(()-> new RuntimeException("Error! Author not found!")));
    }

    @Override
    public TaskDTO changeStatus(Long taskId, UpdateTaskRequest updateTaskRequest) {
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new RuntimeException("Error! Task not found"));
        task.setStatus(getCorrectStatus(updateTaskRequest.getStatus()));
        taskRepository.save(task);
        return taskMapper.toTaksDTO(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public TaskDTO assignExecutor(Long taskId, Long executorId){
        Task task = taskRepository.findById(taskId).orElseThrow(()->new RuntimeException("Error! Task not found"));
        task.setExecutorId(executorId);
        taskRepository.save(task);
        return taskMapper.toTaksDTO(task);
    }

    private TaskExecutionPriority getCorrectPriority(String priority){
        TaskExecutionPriority res = switch (priority.toLowerCase()) {
            case "high" -> TaskExecutionPriority.HIGH;
            case "middle" -> TaskExecutionPriority.MIDDLE;
            default -> TaskExecutionPriority.LOW;
        };
        return res;
    }

    private TaskExecutionStatus getCorrectStatus(String status){
        TaskExecutionStatus res = switch (status.toLowerCase()) {
            case "completed" -> TaskExecutionStatus.COMPLETED;
            case "in progress" -> TaskExecutionStatus.IN_PROGRESS;
            default -> TaskExecutionStatus.WAITING;
        };
        return res;
    }
}
