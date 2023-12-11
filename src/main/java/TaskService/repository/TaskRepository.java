package TaskService.repository;

import TaskService.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Page<Task>> findAllByExecutorId(Long id, Pageable pageable);

    Optional<Page<Task>> findAllByAuthorId(Long id, Pageable pageable);
}
