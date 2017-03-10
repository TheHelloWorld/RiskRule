package com.riskrule.util;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;

public class RuleBaseFacatory {

    private static KnowledgeBase kBase;

    /**
     * 单例模式获得kBase
     * @return
     */
    public static KnowledgeBase getKBase(){
        return null != kBase ? kBase : KnowledgeBaseFactory.newKnowledgeBase();
    }
}