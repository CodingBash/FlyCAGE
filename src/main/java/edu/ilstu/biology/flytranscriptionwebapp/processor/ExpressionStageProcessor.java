package edu.ilstu.biology.flytranscriptionwebapp.processor;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class ExpressionStageProcessor {

	public static List<Integer> selectedExpressionStageIndices(Map<String, Boolean> selectedExpressionStages,
			List<String> allExpressionStages) {
		List<Integer> selectedExpressionIndices = new LinkedList<Integer>();
		for (Map.Entry<String, Boolean> entry : selectedExpressionStages.entrySet()) {
			if (entry.getValue() != null && entry.getValue() == Boolean.TRUE) {
				selectedExpressionIndices.add(allExpressionStages.indexOf(entry.getKey()));
			}
		}
		return selectedExpressionIndices;
	}

	/**
	 * Retrieves the selected expression stage labels
	 * 
	 * @param selectedExpressionIndices
	 * @param allExpressionStages
	 * @return
	 */
	public static List<String> selectedExpressionStageLabels(List<Integer> selectedExpressionIndices,
			List<String> allExpressionStages) {
		List<String> selectedExpressionStageLabels = new LinkedList<String>();
		for (Integer index : selectedExpressionIndices) {
			selectedExpressionStageLabels.add(allExpressionStages.get(index));
		}
		return selectedExpressionStageLabels;
	}

	public static List<String> convertListofLabelsToId(List<String> inputLabelList) {
		List<String> resultLabelList = inputLabelList;
		ListIterator<String> listIterator = inputLabelList.listIterator();
		while (listIterator.hasNext()) {
			listIterator.set(convertLabelToId(listIterator.next()));
		}
		return resultLabelList;
	}

	public static String convertLabelToId(String inputLabel) {
		String result = "";
		// Remove space with -, and nonASCII with ?
		result = inputLabel.replaceAll(" ", "-").replaceAll("[^\\p{ASCII}]", "?"); // TODO: Weird statement, redo
		return result;
	}
}
