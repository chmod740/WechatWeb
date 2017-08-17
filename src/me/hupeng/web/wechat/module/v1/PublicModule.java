package me.hupeng.web.wechat.module.v1;

import me.hupeng.web.wechat.bean.Relationship;
import me.hupeng.web.wechat.bean.User;
import me.hupeng.web.wechat.module.BaseModule;
import me.hupeng.web.wechat.util.Toolkit;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.random.R;
import org.nutz.mvc.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@IocBean
@Ok("json:{locked:'^password$|^salt$|^createTime$|^updateTime$'}")
@Fail("http:500")
@At("/v1/")
@Filters(@By(type = AccessKeyFilter.class ,args={"ioc:accessKeyFilter"}))
public class PublicModule extends BaseModule{

    @Filters
    @At("login/username/?/password/?")
    public Object login(String username, String password){
        if (username != null && password != null && !username.equals("") && !password.equals("")){
            User user = dao.fetch(User.class, Cnd.where("username", "=", username));
            if (user!= null && user.getPassword().equals(Toolkit.passwordEncode(password, user.getSalt()))){
                String ak = R.sg(64).next();
                user.setAk(ak);
                user.setUpdateTime(new Date(System.currentTimeMillis()));
                dao.update(user);
                return getSuccessResult(user);
            }
        }
        return getFailResult(-1, "用户名或者密码错误",null);
    }

    @At("get_relationship_list")
    public Object getRelationshipList(HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        List<Relationship> relationships = dao.query(Relationship.class, Cnd.where("from_user","=", user.getId()));
        List<User> users = new LinkedList<>();
        for (Relationship relationship: relationships){
            user = dao.fetch(User.class, Cnd.where("id","=",relationship.getToUser()));
            users.add(user);
        }
        return getSuccessResult(users);
    }

    @At("get_relationship_list_count")
    public Object getRelationshipListCount(HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        List<Relationship> relationships = dao.query(Relationship.class, Cnd.where("from_user","=", user.getId()));
        return getSuccessResult(relationships.size());
    }



    @At("check_user_login_status")
    public Object checkUserLoginStatus(HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        return getSuccessResult(user);
    }

    /**
     * 检查用户名是否时可用的
     * */
    @At("check_username_usable/?")
    public Object checkUsernameUsable(String username){
        User user = dao.fetch(User.class, Cnd.where("username", "=", username));
        if (user == null){
            return getSuccessResult(null);
        }else {
            return getFailResult(-1,"用户名不可用",null);
        }
    }

    /**
     * 注册方法
     * */
    @At("register/username/?/password/?")
    public Object register(String username, String password){
        User user = dao.fetch(User.class, Cnd.where("username", "=", username));
        boolean registerFlag = true;
        String registerMsg = "";
        if (!(user == null)){
            registerFlag = false;
            registerMsg = "用户名已经存在";
        }
        if (registerFlag){
            user = new User();
            user.setUsername(username);
            Toolkit.setPasswd(user,password);
            user.setUserPhoto("http://ounlqs4hi.bkt.clouddn.com/default_user_logo.png");
            user.setPhone("");
            user.setEmail("");
            String ak = R.sg(64).next();
            user.setAk(ak);
            user.setUpdateTime(new Date(System.currentTimeMillis()));
            dao.insert(user);
        }
        if (registerFlag){
            return getSuccessResult(user);
        }else {
            return getFailResult(-1,"注册失败",null);
        }
    }

    /**
     * 更新用户数据
     * */
    @At("update_user")
    public Object updateUser(@Param("nick_name")String nickName, @Param("sex")int sex, @Param("phone")String phone, @Param("email")String email, HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        if (nickName != null){
            user.setNickName(nickName);
        }
        user.setSex(sex == 0?false:true);
        if (phone!=null){
            user.setPhone(phone);
        }
        if (email != null){
            user.setEmail(email);
        }
        dao.update(user);
        return getSuccessResult(user);
    }
}
