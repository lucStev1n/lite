package cs.jou.domain.comment.repository;

import cs.jou.domain.comment.model.entity.CommentCmd;

public interface ICommentRepository {
    void exec(CommentCmd.Create cmd);
}
