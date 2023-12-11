package TaskService.mappers;

import TaskService.dto.CommentDTO;
import TaskService.models.Comment;
import org.mapstruct.Mapper;

@Mapper
public interface CommentMapper {
    Comment toComment(CommentDTO commentDTO);
    CommentDTO toCommentDTO(Comment comment);
}
