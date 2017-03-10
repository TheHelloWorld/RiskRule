package com.riskrule.bean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RuleObj implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8944210995174253821L;

	private Map<String, Object> ruleDetail;

	private List<String> rules = new ArrayList<String>();

	private Double point;

	public Map<String, Object> getRuleDetail() {
		return ruleDetail;
	}

	public void setRuleDetail(Map<String, Object> ruleDetail) {
		this.ruleDetail = ruleDetail;
	}

	public List<String> getRules() {
		return rules;
	}

	public void setRules(List<String> rules) {
		this.rules = rules;
	}

	public Double getPoint() {
		return point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}

	public void setRuleRecords(String rCode) {
		this.rules.add(rCode);
	}

	/**
	 * 将信息存入文件
	 * @Description 将信息存入文件
	 * @see
	 */
	public void saveInfile() {
		try {
			String filepath = String.valueOf(ruleDetail.get("filepath"));
			File file = new File(filepath);
			if (!file.exists()) {
				file.createNewFile();
			}
			// 添加true标识,表示为追加模式
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			writer.write(ruleDetail.get("CUSTOMER_NO") + "\r\n");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
