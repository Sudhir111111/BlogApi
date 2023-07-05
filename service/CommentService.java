package com.blogapi.service;

import com.blogapi.paylod.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto findByCommentsId(long postId, long id) ;


    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> findByPostId(long postId);

    void deleteByPostId(long postId, long id);

    CommentDto updateByCommentsId(long postId, long id, CommentDto commentbody);
}
