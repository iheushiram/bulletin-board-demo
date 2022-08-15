package com.example.demo.domain.model;

import com.example.demo.application.dto.UserCommentDto;

public interface UserCommentRepository {
    void save(UserComment userComment);
    UserComments select();
    UserComments select(UserId userId);
}
