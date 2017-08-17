package me.hupeng.web.wechat.module;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.lang.util.NutMap;

public abstract class BaseModule {

    /**
     * 注入同名的一个ioc对象
     */
    @Inject
    protected Dao dao;


    protected NutMap getSuccessResult(Object data) {
        return new NutMap().setv("rst", 0).setv("msg", "ok").setv("data", data);
    }

    protected NutMap getFailResult(int rst, String msg, Object data){
        return new NutMap().setv("rst", rst).setv("msg", msg).setv("data", data);
    }
}