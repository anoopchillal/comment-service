package com.example.commentservice.model;

import com.example.commentservice.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class LikeFeign {
    private Comment comment;
    private int likesCount;

}
