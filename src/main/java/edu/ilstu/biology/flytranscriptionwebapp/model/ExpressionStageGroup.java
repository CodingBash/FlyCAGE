package edu.ilstu.biology.flytranscriptionwebapp.model;

import java.util.List;

public class ExpressionStageGroup {
	private String groupTitle;
	private String groupId;
	private List<ExpressionStage> expressionStageList;

	public String getGroupTitle() {
		return groupTitle;
	}

	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public List<ExpressionStage> getExpressionStageList() {
		return expressionStageList;
	}

	public void setExpressionStageList(List<ExpressionStage> expressionStageList) {
		this.expressionStageList = expressionStageList;
	}

}
