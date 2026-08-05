package vn.ctel.kids.taskflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ctel.kids.taskflow.domain.Task;
import vn.ctel.kids.taskflow.domain.User;
import vn.ctel.kids.taskflow.dto.UserRequest;
import vn.ctel.kids.taskflow.dto.UserResponse;
import vn.ctel.kids.taskflow.exception.ConflictException;
import vn.ctel.kids.taskflow.exception.ResourceNotFoundException;
import vn.ctel.kids.taskflow.mapper.UserConverter;
import vn.ctel.kids.taskflow.repository.TaskMapper;
import vn.ctel.kids.taskflow.repository.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserConverter userConverter;
    private final TaskMapper taskMapper;

    @Override
    public UserResponse create(UserRequest request) {
        User user = userConverter.toEntity(request);
        userMapper.insert(user);
        return userConverter.toResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userMapper.findAll().stream()
                .map(userConverter::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(Long id) {
        User user = findOrThrow(id);
        return userConverter.toResponse(user);
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {
        User user = findOrThrow(id);
        userConverter.updateEntity(user, request);
        userMapper.update(user);
        return userConverter.toResponse(user);
    }

    @Override
    public void delete(Long id) {
        findOrThrow(id);
        List<Task> created = taskMapper.findByCreatedBy(id);
        List<Task> assigned = taskMapper.findByAssignedTo(id);
        if (!created.isEmpty() || !assigned.isEmpty()) {
            throw new ConflictException("Không thể xóa User vì đang có Task liên quan (tạo hoặc được giao)");
        }
        userMapper.deleteById(id);
    }

    private User findOrThrow(Long id) {
        return userMapper.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy User với id: " + id));
    }
}