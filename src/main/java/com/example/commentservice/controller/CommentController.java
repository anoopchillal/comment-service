package com.example.commentservice.controller;

import com.example.commentservice.entity.Comment;
import com.example.commentservice.model.CommentDto;
import com.example.commentservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> postComment(@Valid @RequestBody Comment comment, @PathVariable("postId") String postId) {
        return new ResponseEntity<>(commentService.postComment(comment, postId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<Comment>> allComments(){
        return new ResponseEntity<List<Comment>>(commentService.allComments(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> findByCommentId(@PathVariable("postId") String postId, @PathVariable("commentId") String commentId){
        return new ResponseEntity<>(commentService.findByCommentId(commentId), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@RequestBody @Valid Comment comment, @PathVariable("postId") String postId, @PathVariable("commentId") String commentId){
        return new ResponseEntity<>(commentService.commentUpdate(comment,postId,commentId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentBytId(@PathVariable("postId") String postId, @PathVariable("commentId") String commentId){
        return new ResponseEntity<>(commentService.deleteCommentById(commentId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{postId}/comments/count")
    public ResponseEntity<Integer> commentCount(@PathVariable("postId") String postId){
        return  new ResponseEntity<>(commentService.countComments(postId), HttpStatus.ACCEPTED);
    }
}
