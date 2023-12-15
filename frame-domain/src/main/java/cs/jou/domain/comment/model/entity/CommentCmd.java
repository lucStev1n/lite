package cs.jou.domain.comment.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public class CommentCmd {
    private CommentCmd() {}

    @Data
    @Schema
    public static class Create {
        private String ip;
        private String meta;
        private String device;
        private String message;
    }
}
