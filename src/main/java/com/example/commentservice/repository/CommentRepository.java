package com.example.commentservice.repository;

import com.example.commentservice.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    public List<Comment> findBypostID(String postId);
    public List<Comment> findBypostID(String postId, Pageable page);
}
