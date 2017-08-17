package me.hupeng.web.wechat;


import me.hupeng.web.wechat.bean.Relationship;
import me.hupeng.web.wechat.bean.User;
import me.hupeng.web.wechat.util.Toolkit;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

public class MainSetup implements Setup{
    public static Ioc ioc;
    public static Dao dao;
    @Override
    public void destroy(NutConfig arg0) {

    }

    @Override
    public void init(NutConfig conf) {
        MainSetup.ioc = conf.getIoc();
        dao = ioc.get(Dao.class);

        Daos.createTablesInPackage(dao, "me.hupeng.web.wechat", false);

        if (dao.count(User.class) == 0){
            User user = new User();
            user.setUsername("admin");
            Toolkit.setPasswd(user,"123456");
            user.setUserPhoto("http://ounlqs4hi.bkt.clouddn.com/admin.jpg");
            user.setPhone("");
            user.setEmail("");
            user.setSex(true);
            dao.insert(user);

            user = new User();
            user.setUsername("admin1");
            Toolkit.setPasswd(user,"123456");
            user.setUserPhoto("http://ounlqs4hi.bkt.clouddn.com/admin1.jpg");
            user.setPhone("");
            user.setEmail("");
            user.setSex(true);
            dao.insert(user);

            user = new User();
            user.setUsername("admin2");
            Toolkit.setPasswd(user,"123456");
            user.setUserPhoto("http://ounlqs4hi.bkt.clouddn.com/admin2.jpg");
            user.setPhone("");
            user.setEmail("");
            user.setSex(true);
            dao.insert(user);

            user = new User();
            user.setUsername("admin3");
            Toolkit.setPasswd(user,"123456");
            user.setUserPhoto("http://ounlqs4hi.bkt.clouddn.com/admin3.jpg");
            user.setPhone("");
            user.setEmail("");
            user.setSex(true);
            dao.insert(user);

            Relationship relationship = new Relationship();
            relationship.setFromUser(1);
            relationship.setToUser(2);
            dao.insert(relationship);

            relationship = new Relationship();
            relationship.setFromUser(1);
            relationship.setToUser(3);
            dao.insert(relationship);

            relationship = new Relationship();
            relationship.setFromUser(1);
            relationship.setToUser(4);
            dao.insert(relationship);

            relationship = new Relationship();
            relationship.setFromUser(4);
            relationship.setToUser(1);
            dao.insert(relationship);

            relationship = new Relationship();
            relationship.setFromUser(3);
            relationship.setToUser(1);
            dao.insert(relationship);

            relationship = new Relationship();
            relationship.setFromUser(2);
            relationship.setToUser(1);
            dao.insert(relationship);
        }
    }
}