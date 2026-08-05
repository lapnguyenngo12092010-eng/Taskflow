package vn.ctel.kids.taskflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ctel.kids.taskflow.domain.Project;
import vn.ctel.kids.taskflow.domain.Task;
import vn.ctel.kids.taskflow.dto.ProjectRequest;
import vn.ctel.kids.taskflow.dto.ProjectResponse;
import vn.ctel.kids.taskflow.dto.TaskResponse;
import vn.ctel.kids.taskflow.exception.ConflictException;
import vn.ctel.kids.taskflow.exception.ResourceNotFoundException;
import vn.ctel.kids.taskflow.mapper.ProjectConverter;
import vn.ctel.kids.taskflow.mapper.TaskConverter;
import vn.ctel.kids.taskflow.repository.ProjectMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectConverter projectConverter;
    private final TaskConverter taskConverter;

    @Override
    public ProjectResponse create(ProjectRequest request) {
        Project project = projectConverter.toEntity(request);
        projectMapper.insert(project);
        return projectConverter.toResponse(project);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponse> findAll() {
        return projectMapper.findAll().stream()
                .map(projectConverter::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectResponse getById(Long id) {
        Project project = findOrThrow(id);
        return projectConverter.toResponse(project);
    }

    @Override
    public ProjectResponse update(Long id, ProjectRequest request) {
        Project project = findOrThrow(id);
        projectConverter.updateEntity(project, request);
        projectMapper.update(project);
        return projectConverter.toResponse(project);
    }

    @Override
    public void delete(Long id) {
        findOrThrow(id);
        List<Task> tasks = projectMapper.findTasksByProjectId(id);
        if (!tasks.isEmpty()) {
            throw new ConflictException("Không thể xóa Project vì đang có Task thuộc project này");
        }
        projectMapper.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> getTasksByProjectId(Long projectId) {
        findOrThrow(projectId);
        return projectMapper.findTasksByProjectId(projectId).stream()
                .map(taskConverter::toResponse)
                .toList();
    }

    private Project findOrThrow(Long id) {
        return projectMapper.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Project với id: " + id));
    }
}