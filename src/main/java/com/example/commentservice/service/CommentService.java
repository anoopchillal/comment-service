package com.example.commentservice.service;

import com.example.commentservice.entity.Comment;
import com.example.commentservice.exception.CommentNotFoundException;
import com.example.commentservice.feign.LikeService;
import com.example.commentservice.feign.UserService;
import com.example.commentservice.model.CommentDto;
import com.example.commentservice.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    LikeService likeService;

    @Autowired
    UserService UserFeign;

    public CommentDto postComment(Comment comment, String postId) {
        comment.setPostID(postId);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        commentRepository.save(comment);
        return feignModel(comment);
    }

    public CommentDto feignModel(Comment comment) {
        return new CommentDto(comment.getCommentID(),
                UserFeign.findByID(comment.getCommentedBy()).getFirstName(),
                comment.getComment(), comment.getCreatedAt(), comment.getUpdatedAt(),
                likeService.countLike(comment.getCommentID()));
    }


    public List<Comment> allComments() {
        return commentRepository.findAll();
    }

    public CommentDto findByCommentId(String commentId) {

        Comment comment = commentRepository.findById(commentId).get();
        try {
            return feignModel(comment);
        } catch (Exception e) {
            throw new CommentNotFoundException("Comment Not Found");
        }
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

    public int countComments(String postId) {
        int count=commentRepository.findBypostID(postId).size();
        return count;
    }

    public Comment commentUpdate(Comment comment, String postId, String commentId) {
        comment.setCommentID(commentId);
        comment.setUpdatedAt(LocalDateTime.now());
        comment.setCreatedAt(commentRepository.findById(commentId).get().getCreatedAt());
        comment.setPostID(postId);
        return commentRepository.save(comment);
    }
}
