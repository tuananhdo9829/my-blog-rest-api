package com.tuananhdo.service.impl;

import com.tuananhdo.entity.Comment;
import com.tuananhdo.entity.Post;
import com.tuananhdo.exception.BlogAPIException;
import com.tuananhdo.exception.ResourceNotFoundException;
import com.tuananhdo.payload.CommentDTO;
import com.tuananhdo.repository.CommentRepository;
import com.tuananhdo.repository.PostRepository;
import com.tuananhdo.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDTO createComment(long id, CommentDTO commentDTO) {
        Comment comment = mapToCommentEntity(commentDTO);
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Posts", "id", id));
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return mapToCommentDTO(newComment);
    }

    private CommentDTO mapToCommentDTO(Comment comment) {
        return mapper.map(comment, CommentDTO.class);
    }

    private Comment mapToCommentEntity(CommentDTO commentDTO) {
        return mapper.map(commentDTO, Comment.class);
    }


    @Override
    public CommentDTO updateComment(long postId, long commentId, CommentDTO commentDTO) {
        Comment comment = validatePostAndComment(postId, commentId);
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setContent(commentDTO.getContent());

        Comment updatedComment = commentRepository.save(comment);
        return mapToCommentDTO(updatedComment);
    }

    @Override
    public List<CommentDTO> getCommentByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream()
                .map(this::mapToCommentDTO).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(long postId, long commentId) {
        Comment comment = validatePostAndComment(postId, commentId);
        return mapToCommentDTO(comment);
    }

    @Override
    public void deleteCommentById(long postId, long commentId) {
        Comment comment = validatePostAndComment(postId, commentId);
        commentRepository.delete(comment);
    }

    private Comment validatePostAndComment(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Posts", "postId", postId));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comments", "commentId", commentId));
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        return comment;
    }
}
