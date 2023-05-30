package com.tuananhdo.service;

import com.tuananhdo.payload.PostDTO;
import com.tuananhdo.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostResponse getAllPost(int pageNo, int pageSize,String sortBy,String sortDir);
    PostDTO createPost(PostDTO postDTO);
    PostDTO updatePost(PostDTO postDTO,long id);
    PostDTO getPostById(long id);
    void deletePostById(long id);

    List<PostDTO> getPostsByCategory(Long categoryId);
}
