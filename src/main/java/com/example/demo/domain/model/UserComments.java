package com.example.demo.domain.model;

import com.example.demo.domain.type.Comment;
import com.example.demo.domain.type.DateTime;
import com.example.demo.domain.type.MailAddress;
import com.example.demo.domain.type.Name;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.MissingFormatArgumentException;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserComments {
    private final List<UserComment> values;

    public static UserComments from(List<UserComment> comments) {
        if (CollectionUtils.isEmpty(comments)) return new UserComments(Collections.emptyList());
        return new UserComments(comments);
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class UserComment {
        private final int id;
        private final UserId userId;
        private final Name name;
        private final MailAddress mailAddress;
        private final Comment comment;
        private final DateTime dateTime;

        public static UserComment from(
                int id,
                String userId,
                String name,
                String mailAddress,
                String comment,
                LocalDateTime dateTime) {
            return new UserComment(
                    id,
                    UserId.from(userId),
                    Name.from(name),
                    MailAddress.from(mailAddress),
                    Comment.from(comment),
                    DateTime.from(dateTime)
            );
        }
    }
}