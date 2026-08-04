package vn.ctel.kids.taskflow.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vn.ctel.kids.taskflow.domain.Task;
import vn.ctel.kids.taskflow.domain.TaskStatus;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TaskMapper {

    void insert(Task task);

    List<Task> search(@Param("status") TaskStatus status,
                      @Param("keyword") String keyword);

    Optional<Task> findById(@Param("id") Long id);

    void update(Task task);

    void deleteById(@Param("id") Long id);

    List<Task> filterTasks(@Param("status") String status,
                           @Param("priority") String priority,
                           @Param("projectId") Long projectId);
}