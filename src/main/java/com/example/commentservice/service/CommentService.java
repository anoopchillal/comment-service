package com.example.commentservice.service;

import com.example.commentservice.entity.Comment;
import com.example.commentservice.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment postComment(Comment comment, String postId) {
        comment.setPostID(postId);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }


    public List<Comment> allComments() {
        return commentRepository.findAll();
    }

    public Comment findByCommentId(String commentId) {
        return commentRepository.findById(commentId).get();
    }

    public Comment updateComment(Comment comment, String postId, String commentId) {
        comment.setCommentID(commentId);
        comment.setUpdatedAt(LocalDateTime.now());
        comment.setCreatedAt(commentRepository.findById(commentId).get().getCreatedAt());
        comment.setPostID(postId);
        return commentRepository.save(comment);
    }

    public String deleteCommentById(String Id) {
        commentRepository.deleteById(Id);
        return "Delete ID "+Id+" from DB";
    }
}
