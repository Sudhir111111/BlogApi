package com.blogapi.controller;

import com.blogapi.paylod.PostDto;
import com.blogapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    PostService postService;

//    public PostController(PostService postService) {
//        this.postService = postService;
//    }
 @PreAuthorize("hasRole('ADMIN')")
    @PostMapping

    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult result){
        if(result.hasErrors()){

            return  new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

       PostDto savedDto = postService.createPost(postDto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }
   // localhost:8080/api/posts/1   path parameter
    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        PostDto postById = postService.getPostById(id);
        return new ResponseEntity<>(postById,HttpStatus.OK) ;
    }

    @GetMapping
    public List<PostDto> getAllPosts(){
       List<PostDto> postdtos =postService.getAllPosts();

       return postdtos;
   }
    @PreAuthorize("hasRole('ADMIN')")

    @DeleteMapping("{id}")

    public ResponseEntity<String>deletepost(@PathVariable("id") long id){ //status code is 200

        postService.deletepost(id);
        return new ResponseEntity<>("delete sucess",HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<PostDto>updatePost(@PathVariable("id") long id,@RequestBody PostDto dto){
        PostDto dtos=postService.updatePost(dto,id);
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }


    //localhost:8080/api/posts?pageNo=1
                            // ?pageNo=1& pagesize=3&sortedBy=title&sorteDir=desc
//    @GetMapping
//    public List<PostDto> getAllPostsPaging(@RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
//                                      @RequestParam(value = "pagesize",defaultValue = "5",required = false) int pagesize,
//                                           @RequestParam(value = "sortedBy",defaultValue = "id",required = false) String sortedBy,
//                                       @RequestParam(value = "sorteDir",defaultValue = "5",required = false) String sorteDir)
//
//    {
//        List<PostDto> allPostsPaging = postService.getAllPostsPaging(pageNo, pagesize,sortedBy,sorteDir);
//
//        return allPostsPaging;
//    }

}
