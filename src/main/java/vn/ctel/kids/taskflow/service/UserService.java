package vn.ctel.kids.taskflow.service;

import vn.ctel.kids.taskflow.dto.UserRequest;
import vn.ctel.kids.taskflow.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse create(UserRequest request);
    List<UserResponse> findAll();
    UserResponse getById(Long id);
    UserResponse update(Long id, UserRequest request);
    void delete(Long id);
}