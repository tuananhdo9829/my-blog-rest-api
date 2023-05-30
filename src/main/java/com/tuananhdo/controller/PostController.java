package com.tuananhdo.controller;

import com.tuananhdo.payload.PostDTO;
import com.tuananhdo.payload.PostResponse;
import com.tuananhdo.service.PostService;
import com.tuananhdo.utils.AppConstants;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(name = "REST APIs for Post Resource")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }


    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable("postId") long postId) {
        return new ResponseEntity<>(postService.updatePost(postDTO, postId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                                   @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                                   @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                                   @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return ResponseEntity.ok(postService.getAllPost(pageNo, pageSize, sortBy, sortDir));
    }


    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "postId") long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @SecurityRequirement(name = "Bear Authentication1")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePostById(@PathVariable(name = "postId") long postId) {
        postService.deletePostById(postId);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable(name = "categoryId") long categoryId) {
        return ResponseEntity.ok(postService.getPostsByCategory(categoryId));
    }
}
