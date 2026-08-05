package vn.ctel.kids.taskflow.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vn.ctel.kids.taskflow.domain.Project;
import vn.ctel.kids.taskflow.domain.Task;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProjectMapper {

    void insert(Project project);

    List<Project> findAll();

    Optional<Project> findById(@Param("id") Long id);

    void update(Project project);

    void deleteById(@Param("id") Long id);

    List<Task> findTasksByProjectId(@Param("projectId") Long projectId);
}