package vn.ctel.kids.taskflow.mapper;

import org.springframework.stereotype.Component;
import vn.ctel.kids.taskflow.domain.User;
import vn.ctel.kids.taskflow.dto.UserRequest;
import vn.ctel.kids.taskflow.dto.UserResponse;

@Component
public class UserConverter {

    public User toEntity(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        return user;
    }

    public void updateEntity(User user, UserRequest request) {
        user.setName(request.getName());
        user.setEmail(request.getEmail());
    }

    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        return response;
    }
}