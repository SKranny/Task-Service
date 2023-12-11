package TaskService.service;

import TaskService.constants.TaskExecutionPriority;
import TaskService.constants.TaskExecutionStatus;
import TaskService.dto.TaskDTO;
import TaskService.dto.person.PersonDTO;
import TaskService.dto.request.CreateTaskRequest;
import TaskService.dto.request.UpdateTaskRequest;
import TaskService.dto.security.TokenData;
import TaskService.mappers.TaskMapper;
import TaskService.models.Task;
import TaskService.repository.TaskRepository;
import TaskService.service.feign.PersonService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TaskServiceImplTest {
    private TaskRepository taskRepository = mock(TaskRepository.class);

    private PersonService personService = mock(PersonService.class);

    private TaskMapper taskMapper = mock(TaskMapper.class);

    private TokenData tokenData = TokenData.builder()
            .token("some8-jwt9-token")
            .id(1L)
            .email("test@mail.com")
            .userName("superUser1337")
            .build();

    private TaskServiceImpl taskService = new TaskServiceImpl(taskRepository,taskMapper,personService);

    @org.junit.jupiter.api.Test
    void createTask() {

        CreateTaskRequest createTaskRequest = CreateTaskRequest.builder()
                .title("title")
                .description("description")
                .priority("Low")
                .executorId(3L)
                .build();

        Task task = Task.builder()
                .id(1L)
                .authorId(2L)
                .executorId(3L)
                .title("title")
                .description("description")
                .priority(TaskExecutionPriority.LOW)
                .status(TaskExecutionStatus.WAITING)
                .build();

        TaskDTO taskDTO = TaskDTO.builder()
                .id(1L)
                .authorId(2L)
                .executorId(3L)
                .title("title")
                .description("description")
                .status(TaskExecutionStatus.WAITING)
                .priority(TaskExecutionPriority.LOW)
                .build();

        PersonDTO personDTO = PersonDTO.builder()
                .username("superUser1337")
                .build();

        when(personService.getPersonDTOByUsername(tokenData.getUserName())).thenReturn(personDTO);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskMapper.toTaksDTO(task)).thenReturn(taskDTO);

        taskService.createTask(createTaskRequest,tokenData);
        assertEquals(taskDTO, taskMapper.toTaksDTO(taskRepository.findById(1L).get()));
    }

    @org.junit.jupiter.api.Test
    void deleteTask() {
    }

    @org.junit.jupiter.api.Test
    void getAllTasksByExecutorId() {
        Long id = 3L;

        Integer offset = 1;

        Integer limit = 10;

        Task task1 = Task.builder()
                .id(1L)
                .authorId(2L)
                .executorId(3L)
                .title("title")
                .description("description")
                .priority(TaskExecutionPriority.LOW)
                .status(TaskExecutionStatus.WAITING)
                .build();
        Task task2 = Task.builder()
                .id(1L)
                .authorId(2L)
                .executorId(3L)
                .title("title")
                .description("description")
                .priority(TaskExecutionPriority.LOW)
                .status(TaskExecutionStatus.WAITING)
                .build();

        TaskDTO taskDTO1 = TaskDTO.builder()
                .id(1L)
                .authorId(2L)
                .executorId(3L)
                .title("title")
                .description("description")
                .status(TaskExecutionStatus.WAITING)
                .priority(TaskExecutionPriority.LOW)
                .build();

        TaskDTO taskDTO2 = TaskDTO.builder()
                .id(1L)
                .authorId(2L)
                .executorId(3L)
                .title("title")
                .description("description")
                .status(TaskExecutionStatus.WAITING)
                .priority(TaskExecutionPriority.LOW)
                .build();

        List<Task> tasksInDB = List.of(task1,task2);

        List<TaskDTO> expectedListOfTasks = List.of(taskDTO1,taskDTO2);

        when(taskRepository.findAllByExecutorId(id,PageRequest.of(offset,limit))).thenReturn(Optional.of(new PageImpl<>(tasksInDB)));
        when(taskMapper.toTaksDTO(task1)).thenReturn(taskDTO1);
        when(taskMapper.toTaksDTO(task2)).thenReturn(taskDTO2);

        assertEquals(expectedListOfTasks, taskService.getAllTasksByExecutorId(id,offset, limit).stream().toList());

    }

    @org.junit.jupiter.api.Test
    void getAllTasksByAuthorId() {
        Long id = 2L;

        Integer offset = 1;

        Integer limit = 10;

        Task task1 = Task.builder()
                .id(1L)
                .authorId(2L)
                .executorId(3L)
                .title("title")
                .description("description")
                .priority(TaskExecutionPriority.LOW)
                .status(TaskExecutionStatus.WAITING)
                .build();
        Task task2 = Task.builder()
                .id(1L)
                .authorId(2L)
                .executorId(3L)
                .title("title")
                .description("description")
                .priority(TaskExecutionPriority.LOW)
                .status(TaskExecutionStatus.WAITING)
                .build();

        TaskDTO taskDTO1 = TaskDTO.builder()
                .id(1L)
                .authorId(2L)
                .executorId(3L)
                .title("title")
                .description("description")
                .status(TaskExecutionStatus.WAITING)
                .priority(TaskExecutionPriority.LOW)
                .build();

        TaskDTO taskDTO2 = TaskDTO.builder()
                .id(1L)
                .authorId(2L)
                .executorId(3L)
                .title("title")
                .description("description")
                .status(TaskExecutionStatus.WAITING)
                .priority(TaskExecutionPriority.LOW)
                .build();

        List<Task> tasksInDB = List.of(task1,task2);

        List<TaskDTO> expectedListOfTasks = List.of(taskDTO1,taskDTO2);

        when(taskRepository.findAllByAuthorId(id,PageRequest.of(offset,limit))).thenReturn(Optional.of(new PageImpl<>(tasksInDB)));
        when(taskMapper.toTaksDTO(task1)).thenReturn(taskDTO1);
        when(taskMapper.toTaksDTO(task2)).thenReturn(taskDTO2);

        assertEquals(expectedListOfTasks, taskService.getAllTasksByAuthorId(id,offset, limit).stream().toList());
    }

    @org.junit.jupiter.api.Test
    void editTask(){
        UpdateTaskRequest updateTaskRequest = UpdateTaskRequest.builder()
                .status("Waiting")
                .description("new description")
                .id(1L)
                .title("new title")
                .priority("Low")
                .build();

        Task task = Task.builder()
                .id(1L)
                .authorId(2L)
                .executorId(3L)
                .title("title")
                .description("description")
                .priority(TaskExecutionPriority.LOW)
                .status(TaskExecutionStatus.WAITING)
                .build();

        TaskDTO taskDTO = TaskDTO.builder()
                .id(1L)
                .authorId(2L)
                .executorId(3L)
                .title("new title")
                .description("new description")
                .status(TaskExecutionStatus.WAITING)
                .priority(TaskExecutionPriority.LOW)
                .build();

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskMapper.toTaksDTO(task)).thenReturn(taskDTO);

        assertEquals(taskDTO, taskService.editTask(updateTaskRequest));
    }

    @org.junit.jupiter.api.Test
    void assignExecutor(){
        Long taskId = 1L;
        Long executorId = 3L;

        Task task = Task.builder()
                .id(1L)
                .authorId(2L)
                .executorId(null)
                .title("title")
                .description("description")
                .priority(TaskExecutionPriority.LOW)
                .status(TaskExecutionStatus.WAITING)
                .build();

        TaskDTO taskDTO = TaskDTO.builder()
                .id(1L)
                .authorId(2L)
                .executorId(3L)
                .title("title")
                .description("description")
                .status(TaskExecutionStatus.WAITING)
                .priority(TaskExecutionPriority.LOW)
                .build();

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskMapper.toTaksDTO(task)).thenReturn(taskDTO);

        assertEquals(taskDTO, taskService.assignExecutor(taskId,executorId));
    }

    @org.junit.jupiter.api.Test
    void changeStatus(){
        Long taskId = 1L;
        UpdateTaskRequest updateTaskRequest = UpdateTaskRequest.builder()
                .status("In progress")
                .description("description")
                .id(1L)
                .title("title")
                .priority("Low")
                .build();

        Task task = Task.builder()
                .id(1L)
                .authorId(2L)
                .executorId(3L)
                .title("title")
                .description("description")
                .priority(TaskExecutionPriority.LOW)
                .status(TaskExecutionStatus.WAITING)
                .build();

        TaskDTO taskDTO = TaskDTO.builder()
                .id(1L)
                .authorId(2L)
                .executorId(3L)
                .title("title")
                .description("description")
                .status(TaskExecutionStatus.IN_PROGRESS)
                .priority(TaskExecutionPriority.LOW)
                .build();

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskMapper.toTaksDTO(task)).thenReturn(taskDTO);

        assertEquals(taskDTO, taskService.changeStatus(taskId,updateTaskRequest));
    }

}
