package me.hupeng.web.wechat.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by admin on 2017/8/12.
 */
@Table("relationship")
public class Relationship extends BasePojo {
    @Id
    private int id;

    @Column("from_user")
    private int fromUser;

    @Column("to_user")
    private int toUser;

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
}
