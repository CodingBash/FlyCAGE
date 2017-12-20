package edu.ilstu.biology.flytranscriptionwebapp.model;

public class ExpressionStage {
	private String expressionStageTitle;
	private String expressionStageId;
	private Integer expressionStageNumericalId; // TODO: Move away from
												// expressionStageId:String

	public String getExpressionStageTitle() {
		return expressionStageTitle;
	}

	public void setExpressionStageTitle(String expressionStageTitle) {
		this.expressionStageTitle = expressionStageTitle;
	}

	public String getExpressionStageId() {
		return expressionStageId;
	}

	public void setExpressionStageId(String expressionStageId) {
		this.expressionStageId = expressionStageId;
	}

	public Integer getExpressionStageNumericalId() {
		return expressionStageNumericalId;
	}

	public void setExpressionStageNumericalId(Integer expressionStageNumericalId) {
		this.expressionStageNumericalId = expressionStageNumericalId;
	}

}
