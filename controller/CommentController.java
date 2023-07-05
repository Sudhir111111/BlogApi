package com.blogapi.controller;

import com.blogapi.paylod.CommentDto;
import com.blogapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/posts")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService)
    {
        this.commentService = commentService;
    }

    //localhost:8080/api/posts/1/comments
    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value =
                                      "postId") long postId,   @RequestBody CommentDto commentDto)
    {
        CommentDto comment = commentService.createComment(postId, commentDto);

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDto>>findByPostId(@PathVariable(value = "postId")  Long postId)
    {
        List<CommentDto> commentsByPostId = commentService.findByPostId(postId);
        return new ResponseEntity<>(commentsByPostId,HttpStatus.OK);
    }
    //localhost:8080/api/posts/3/comments/3
    @DeleteMapping("/{postId}/comments/{id}")
    public ResponseEntity<String> deleteByPostId(@PathVariable(value= "postId") long postId ,@PathVariable("id") long id){

        commentService.deleteByPostId(postId,id);
        return new ResponseEntity<>("delete sucess",HttpStatus.OK);

    }
    @GetMapping ("/{postId}/comments/{id}")
    public  ResponseEntity<CommentDto> findByCommentsId (@PathVariable("postId") long postId,@PathVariable("id") long id){

        CommentDto dto =commentService.findByCommentsId(postId,id);
        return  new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @PutMapping ("/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateByCommentsId(@PathVariable("postId") long postId,@PathVariable("id") long id
                                                           ,@RequestBody CommentDto commentbody)
    {
       CommentDto commentDto= commentService.updateByCommentsId(postId,id,commentbody);
       return new ResponseEntity<>(commentDto,HttpStatus.OK);

    }


}