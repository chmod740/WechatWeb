package me.hupeng.web.wechat.module.v1;

import me.hupeng.web.wechat.bean.User;
import me.hupeng.web.wechat.module.BaseActionFilter;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.JsonFormat;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.View;
import org.nutz.mvc.view.UTF8JsonView;
import org.nutz.mvc.view.ViewWrapper;

/**
 * Created by HUPENG on 2017/8/12.
 */
@IocBean
public class AccessKeyFilter extends BaseActionFilter {

    @Override
    public View match(ActionContext actionContext) {
        //获取ak ts
        String ak = actionContext.getRequest().getParameter("ak");

        //验证ak
        if (!checkAccessKey(actionContext,ak)){
            //失败返回登录状态无效
            return new ViewWrapper(new UTF8JsonView(new JsonFormat(true)), getFailResult(-100,"用户登录状态失效，请重新登录",null));
        }
        return null;
    }

    /**
     * 验证用户的ak
     * 验证成功写入request域，并返回true
     * 否则直接返回false
     * */
    private boolean checkAccessKey(ActionContext actionContext,String ak){
        if(ak!=null){
            User user = dao.fetch(User.class, Cnd.where("ak","=",ak));
            if(user != null){
                //写入user到request域
                actionContext.getRequest().setAttribute("user",user);
                return true;
            }
        }
        return false;
    }

}
