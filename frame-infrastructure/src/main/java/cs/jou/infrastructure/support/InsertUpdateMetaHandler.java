package cs.jou.infrastructure.support;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import cs.jou.common.support.WebContext;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class InsertUpdateMetaHandler implements MetaObjectHandler {
    final WebContext context;
    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createTime", LocalDate.now());
        metaObject.setValue("createBy", context.getUser().getName());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime", LocalDate.now());
        metaObject.setValue("updateBy", context.getUser().getName());
    }
}
