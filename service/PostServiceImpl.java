package com.blogapi.service;

import com.blogapi.entity.Post;
import com.blogapi.exception.ResourceNotFoundException;
import com.blogapi.paylod.PostDto;
import com.blogapi.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{
    @Autowired

  private  PostRepository PostRepo;
    @Autowired
    private ModelMapper modelMapper;

//    public PostServiceImpl(PostRepository postRepo,ModelMapper modelMapper) {
//       this. PostRepo = postRepo;
    //this.modelMapper=modelMapper;
//    }

    @Override
    public PostDto createPost(PostDto postDto) {


        Post pa = mapToEntity(postDto);

        Post save = PostRepo.save(pa);

        PostDto postDto1 = mapToDto(save);


        return postDto1;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = PostRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(id)
        );
        PostDto postDto = mapToDto(post);
        return postDto;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = PostRepo.findAll();
        List<PostDto> postDtos = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public void deletepost(long id) {
        PostRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(id));

        PostRepo.deleteById(id);
    }

    @Override
    public PostDto updatePost(PostDto dto, long id) {
        Post post = PostRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        Post updateContent = mapToEntity(dto);

          updateContent.setId(post.getId());
        Post saved = PostRepo.save(updateContent);
        PostDto postDto = mapToDto(saved);


        return postDto;
    }

    @Override
    public List<PostDto> getAllPostsPaging(int pageNo, int pagesize, String sortedBy, String sorteDir) {
        Sort sort  = sorteDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortedBy).ascending() : Sort.by(sortedBy).descending();

        PageRequest pageable = PageRequest.of(pageNo,pagesize,sort);
        Page<Post> posts = PostRepo.findAll(pageable);
        List<Post> content = posts.getContent();
        List<PostDto> postDtos = content.stream().map(post -> mapToDto(post)).collect(Collectors.toList());


        return postDtos;
    }


    PostDto mapToDto(Post post){
        PostDto postDto1 = modelMapper.map(post, PostDto.class);

//        PostDto postDto1=new PostDto();
//        postDto1.setId(post.getId());
//        postDto1.setTitle(post.getTitle());
//        postDto1.setDescription(post.getDescription());
//        postDto1.setContent(post.getContent());


        return postDto1;
    }

Post mapToEntity(PostDto postDto){
    Post pa = modelMapper.map(postDto, Post.class);
//    Post pa=new Post();
//    pa.setTitle(postDto.getTitle());
//    pa.setDescription(postDto.getDescription());
//    pa.setContent(postDto.getContent());

    return pa;
}
}
