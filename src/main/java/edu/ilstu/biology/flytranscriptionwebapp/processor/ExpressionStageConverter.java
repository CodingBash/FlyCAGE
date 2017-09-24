package edu.ilstu.biology.flytranscriptionwebapp.processor;

import java.util.List;
import java.util.ListIterator;

import org.springframework.stereotype.Component;

@Component
public class ExpressionStageConverter {

	public String convertLabelToId(String inputLabel){
		String result = "";
		
		
		result = inputLabel.replaceAll(" ", "-");
		
		
		return result;
	}
	
	public List<String> convertListofLabelsToId(List<String> inputLabelList){
		List<String> resultLabelList = inputLabelList;
		ListIterator<String> listIterator = inputLabelList.listIterator();
		while(listIterator.hasNext()){
			listIterator.set(convertLabelToId(listIterator.next()));
		}
		return resultLabelList;
	}
}
