package TaskService.dto;

import TaskService.constants.TaskExecutionPriority;
import TaskService.constants.TaskExecutionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Заголовок задачи")
    private String title;
    @Schema(description = "Описание задачи")
    private String description;
    @Schema(description = "Статус выполнения задачи")
    private TaskExecutionStatus status;
    @Schema(description = "Приоритет выполнения задачи")
    private TaskExecutionPriority priority;
    @Schema(description = "Идентификатор автора задачи")
    private Long authorId;
    @Schema(description = "Идентификатор исполнителя задачи")
    private Long executorId;
}
