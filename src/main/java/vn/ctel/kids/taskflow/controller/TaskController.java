package vn.ctel.kids.taskflow.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.ctel.kids.taskflow.domain.TaskStatus;
import vn.ctel.kids.taskflow.dto.TaskRequest;
import vn.ctel.kids.taskflow.dto.TaskResponse;
import vn.ctel.kids.taskflow.service.TaskService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /**
     * POST /api/tasks — tạo mới. Trả 201 Created + header Location.
     */
    @PostMapping
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody TaskRequest request,
                                               UriComponentsBuilder uriBuilder) {
        TaskResponse created = taskService.create(request);
        URI location = uriBuilder.path("/api/tasks/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    /**
     * GET /api/tasks — danh sách, có thể lọc theo status và keyword.
     * Ví dụ: /api/tasks?status=TODO&keyword=login
     */
    @GetMapping
    public List<TaskResponse> search(@RequestParam(required = false) TaskStatus status,
                                     @RequestParam(required = false) String keyword) {
        return taskService.search(status, keyword);
    }

    /**
     * GET /api/tasks/{id} — lấy 1 task. Trả 404 nếu không có.
     */
    @GetMapping("/{id}")
    public TaskResponse getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    /**
     * PUT /api/tasks/{id} — cập nhật. Trả 404 nếu id không tồn tại.
     */
    @PutMapping("/{id}")
    public TaskResponse update(@PathVariable Long id, @Valid @RequestBody TaskRequest request) {
        return taskService.update(id, request);
    }

    /**
     * DELETE /api/tasks/{id} — xóa. Trả 204 No Content.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    /**
     * GET /api/tasks/filter — lọc theo nhiều tiêu chí: status, priority, projectId.
     * Ví dụ: /api/tasks/filter?status=DONE&priority=HIGH&projectId=3
     */
    @GetMapping("/filter")
    public List<TaskResponse> getTasks(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) Long projectId) {
        return taskService.filterTasks(status, priority, projectId);
    }
}