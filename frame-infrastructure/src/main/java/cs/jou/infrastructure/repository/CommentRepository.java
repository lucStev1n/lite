package cs.jou.infrastructure.repository;

import cs.jou.domain.comment.model.entity.CommentCmd;
import cs.jou.domain.comment.repository.ICommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepository implements ICommentRepository {


    @Override
    public void exec(CommentCmd.Create cmd) {

    }

}
