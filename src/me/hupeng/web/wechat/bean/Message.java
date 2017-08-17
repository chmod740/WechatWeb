package me.hupeng.web.wechat.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * Created by HUPENG on 2017/6/11.
 */
@Table("message")
public class Message extends BasePojo{
    @Id
    private int id;

    @Column("from_user")
    private int fromUser;

    @Column("to_user")
    private int toUser;


    @Column("send_time")
    private Date sendTime;

    @Column("message")
    private String message;

    @Column("send_result")
    private int sendResult;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromUser() {
        return fromUser;
    }

    public void setFromUser(int fromUser) {
        this.fromUser = fromUser;
    }

    public int getToUser() {
        return toUser;
    }

    public void setToUser(int toUser) {
        this.toUser = toUser;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSendResult() {
        return sendResult;
    }

    public void setSendResult(int sendResult) {
        this.sendResult = sendResult;
    }
}
