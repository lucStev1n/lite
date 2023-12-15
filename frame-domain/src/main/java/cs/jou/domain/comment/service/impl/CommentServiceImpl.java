package cs.jou.domain.comment.service.impl;

import cs.jou.domain.comment.model.entity.CommentCmd;
import cs.jou.domain.comment.repository.ICommentRepository;
import cs.jou.domain.comment.service.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {
    private final ICommentRepository repository;

    @Override
    public void save(CommentCmd.Create cmd) {
        repository.exec(cmd);
    }
}
