package edu.ilstu.biology.flytranscriptionwebapp.constants;

public class ErrorConstants {

	public final static String UNFOUND_GENE_CODE = "001";
	public final static String UNFOUND_GENE_MESSAGE = "Gene not found (geneIdentifer={})";

	public final static String GENE_WITH_NO_RNA_CODE = "002";
	public final static String GENE_WITH_NO_RNA_MESSAGE = "Gene has no RNA data available (geneIdentifer={})";

	public final static String INVALID_SPECIES_CODE = "010";
	public final static String INVALID_SPECIES_MESSAGE = "Species is not valid (species={})";

	public final static String INVALID_IDENTIFIER_CODE = "011";
	public final static String INVALID_IDENTIFIER_MESSAGE = "Gene identifier is not valid (identifier={})";

	public final static String INVALID_RESULT_COUNT_CODE = "012";
	public final static String INVALID_RESULT_COUNT_NULL_MESSAGE = "No result count entered - please enter a result count";
	public final static String INVALID_RESULT_COUNT_MIN_MESSAGE = "Result count must be greater than 0 (count={})";
	public final static String INVALID_RESULT_COUNT_MAX_MESSAGE = "Result count must be less than 20000 (count={})";

	public final static String INVALID_STAGEMAP_CODE = "013";
	public final static String INVALID_STAGEMAP_NULL_MESSAGE = "No expression stages selected - please select at least 3 expression stages";
	public final static String INVALID_STAGEMAP_MIN_MESSAGE = "Less than three expression stages selected - please select at least 3 expression stages (count={})";

	public final static String INVALID_CUSTOM_EXPRESSION_CODE = "013";
	public final static String INVALID_CUSTOM_EXPRESSION_MESSAGE = "All custom expression values cannot be the same (all values are {})";

	public final static String INVALID_CUSTOM_EXPRESSION_INDICATOR_CODE = "014";
	public final static String INVALID_CUSTOM_EXPRESSION_INDICATOR_NULL_MESSAGE = "No custom expression indicator found";
	public final static String INVALID_CUSTOM_EXPRESSION_INDICATOR_VALUE_MESSAGE = "Custom expression indicator is invalid (indicator={})";
 
}
