package com.zh.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_comment")
@Data
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String email;
    private String content; //评论内容
    private String avatar; //头像

    @Temporal(TemporalType.TIMESTAMP)
    private Date creatTime;
    @ManyToOne
    private Blog blog;
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComment = new ArrayList<>();
    @ManyToOne
    private Comment parentComment;
    private Boolean adminComment;
}
