package com.example.demo.domain.model;

import com.example.demo.domain.type.Comment;
import com.example.demo.domain.type.MailAddress;
import com.example.demo.domain.type.Name;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserComment {
    private final UserId userId;
    private final Name name;
    private final MailAddress mailAddress;
    private final Comment comment;

    public static UserComment from(String name, String userId, String mailAddress, String comment) {
        return new UserComment(
                UserId.from(userId),
                Name.from(name),
                MailAddress.from(mailAddress),
                Comment.from(comment)
        );
    }

    /**
     * 名前が[!omikuji]ならば、おみくじの結果を返す
     * そうでないならNameをそのまま返す
     * @return 名前
     */
    public Name getName() {
        if(!name.equals("!omikuji")) return name;

        int random = new Random().nextInt(3);

        switch (random) {
            case 0:
                return Name.from("大吉");
            case 1:
                return Name.from("中吉");
            default:
                return Name.from("庄吉");
        }
    }
}
