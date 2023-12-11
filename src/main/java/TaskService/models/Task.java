package TaskService.models;

import TaskService.constants.TaskExecutionPriority;
import TaskService.constants.TaskExecutionStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "title")
    private String title;

    @NotBlank
    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskExecutionStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private TaskExecutionPriority priority;

    @NotNull
    @Column(name = "author_id")
    private Long authorId;

    @NotNull
    @Column(name = "executor_id")
    private Long executorId;

}
