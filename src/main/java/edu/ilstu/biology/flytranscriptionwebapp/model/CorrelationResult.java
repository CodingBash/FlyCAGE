package edu.ilstu.biology.flytranscriptionwebapp.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

// TODO: This POJO is way to complicated! Simplify!
public class CorrelationResult {
	private double rVal;
	private double seVal;
	private double ciVal;
	private double ciPercentage;
	private double pVal;
	private MathContext mathContext;

	public CorrelationResult(double rVal, double seVal, double ciVal, double ciPercentage, double pVal,
			MathContext mathContext) {
		super();
		this.rVal = rVal;
		this.seVal = seVal;
		this.ciVal = ciVal;
		this.ciPercentage = ciPercentage;
		this.pVal = pVal;
		this.mathContext = mathContext;
	}

	public double getrVal() {
		return rVal;
	}

	public void setrVal(double rVal) {
		this.rVal = rVal;
	}

	public double getSeVal() {
		return seVal;
	}

	public void setSeVal(double seVal) {
		this.seVal = seVal;
	}

	public double getCiVal() {
		return ciVal;
	}

	public void setCiVal(double ciVal) {
		this.ciVal = ciVal;
	}

	public double getCiPercentage() {
		return ciPercentage;
	}

	public void setCiPercentage(double ciPercentage) {
		this.ciPercentage = ciPercentage;
	}

	public double getpVal() {
		return pVal;
	}

	public void setpVal(double pVal) {
		this.pVal = pVal;
	}

	public MathContext getMathContext() {
		return mathContext;
	}

	public void setMathContext(MathContext mathContext) {
		this.mathContext = mathContext;
	}

	public String getrValSigFigs() {
		return getSignificantFigures(rVal);
	}

	public String getSeValSigFigs() {
		return getSignificantFigures(seVal);
	}

	public String getCiValSigFigs() {
		return getSignificantFigures(ciVal);
	}

	public String getpValSigFigs() {
		return getSignificantFigures(pVal);
	}
	
	private String getSignificantFigures(double number){
		if(number == 0.0){
			return String.format("%." + mathContext.getPrecision() + "f", 0.0);
		} else if(number < 0.001){
			return new BigDecimal(number).round(mathContext).toString();
		} else {
			return new BigDecimal(number).setScale(mathContext.getPrecision(), RoundingMode.HALF_UP).toString();
		} 
	}

	// TODO: Maybe this logic can be done in the front end?
	public String getrValueString() {
		return getrValSigFigs() + " \u00B1 " + getCiValSigFigs();
	}
	
}
