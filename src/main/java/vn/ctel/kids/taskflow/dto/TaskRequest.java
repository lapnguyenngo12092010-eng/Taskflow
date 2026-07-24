package vn.ctel.kids.taskflow.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.ctel.kids.taskflow.domain.TaskPriority;
import vn.ctel.kids.taskflow.domain.TaskStatus;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {

    @NotBlank(message = "Tiêu đề không được để trống")
    @Size(max = 255, message = "Tiêu đề tối đa 255 ký tự")
    private String title;

    @Size(max = 2000, message = "Mô tả tối đa 2000 ký tự")
    private String description;

    private TaskStatus status;

    private TaskPriority priority;

    private String assignee;

    @NotNull(message = "Hạn hoàn thành không được để trống")
    @FutureOrPresent(message = "Hạn hoàn thành phải là hiện tại hoặc tương lai")
    private LocalDate dueDate;
}