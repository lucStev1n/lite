package cs.jou.domain.comment.service;

import cs.jou.domain.comment.model.entity.CommentCmd;
import cs.jou.domain.comment.repository.ICommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ICommentRepository repository;

    public void save(CommentCmd.Create cmd) {
        repository.exec(cmd);
    }
}
