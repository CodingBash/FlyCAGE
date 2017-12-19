package edu.ilstu.biology.flytranscriptionwebapp.model;

import java.util.List;

public class ExpressionStageOptions {

	private List<ExpressionStageGroup> expressionStageGroupList;

	private List<ExpressionStage> expressionStageList;

	public List<ExpressionStageGroup> getExpressionStageGroupList() {
		return expressionStageGroupList;
	}

	public void setExpressionStageGroupList(List<ExpressionStageGroup> expressionStageGroupList) {
		this.expressionStageGroupList = expressionStageGroupList;
	}

	public List<ExpressionStage> getExpressionStageList() {
		return expressionStageList;
	}

	public void setExpressionStageList(List<ExpressionStage> expressionStageList) {
		this.expressionStageList = expressionStageList;
	}

}
