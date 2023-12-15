package cs.jou.domain.comment.service;

import cs.jou.domain.comment.model.entity.CommentCmd;

public interface ICommentService {
    void save(CommentCmd.Create cmd);
}
