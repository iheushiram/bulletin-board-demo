package com.example.demo.application.usecase;

import com.example.demo.application.form.CommentForm;
import com.example.demo.domain.model.UserComment;
import com.example.demo.domain.model.UserCommentRepository;
import com.example.demo.domain.model.UserComments;
import com.example.demo.domain.model.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommentUseCase {
    private final UserCommentRepository repository;
    /**
     * ユーザの書き込みをDBに反映し、表示するデータをプレゼンテーション層に渡す
     * @param commentForm ユーザの入力データ
     * @return 表示するデータ
     */
    public void write(CommentForm commentForm, User user) {
        UserComment userComment = UserComment.from(
                user.getUsername(),
                commentForm.getName(),
                commentForm.getMailAddress(),
                commentForm.getComment()
        );

    repository.save(userComment);
    }

    /**
     * 投稿の取得
     * @return 投稿のリスト
     */
    public UserComments read() {
        return repository.select();
    }

    public UserComments read(UserId userId) {
        return repository.select(userId);
    }
}
