package com.riskrule.runmvel;

import java.io.Serializable;
import java.util.*;

import com.riskrule.bean.RuleObj;
import com.riskrule.domain.RunRuleBean;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;

import org.drools.builder.KnowledgeBuilder;
import org.drools.definition.KnowledgePackage;
import org.mvel2.MVEL;

import com.riskrule.util.DroolUtil;


/**
 * 规则引擎主类
 */
public class RunRule {

	private static Map<String,RunRuleBean> ruleMap = new HashMap<>();


	/**
	 * 用Mvel得出当前数据是否需要后续操作,Y为需要,N为不需要
	 * @param map
	 * @param str
	 * @return
	 */
	public String getResultByMvel(Map<String,String> map,String str){
		Map<String,Object> inputMap = new HashMap<>();
		inputMap.put("map", map);
		Serializable r = MVEL.compileExpression(str);
		String re = (String)MVEL.executeExpression(r, inputMap);
		return re;
	}


	/**
	 * 系统启动时加载规则引擎
	 * @param ruleName 规则名称
	 * @param version 版本号
	 * @param path 规则文件路径
	 */
	public static synchronized void initRuleEngine(String ruleName , Integer version , List<String> path){
		System.out.println("初始化流程 ： " + ruleName);
		System.out.println("初始化流程 version ： " + version);
		System.out.println("初始化流程 path ： " + path);
		RunRuleBean ruleBean = new RunRuleBean();
		KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
		KnowledgeBuilder knowledgeBuilder = null;
		//加载规则文件
		try {
			knowledgeBuilder = DroolUtil.getKnowledgeBuilder(path);
		} catch (Exception e) {
			e.printStackTrace();
		}

		kBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
		ruleBean.setVersion(version);
		ruleBean.setkBase(kBase);
		ruleMap.put(ruleName, ruleBean);
	}

	/**
	 * 刷新规则引擎
	 * @param ruleName 规则名称
	 * @param version 版本号
	 * @param path 规则文件路径
	 */
	public static void refRuleEngine(String ruleName , Integer version , List<String> path){
		System.out.println("刷新流程:" + ruleName);
		System.out.println("刷新流程 version:" + version);
		System.out.println("刷新流程 path:" + path);
		RunRuleBean ruleBean = ruleMap.get(ruleName);
		if(ruleBean != null ){
			KnowledgeBase kBase = ruleBean.getkBase();
			Collection<KnowledgePackage> packages = kBase.getKnowledgePackages();
			for(KnowledgePackage pg : packages){
				kBase.removeKnowledgePackage(pg.getName());
			}
		}
		initRuleEngine(ruleName, version, path);
	}


	/**
	 * 执行规则
	 * @param ruleName 规则名称
	 * @param obj 执行参数
	 */
	public static void frieRule(String ruleName , Object obj){
		RunRuleBean ruleBean = ruleMap.get(ruleName);
		if(ruleBean != null ){
			KnowledgeBase kBase = ruleBean.getkBase();
			DroolUtil.executeRuleEngine(kBase,obj);
		}

	}


	public static  String exeRuleEngine(Map<String , String> param , Object obj){
		String verStr = param.get("version");
		String ruleName = param.get("ruleName");
		Integer verInt = Integer.parseInt(verStr);

		RunRuleBean bean = ruleMap.get(ruleName);
		List<String> list = TransPath2List(param);

		String res = "Y";

		if(bean == null || bean.getVersion() == null){
			System.out.println("初始化流程");
			initRuleEngine(ruleName, verInt, list);
			res = "N";
		}
		bean = ruleMap.get(ruleName);
		if(bean.getVersion() < verInt){
			System.out.println("刷新流程");
			refRuleEngine(ruleName, verInt, list);
			res = "N";
		}

		frieRule(ruleName, obj);

		return res;
	}



	private static List<String> TransPath2List(Map<String, String> param) {
		String pathStr = param.get("path");
		String[] paths = pathStr.split(";");
		List<String> list = new ArrayList<>();
		for(String s : paths){
			list.add(s);
		}
		return list;
	}

	public static String returnMapStatus(){
		StringBuffer sb = new StringBuffer(" ");
		if(ruleMap == null)
			sb.append("ruleMap is null");
		sb.append("ruleMap size : " + ruleMap.size());
		Set<String> set = ruleMap.keySet();
		for(String s : set){
			sb.append("ruleName :  " + s );
			RunRuleBean bean = ruleMap.get(s);
			if(bean == null){
				sb.append("RunRuleBean  is null  ");
			}else{
				sb.append("RunRuleBean :  " + bean.toString());
			}
		}

		return sb.toString();
	}


	public static void main(String[] args){
		Integer version = 4; // 4
		String ruleName = "test"; // M00001_t0
		//String rulePath = "/Users/liuzikun/risk/test_4.drl"; // H:/rule/M00001t0_4.drl
		//String rulePath = "src/main/resources/drlFile/test.drl";
		String rulePath = RunRule.class.getResource("/drlFile/test.drl").getPath();
		List<String> list = new ArrayList<>();
		list.add(rulePath);
		// 初始化规则引擎
		initRuleEngine(ruleName, version, list);

		Map<String,String> map  = new HashMap<>();
		map.put("version",String.valueOf(version));
		map.put("ruleName",ruleName);
		map.put("path",rulePath);
		RuleObj obj = new RuleObj();
		Map ruleDetail = new HashMap<>();
		ruleDetail.put("customer_social_security","123");
		ruleDetail.put("system_social_security","123");
		ruleDetail.put("social_amt",7000.0);
		ruleDetail.put("customer_public_fund","312");
		ruleDetail.put("system_public_fund","312");
		ruleDetail.put("fund_amt",10000.0);
		obj.setRuleDetail(ruleDetail);
		System.out.println("rules before:"+obj.getRules());
		System.out.println("point before:"+obj.getPoint());
		// 执行规则
		exeRuleEngine(map,obj);
		System.out.println("rules after:"+obj.getRules());
		System.out.println("point after:"+obj.getPoint());

	}


}