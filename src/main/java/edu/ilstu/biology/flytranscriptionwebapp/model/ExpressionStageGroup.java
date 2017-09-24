package edu.ilstu.biology.flytranscriptionwebapp.model;

import java.util.List;

public class ExpressionStageGroup {
	private String groupTitle;
	private List<String> expressionStageList;

	public String getGroupTitle() {
		return groupTitle;
	}

	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}

	public List<String> getExpressionStageList() {
		return expressionStageList;
	}

	public void setExpressionStageList(List<String> expressionStageList) {
		this.expressionStageList = expressionStageList;
	}

}
