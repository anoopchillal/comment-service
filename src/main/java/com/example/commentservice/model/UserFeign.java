package com.example.commentservice.model;

import com.example.commentservice.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserFeign {
    private User user;
    private Comment comment;
}
