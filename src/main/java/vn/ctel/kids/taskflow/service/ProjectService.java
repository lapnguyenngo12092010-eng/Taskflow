package vn.ctel.kids.taskflow.service;

import vn.ctel.kids.taskflow.dto.ProjectRequest;
import vn.ctel.kids.taskflow.dto.ProjectResponse;
import vn.ctel.kids.taskflow.dto.TaskResponse;

import java.util.List;

public interface ProjectService {
    ProjectResponse create(ProjectRequest request);
    List<ProjectResponse> findAll();
    ProjectResponse getById(Long id);
    ProjectResponse update(Long id, ProjectRequest request);
    void delete(Long id);
    List<TaskResponse> getTasksByProjectId(Long projectId);
}