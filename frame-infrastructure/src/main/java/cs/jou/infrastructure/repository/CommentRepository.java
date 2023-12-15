package cs.jou.infrastructure.repository;

import cs.jou.domain.comment.model.entity.CommentCmd;
import cs.jou.domain.comment.repository.ICommentRepository;
import cs.jou.infrastructure.pojo.CommentContent;
import cs.jou.infrastructure.service.CommentContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepository implements ICommentRepository {
    final CommentContentService dao;

    @Override
    public void exec(CommentCmd.Create cmd) {
        dao.save(parse(cmd));
    }

    CommentContent parse(Object cmd) {
        CommentContent db = new CommentContent();

        BeanUtils.copyProperties(cmd, db);

        return db;
    }
}
