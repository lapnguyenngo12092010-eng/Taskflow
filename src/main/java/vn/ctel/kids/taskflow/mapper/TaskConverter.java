package vn.ctel.kids.taskflow.mapper;

import org.springframework.stereotype.Component;
import vn.ctel.kids.taskflow.domain.Task;
import vn.ctel.kids.taskflow.dto.TaskRequest;
import vn.ctel.kids.taskflow.dto.TaskResponse;

@Component
public class TaskConverter {

    public Task toEntity(TaskRequest request) {
        return Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus() != null ? request.getStatus() : vn.ctel.kids.taskflow.domain.TaskStatus.TODO)
                .priority(request.getPriority() != null ? request.getPriority() : vn.ctel.kids.taskflow.domain.TaskPriority.MEDIUM)
                .assignee(request.getAssignee())
                .dueDate(request.getDueDate())
                .build();
    }

    public void updateEntity(Task task, TaskRequest request) {
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
        }
        if (request.getPriority() != null) {
            task.setPriority(request.getPriority());
        }
        task.setAssignee(request.getAssignee());
        task.setDueDate(request.getDueDate());
    }

    public TaskResponse toResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .assignee(task.getAssignee())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }

}