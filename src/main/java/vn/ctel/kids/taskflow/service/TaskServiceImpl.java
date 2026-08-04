package vn.ctel.kids.taskflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ctel.kids.taskflow.domain.Task;
import vn.ctel.kids.taskflow.domain.TaskStatus;
import vn.ctel.kids.taskflow.dto.TaskRequest;
import vn.ctel.kids.taskflow.dto.TaskResponse;
import vn.ctel.kids.taskflow.exception.ResourceNotFoundException;
import vn.ctel.kids.taskflow.mapper.TaskConverter;
import vn.ctel.kids.taskflow.repository.TaskMapper;
import java.util.Set;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private final TaskConverter taskConverter;

    private static final Set<String> ALLOWED_SORT = Set.of("dueDate", "priority", "status", "createdAt");

    public void validateSort(String sortBy) {
        if (sortBy != null && !ALLOWED_SORT.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort field");
        }
    }

    @Override
    public TaskResponse create(TaskRequest request) {
        Task task = taskConverter.toEntity(request);
        taskMapper.insert(task);
        return taskConverter.toResponse(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> search(TaskStatus status, String keyword) {
        return taskMapper.search(status, keyword).stream()
                .map(taskConverter::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponse getById(Long id) {
        Task task = findOrThrow(id);
        return taskConverter.toResponse(task);
    }

    @Override
    public TaskResponse update(Long id, TaskRequest request) {
        Task task = findOrThrow(id);
        taskConverter.updateEntity(task, request);
        taskMapper.update(task);
        return taskConverter.toResponse(task);
    }

    @Override
    public void delete(Long id) {
        findOrThrow(id);
        taskMapper.deleteById(id);
    }
    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> filterTasks(String status, String priority, Long projectId) {
        return taskMapper.filterTasks(status, priority, projectId).stream()
                .map(taskConverter::toResponse)
                .toList();
    }

    private Task findOrThrow(Long id) {
        return taskMapper.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Task với id: " + id));
    }
}