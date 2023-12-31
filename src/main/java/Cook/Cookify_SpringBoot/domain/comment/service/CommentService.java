package Cook.Cookify_SpringBoot.domain.comment.service;


import Cook.Cookify_SpringBoot.domain.comment.entity.Comment;
import Cook.Cookify_SpringBoot.domain.comment.dto.CommentRequestDto;
import Cook.Cookify_SpringBoot.domain.comment.dto.CommentResponseDto;
import Cook.Cookify_SpringBoot.domain.comment.exception.CommentException;

import java.util.List;


public interface CommentService {
    Comment save(Long recipeId, CommentRequestDto commentRequestDto);

    Comment saveReComment(Long recipeId, Long parentId, CommentRequestDto commentRequestDto);

    List<CommentResponseDto> getComments(Long recipeId);


    void update(Long id, CommentRequestDto commentRequestDto);

    void remove(Long id) throws CommentException;
}
