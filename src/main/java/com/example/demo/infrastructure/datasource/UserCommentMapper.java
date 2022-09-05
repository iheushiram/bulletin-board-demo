package com.example.demo.infrastructure.datasource;

import com.example.demo.application.dto.UserCommentDto;
import com.example.demo.application.dto.UserCommentReadDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;
@Mapper
public interface UserCommentMapper {
    @Insert("sql/insertUserComment.sql")
    void insert(@Param("dto") UserCommentDto dto);

    @Select("sql/selectUserComment.sql")
    List<UserCommentReadDto> select();

    @Select("sql/selectMyComment.sql")
    List<UserCommentReadDto> selectById(@Param("userId") String userId);

}
