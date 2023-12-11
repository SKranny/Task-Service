package TaskService.mappers;

import TaskService.dto.TaskDTO;
import TaskService.models.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDTO toTaksDTO(Task task);
    Task toTask(TaskDTO taskDTO);
}
