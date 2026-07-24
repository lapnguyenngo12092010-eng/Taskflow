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

    List<Task> search(@Param("status") TaskStatus status, @Param("keyword") String keyword);

    Optional<Task> findById(@Param("id") Long id);

    int update(Task task);

    int deleteById(@Param("id") Long id);
}