package cs.jou.infrastructure.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cs.jou.infrastructure.pojo.CommentContent;
import cs.jou.infrastructure.service.CommentContentService;
import cs.jou.infrastructure.mapper.CommentContentMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【tbl_comment_content】的数据库操作Service实现
* @createDate 2023-12-16 02:03:17
*/
@Service
public class CommentContentServiceImpl extends ServiceImpl<CommentContentMapper, CommentContent>
    implements CommentContentService{

}




