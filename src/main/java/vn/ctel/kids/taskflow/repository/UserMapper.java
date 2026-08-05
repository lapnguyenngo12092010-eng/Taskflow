package vn.ctel.kids.taskflow.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vn.ctel.kids.taskflow.domain.User;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    void insert(User user);

    List<User> findAll();

    Optional<User> findById(@Param("id") Long id);

    void update(User user);

    void deleteById(@Param("id") Long id);
}