package com.blogapi.service;

import com.blogapi.paylod.PostDto;

import java.util.List;

public interface PostService {
    public PostDto createPost(PostDto postDto);

    public PostDto  getPostById(long id);



  public List<PostDto> getAllPosts();

    void deletepost(long id);




    PostDto updatePost(PostDto dto, long id);

    List<PostDto> getAllPostsPaging(int pageNo, int pagesize, String sortedBy, String sorteDir);
}
