package edu.ilstu.biology.flytranscriptionwebapp.processor;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	 */
	public ExpressionStageOptions generateExpressionStageOptions() {
		
		List<String> allExpressionStages = retrieveExpressionStages.getDmelanogasterExpressionStages();
		ExpressionStageOptions expressionStageOptions = new ExpressionStageOptions();
		List<ExpressionStageGroup> expressionStageGroupList = new LinkedList<ExpressionStageGroup>();
		
		/*
		 * Add embryology expressionStageGroup
		 */
		ExpressionStageGroup embryologyExpressionStageGroup = new ExpressionStageGroup();
		embryologyExpressionStageGroup.setGroupTitle("embryology");
		List<String> embryologyExpressionStageList = new LinkedList<String>();
		// Add embryo stages
		for(int i = 56; i <= 67; i++){
			embryologyExpressionStageList.add(allExpressionStages.get(i));
		}
		embryologyExpressionStageGroup.setExpressionStageList(embryologyExpressionStageList);
		expressionStageGroupList.add(embryologyExpressionStageGroup);
		
		/*
		 * Add development expressionStageGroup
		 */
		ExpressionStageGroup developmentExpressionStageGroup = new ExpressionStageGroup();
		developmentExpressionStageGroup.setGroupTitle("development");
		List<String> developmentExpressionStageList = new LinkedList<String>();
		// Add embryo stages
		for(int i = 56; i <= 67; i++){
			developmentExpressionStageList.add(allExpressionStages.get(i));
		}
		// larva stages
		for(int i = 75; i <= 81; i++){
			developmentExpressionStageList.add(allExpressionStages.get(i));
		}
		for(int i = 49; i <= 54; i++){
			developmentExpressionStageList.add(allExpressionStages.get(i));
		}
		for(int i = 38; i <= 41; i++){
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
		List<String> tissueExpressionStageList = new LinkedList<String>();
		// A Mate stages
		for(int i = 11; i <= 29; i++){
			tissueExpressionStageList.add(allExpressionStages.get(i));
		}
		
		for(int i = 32; i <= 37; i++){
			tissueExpressionStageList.add(allExpressionStages.get(i));
		}
		
		for(int i = 42; i <= 43; i++){
			tissueExpressionStageList.add(allExpressionStages.get(i));
		}
		
		for(int i = 47; i <= 48; i++){
			tissueExpressionStageList.add(allExpressionStages.get(i));
		}
		tissueExpressionStageGroup.setExpressionStageList(tissueExpressionStageList);
		expressionStageGroupList.add(tissueExpressionStageGroup);
		
		/*
		 * Add treatment expressionStageGroup
		 */
		ExpressionStageGroup treatmentExpressionStageGroup = new ExpressionStageGroup();
		treatmentExpressionStageGroup.setGroupTitle("treatment");
		List<String> treatmentExpressionStageList = new LinkedList<String>();
		// larva stages
		for(int i = 82; i <= 90; i++){
			treatmentExpressionStageList.add(allExpressionStages.get(i));
		}
		for(int i = 0; i <= 10; i++){
			treatmentExpressionStageList.add(allExpressionStages.get(i));
		}
		treatmentExpressionStageGroup.setExpressionStageList(treatmentExpressionStageList);
		expressionStageGroupList.add(treatmentExpressionStageGroup);
		
		/*
		 * Add cell-line expressionStageGroup
		 */
		ExpressionStageGroup cellLineExpressionStageGroup = new ExpressionStageGroup();
		cellLineExpressionStageGroup.setGroupTitle("cellline");
		List<String> cellLineExpressionStageList = new LinkedList<String>();
		// Add embryo stages
		for(int i = 98; i <= 103; i++){
			cellLineExpressionStageList.add(allExpressionStages.get(i));
		}
		// larva stages
		for(int i = 68; i <= 74; i++){
			cellLineExpressionStageList.add(allExpressionStages.get(i));
		}
		for(int i = 91; i <= 96; i++){
			cellLineExpressionStageList.add(allExpressionStages.get(i));
		}
		for(int i = 45; i <= 46; i++){
			cellLineExpressionStageList.add(allExpressionStages.get(i));
		}
		for(int i = 30; i <= 31; i++){
			cellLineExpressionStageList.add(allExpressionStages.get(i));
		}
		for(int i = 91; i <= 96; i++){
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
