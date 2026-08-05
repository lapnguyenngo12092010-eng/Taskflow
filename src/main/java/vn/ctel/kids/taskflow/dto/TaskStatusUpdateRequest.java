package vn.ctel.kids.taskflow.dto;

import jakarta.validation.constraints.NotNull;
import vn.ctel.kids.taskflow.domain.TaskStatus;

public class TaskStatusUpdateRequest {

    @NotNull
    private TaskStatus status;

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}