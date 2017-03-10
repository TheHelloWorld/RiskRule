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
		//加载规则文
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
//		Integer version = 4; // 4
//		String ruleName = "test"; // M00001_t0
//		String rulePath = "/Users/liuzikun/risk/test_4.drl"; // H:/rule/M00001t0_4.drl
//		List<String> list = new ArrayList<>();
//		list.add(rulePath);
//		initRuleEngine(ruleName, version, list);
//
//		Map<String,String> map  = new HashMap<>();
//		map.put("version",String.valueOf(version));
//		map.put("ruleName",ruleName);
//		map.put("path","/Users/liuzikun/risk/test_4.drl");
//		RuleObj obj = new RuleObj();
//		System.out.println(obj.getRules());
//		exeRuleEngine(map,obj);
//		System.out.println(obj.getRules());
		double amt = 100000;
		if(amt > 5000){
			System.out.println(1);
		}else if(amt > 4000){
			System.out.println(2);
		}else if(amt > 3000){
			System.out.println(3);
		}else if(amt > 2000){
			System.out.println(4);
		}else{
			System.out.println(5);
		}
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.DAY_OF_YEAR));
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		System.out.println(cal.get(Calendar.DAY_OF_WEEK_IN_MONTH));



	}

	//社保缴纳情况
	public double getSocialSecurity(RuleObj obj){
		Map<String,Object> map = obj.getRuleDetail();
		String customerScialSecurity = String.valueOf(map.get("customer_social_security"));

		String systemSocialSecurity = String.valueOf(map.get("system_social_security"));
		if(customerScialSecurity != null && systemSocialSecurity != null){
			if(customerScialSecurity.equals(systemSocialSecurity)){
				double amt = Double.valueOf(String.valueOf(map.get("amt")));
				if(amt > 5000){
					return 0.5;
				}else if(amt > 4000){
					return 0.4;
				}else if(amt > 3000){
					return 0.3;
				}else if(amt > 2000){
					return 0.2;
				}else{
					return 0.1;
				}
			}
		}
		// 不符合情况
		return 0.0;
	}

	// 公积金缴纳情况
	public double getPublicFund(RuleObj obj){
		Map<String,Object> map = obj.getRuleDetail();
		String customerPublicFund = String.valueOf(map.get("customer_public_fund"));

		String systemPublicFund = String.valueOf(map.get("system_public_fund"));
		if(customerPublicFund != null && systemPublicFund != null){
			if(customerPublicFund.equals(systemPublicFund)){
				double amt = Double.valueOf(String.valueOf(map.get("amt")));
				if(amt > 3000){
					return 1.0;
				}else if(amt > 2000){
					return 0.8;
				}else if(amt > 1000){
					return 0.6;
				}else if(amt > 500){
					return 0.4;
				}else{
					return 0.2;
				}
			}
		}
		// 不符合情况
		return 0.0;
	}

	/** 判断时间是否连续 **/
 	public boolean getTiemConsecutiveFlag(Set<Long> set ,int size,String type) {
		if (set.size() != size) {
			return false;
		}
		List<Long> list = new ArrayList<>(size);
		list.addAll(set);

		Collections.sort(list);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(list.get(0)));
		Long max_time = list.get(list.size() - 1);
		switch (type) {
			case "Year":
				cal.add(Calendar.YEAR, size - 1);
				break;
			case "Month":
				cal.add(Calendar.MONTH, size - 1);
				break;
			case "Day":
				cal.add(Calendar.DAY_OF_YEAR, size - 1);
				break;
			case "Hour":
				cal.add(Calendar.HOUR, size - 1);
				break;
			case "Minute":
				cal.add(Calendar.MINUTE, size - 1);
				break;
			case "Second":
				cal.add(Calendar.SECOND, size - 1);
				break;
		}
		// 判断最大时间相等
		if (cal.getTimeInMillis() == max_time) {
			return false;
		}
		return true;

	}

}