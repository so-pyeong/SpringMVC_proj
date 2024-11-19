package kopo.aisw.basic_mvc.comment.vo;

import java.sql.Date;

public class CommentVO {
    private int commentNO;
    private int parentNO;
    private String content;
    private Date writeDate;

    public int getCommentNO() {
        return commentNO;
    }

    public void setCommentNO(int commentNO) {
        this.commentNO = commentNO;
    }

    public int getParentNO() {
        return parentNO;
    }

    public void setParentNO(int parentNO) {
        this.parentNO = parentNO;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }
}
