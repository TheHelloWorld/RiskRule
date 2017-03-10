package com.riskrule.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class RiskObj {

	private Map<String,Object> map;

	private List<String> list;

	private Double point;

	/**
	 * @return the map
	 */
	public Map<String, Object> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	/**
	 * @return the list
	 */
	public List<String> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<String> list) {
		this.list = list;
	}

	/**
	 * @return the point
	 */
	public Double getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(Double point) {
		this.point = point;
	}

	public void setRuleRecords(String rCode) {
		if(this.list == null || this.list.size() == 0){
			List<String> li = new ArrayList<String>();
			li.add(rCode);
			this.list = li;
		}
		if(this.list != null && list.size() > 0){
			this.list.add(rCode);
		}
	}

}