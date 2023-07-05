package com.blogapi.service;

import com.blogapi.entity.Comment;
import com.blogapi.entity.Post;
import com.blogapi.exception.ResourceNotFoundException;
import com.blogapi.paylod.CommentDto;
import com.blogapi.repository.CommentRepository;
import com.blogapi.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepository PostRepo;
    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDto findByCommentsId(long postId, long id) {
        Post post = PostRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(postId));

        Comment comment = commentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        CommentDto commentDto = mapToDTO(comment);

        return commentDto;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = PostRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException(postId));
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setPost(post);

        Comment saveComment = commentRepo.save(comment);

        CommentDto dto = new CommentDto();
        dto.setId(saveComment.getId());
        dto.setName(saveComment.getName());
        dto.setEmail(saveComment.getEmail());
        dto.setBody(saveComment.getBody());

        return dto;
    }

    @Override
    public List<CommentDto> findByPostId(long postId) {
        PostRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException(postId));

        List<Comment> comments = commentRepo.findByPostId(postId);
        List<CommentDto> collect = comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());


        return collect;
    }

    @Override
    public void deleteByPostId(long postId, long id) {
        Post post = PostRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(postId));

        commentRepo.deleteById(id);

    }

    @Override
    public CommentDto updateByCommentsId(long postId, long id, CommentDto commentbody) {
        Post post = PostRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException(postId));
        Comment comment = commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        comment.setName(commentbody.getName());
        comment.setEmail(commentbody.getEmail());
        comment.setBody(commentbody.getBody());
        Comment save = commentRepo.save(comment);
        CommentDto commentDto = mapToDTO(save);

        return commentDto;
    }


    CommentDto mapToDTO(Comment comment) {
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        return commentDto;
    }
    Comment mapToEntity(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }
}
