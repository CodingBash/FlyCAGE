package edu.ilstu.biology.flytranscriptionwebapp.processor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.ilstu.biology.flytranscriptionwebapp.model.ExpressionStage;
import edu.ilstu.biology.flytranscriptionwebapp.model.ExpressionStageGroup;
import edu.ilstu.biology.flytranscriptionwebapp.model.ExpressionStageOptions;

@Component
public class ExpressionStageOptionsGenerator {

	/*
	 * TODO: Unsure if this should be autowired or passed in as a parameter. See
	 * how higher classes use this class
	 */
	@Autowired
	private RetrieveExpressionStages retrieveExpressionStages;

	/*
	 * TODO: At a production level, accept an enum of species with predefined
	 * groups, and map with corresponding options
	 * 
	 * TODO: Cleaner way of adding titles and ids
	 */
	public ExpressionStageOptions generateExpressionStageOptions() {

		List<String> allExpressionStagesRaw = retrieveExpressionStages.getDmelanogasterExpressionStages();
		ExpressionStageOptions expressionStageOptions = new ExpressionStageOptions();
		List<ExpressionStageGroup> expressionStageGroupList = new LinkedList<ExpressionStageGroup>();
		List<ExpressionStage> allExpressionStages = new ArrayList<ExpressionStage>(allExpressionStagesRaw.size());

		int idCounter = 0;
		for (String expressionStageString : allExpressionStagesRaw) {
			ExpressionStage expressionStage = new ExpressionStage();
			expressionStage.setExpressionStageTitle(expressionStageString);
			expressionStage.setExpressionStageId("stage" + idCounter);
			allExpressionStages.add(expressionStage);
			idCounter++;
		}

		// Add expressionStageList to returned object
		expressionStageOptions.setExpressionStageList(allExpressionStages);

		/*
		 * Add embryogenesis expressionStageGroup
		 */
		ExpressionStageGroup embryologyExpressionStageGroup = new ExpressionStageGroup();
		embryologyExpressionStageGroup.setGroupTitle("embryogenesis");
		embryologyExpressionStageGroup.setGroupId("group" + 0);
		List<ExpressionStage> embryologyExpressionStageList = new LinkedList<ExpressionStage>();
		// Add embryo stages
		for (int i = 56; i <= 67; i++) {
			embryologyExpressionStageList.add(allExpressionStages.get(i));
		}
		embryologyExpressionStageGroup.setExpressionStageList(embryologyExpressionStageList);
		expressionStageGroupList.add(embryologyExpressionStageGroup);

		/*
		 * Add development expressionStageGroup
		 */
		ExpressionStageGroup developmentExpressionStageGroup = new ExpressionStageGroup();
		developmentExpressionStageGroup.setGroupTitle("development");
		developmentExpressionStageGroup.setGroupId("group" + 1);
		List<ExpressionStage> developmentExpressionStageList = new LinkedList<ExpressionStage>();
		// Add embryo stages
		for (int i = 56; i <= 67; i++) {
			developmentExpressionStageList.add(allExpressionStages.get(i));
		}
		// larva stages
		for (int i = 75; i <= 81; i++) {
			developmentExpressionStageList.add(allExpressionStages.get(i));
		}
		developmentExpressionStageList.add(allExpressionStages.get(97));
		for (int i = 49; i <= 54; i++) {
			developmentExpressionStageList.add(allExpressionStages.get(i));
		}
		for (int i = 38; i <= 41; i++) {
			developmentExpressionStageList.add(allExpressionStages.get(i));
		}
		// TODO: Add the others
		developmentExpressionStageList.add(allExpressionStages.get(19));
		developmentExpressionStageList.add(allExpressionStages.get(44));
		developmentExpressionStageGroup.setExpressionStageList(developmentExpressionStageList);
		expressionStageGroupList.add(developmentExpressionStageGroup);

		/*
		 * Add tissue expressionStageGroup
		 */
		ExpressionStageGroup tissueExpressionStageGroup = new ExpressionStageGroup();
		tissueExpressionStageGroup.setGroupTitle("tissue");
		tissueExpressionStageGroup.setGroupId("group" + 2);
		List<ExpressionStage> tissueExpressionStageList = new LinkedList<ExpressionStage>();
		// A Mate stages
		for (int i = 11; i <= 29; i++) {
			tissueExpressionStageList.add(allExpressionStages.get(i));
		}

		for (int i = 32; i <= 37; i++) {
			tissueExpressionStageList.add(allExpressionStages.get(i));
		}

		for (int i = 42; i <= 43; i++) {
			tissueExpressionStageList.add(allExpressionStages.get(i));
		}

		for (int i = 47; i <= 48; i++) {
			tissueExpressionStageList.add(allExpressionStages.get(i));
		}
		tissueExpressionStageGroup.setExpressionStageList(tissueExpressionStageList);
		expressionStageGroupList.add(tissueExpressionStageGroup);

		/*
		 * Add treatment expressionStageGroup
		 */
		ExpressionStageGroup treatmentExpressionStageGroup = new ExpressionStageGroup();
		treatmentExpressionStageGroup.setGroupTitle("treatment");
		treatmentExpressionStageGroup.setGroupId("group" + 3);
		List<ExpressionStage> treatmentExpressionStageList = new LinkedList<ExpressionStage>();
		// larva stages
		for (int i = 82; i <= 90; i++) {
			treatmentExpressionStageList.add(allExpressionStages.get(i));
		}
		for (int i = 0; i <= 10; i++) {
			treatmentExpressionStageList.add(allExpressionStages.get(i));
		}
		treatmentExpressionStageGroup.setExpressionStageList(treatmentExpressionStageList);
		expressionStageGroupList.add(treatmentExpressionStageGroup);

		/*
		 * Add cell-line expressionStageGroup
		 */
		ExpressionStageGroup cellLineExpressionStageGroup = new ExpressionStageGroup();
		cellLineExpressionStageGroup.setGroupTitle("cell-line");
		cellLineExpressionStageGroup.setGroupId("group" + 4);
		List<ExpressionStage> cellLineExpressionStageList = new LinkedList<ExpressionStage>();
		// Add embryo stages
		for (int i = 98; i <= 103; i++) {
			cellLineExpressionStageList.add(allExpressionStages.get(i));
		}
		// larva stages
		for (int i = 68; i <= 74; i++) {
			cellLineExpressionStageList.add(allExpressionStages.get(i));
		}
		for (int i = 91; i <= 96; i++) {
			cellLineExpressionStageList.add(allExpressionStages.get(i));
		}
		for (int i = 45; i <= 46; i++) {
			cellLineExpressionStageList.add(allExpressionStages.get(i));
		}
		for (int i = 30; i <= 31; i++) {
			cellLineExpressionStageList.add(allExpressionStages.get(i));
		}
		cellLineExpressionStageList.add(allExpressionStages.get(55));

		// TODO: Add the others
		cellLineExpressionStageGroup.setExpressionStageList(cellLineExpressionStageList);
		expressionStageGroupList.add(cellLineExpressionStageGroup);

		expressionStageOptions.setExpressionStageGroupList(expressionStageGroupList);
		return expressionStageOptions;
	}
}
