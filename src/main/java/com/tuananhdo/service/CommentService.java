package com.tuananhdo.service;

import com.tuananhdo.payload.CommentDTO;

import java.util.List;

public interface CommentService {

    //    PostResponse getAllPost(int pageNo, int pageSize,String sortBy,String sortDir);
    CommentDTO createComment(long id, CommentDTO commentDTO);

    CommentDTO updateComment(long postId, long commentId, CommentDTO commentDTO);

    List<CommentDTO> getCommentByPostId(long postId);

    CommentDTO getCommentById(long postId, long commentId);

    void deleteCommentById(long postId,long commentId);
}
