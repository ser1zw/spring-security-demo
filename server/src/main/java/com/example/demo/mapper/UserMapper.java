package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    @Select("select * from users where login_id = #{loginId}")
    @Results({
            @Result(property = "loginId", column = "login_id")
    })
    Optional<User> findByLoginId(String loginId);
}
