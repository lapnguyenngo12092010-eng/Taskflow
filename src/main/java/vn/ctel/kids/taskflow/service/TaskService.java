package vn.ctel.kids.taskflow.service;

import vn.ctel.kids.taskflow.domain.TaskStatus;
import vn.ctel.kids.taskflow.dto.TaskRequest;
import vn.ctel.kids.taskflow.dto.TaskResponse;

import java.util.List;

public interface TaskService {
    TaskResponse create(TaskRequest request);
    List<TaskResponse> search(TaskStatus status, String keyword);
    TaskResponse getById(Long id);
    TaskResponse update(Long id, TaskRequest request);
    void delete(Long id);

    List<TaskResponse> filterTasks(String status, String priority, Long projectId); // thêm nếu cần
}
