package com.riskrule.util;

import com.ql.util.express.ExpressRunner;

/**
 * @author liuzikun@lxfintech.com
 * @Title: ExpressUtil
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: lxfintech.com
 * @Created on 2017/5/10 14:33
 */
public class ExpressUtil {

    /**
     * 执行传过来的表达式并返回结果
     * @param express 要执行的表达式
     * @return
     */
    public Object executeByExpress(String express) {
        try {
            ExpressRunner expressRunner = new ExpressRunner();
            return expressRunner.execute(express,null,null,true,false);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
