package cs.jou.infrastructure.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName tbl_comment_content
 */
@TableName(value ="tbl_comment_content")
@Data
public class CommentContent implements Serializable {
    /**
     * 
     */
    @TableId(value = "tbl_comment_index_id")
    private String tblCommentIndexId;

    /**
     * 
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * 
     */
    @TableField(value = "meta")
    private String meta;

    /**
     * 
     */
    @TableField(value = "device")
    private String device;

    /**
     * 
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 
     */
    @TableField(value = "deleted")
    private Integer deleted;

    /**
     * 
     */
    @TableField(value = "message")
    private byte[] message;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}