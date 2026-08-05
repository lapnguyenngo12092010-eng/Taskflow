package vn.ctel.kids.taskflow.mapper;

import org.springframework.stereotype.Component;
import vn.ctel.kids.taskflow.domain.Task;
import vn.ctel.kids.taskflow.dto.TaskRequest;
import vn.ctel.kids.taskflow.dto.TaskResponse;

@Component
public class TaskConverter {

    public Task toEntity(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setCreatedBy(request.getCreatedBy());
        task.setAssignedTo(request.getAssignedTo());
        task.setProjectId(request.getProjectId());
        task.setDueDate(request.getDueDate());
        return task;
    }

    public void updateEntity(Task task, TaskRequest request) {
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setCreatedBy(request.getCreatedBy());
        task.setAssignedTo(request.getAssignedTo());
        task.setProjectId(request.getProjectId());
        task.setDueDate(request.getDueDate());
    }

    public TaskResponse toResponse(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());
        response.setPriority(task.getPriority());
        response.setCreatedBy(task.getCreatedBy());
        response.setAssignedTo(task.getAssignedTo());
        response.setProjectId(task.getProjectId());
        response.setDueDate(task.getDueDate());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());
        return response;
    }
}