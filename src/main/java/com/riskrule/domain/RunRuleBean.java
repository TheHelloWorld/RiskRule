package com.riskrule.domain;

import org.drools.KnowledgeBase;

public class RunRuleBean {
    /** 版本号 **/
	private Integer version;

    /** 规则引擎 **/
    private KnowledgeBase kBase;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

    public KnowledgeBase getkBase() {
        return kBase;
    }

    public void setkBase(KnowledgeBase kBase) {
        this.kBase = kBase;
    }


	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("version : " + this.version);
		sb.append("__");
        sb.append("kBase" + this.getkBase());
		return sb.toString();
	}


}