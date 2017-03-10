package com.riskrule.util;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.*;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroolUtil {
	private static final Logger logger = LoggerFactory.getLogger(DroolUtil.class);
	/**
	 * 初始化加载drl文件,并返回运行类 RuleBase
	 * @param
	 * @return
	 */
	public static KnowledgeBase initEngine(List<String> path) {
		KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
		try {
			KnowledgeBuilder knowledgeBuilder = getKnowledgeBuilder(path);
			kBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
			return kBase;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static KnowledgeBuilder getKnowledgeBuilder(List<String> list){
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		for(String path : list){
			File file= new File(path);
			Resource resource = ResourceFactory.newFileResource(file);
			kbuilder.add(resource, ResourceType.DRL);
		}
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error: errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		return kbuilder;
	}

	public static void readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

	}

	private static List<Reader> readRuleFromDrlFile(List<String> drlFilePath) throws Exception {
		if (null == drlFilePath || 0 == drlFilePath.size()) {
			return null;
		}
		List<Reader> readers = new ArrayList<Reader>();
		for (String ruleFilePath : drlFilePath) {
			System.out.println(ruleFilePath);
			readers.add(new FileReader(new File(ruleFilePath)));
		}
		return readers;
	}

	/**
	 * 执行规则文件
	 * @param ruleBase
	 * @param obj
	 */
	public static void executeRuleEngine(KnowledgeBase ruleBase, Object obj) {
		if(null == ruleBase.getKnowledgePackages() || 0 == ruleBase.getKnowledgePackages().size()) {
			System.out.println("规则文件为空");
			return;
		}
		StatefulKnowledgeSession statefulSession = ruleBase.newStatefulKnowledgeSession();
		statefulSession.insert(obj);
		//执行
		statefulSession.fireAllRules();
		statefulSession.dispose();
	}

}