package com.example.commentservice.controller;

import com.example.commentservice.entity.Comment;
import com.example.commentservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Comment> postComment(@Valid @RequestBody Comment comment, @PathVariable("postId") String postId) {
        return new ResponseEntity<>(commentService.postComment(comment, postId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> allComments(){
        return new ResponseEntity<List<Comment>>(commentService.allComments(), HttpStatus.ACCEPTED);
    }

}
