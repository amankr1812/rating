package com.origami.rating.entity;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.multipart.MultipartFile;


public class Model {
	


	private MultipartFile productDefinition; // For SL
	private MultipartFile classTable; // For ASA
	private MultipartFile ncciFile; // for UseLatestRates
	private String currentState;
	private boolean useLatestRates;
	private String fileNcciRates;
	//private Integer[] ncciColumnIndexes;
	private String ncciColumnIndexes;
	private String fileProductDefinition;
	private int stateWcRateLcSheetNo;
	private int rowReadIndexForWcSheet;
	private int stateClassVarSheetNo;
	private int rowReadIndexForClassVarSheet;
	private int stateEmpLiabilitySheetNo;
	private int rowReadIndexForEmpLiability;
	private boolean isClientSL;
	private String fileClassTable;
	private boolean filterWithClassTable;
	private int stateQuickChartSheetNo;
	private int rowReadIndexForClassCodes;
	private String jobClassification;
	private String jobProgramCodes;
	private String empLiaLimits;
	private String carrier;
	private String lcm;
	private String ratingTier;
	

	public MultipartFile getProductDefinition() {
		return productDefinition;
	}
	public void setProductDefinition(MultipartFile productDefinition) {
		this.productDefinition = productDefinition;
	}
	public MultipartFile getClassTable() {
		return classTable;
	}
	public void setClassTable(MultipartFile classTable) {
		this.classTable = classTable;
	}
	public MultipartFile getNcciFile() {
		return ncciFile;
	}
	public void setNcciFile(MultipartFile ncciFile) {
		this.ncciFile = ncciFile;
	}

	
	public String getCurrentState() {
		return currentState;
	}
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	public boolean getUseLatestRates() {
		return useLatestRates;
	}
	public void setUseLatestRates(boolean useLatestRates) {
		this.useLatestRates = useLatestRates;
	}
	public String getFileNcciRates() {
		return fileNcciRates;
	}
	public void setFileNcciRates(String fileNcciRates) {
		this.fileNcciRates = fileNcciRates;
	}
//	public Integer[] getNcciColumnIndexes() {
//		return ncciColumnIndexes;
//	}
//	public void setNcciColumnIndexes(Integer[] ncciColumnIndexes) {
//		this.ncciColumnIndexes = ncciColumnIndexes;
//	}
	public Integer[] getNcciColumnIndexes() {
		
		Integer[] ncciColumnIndexes1=new Integer[2];
		int i=0;
		Pattern p = Pattern.compile("-?\\d+");
		Matcher m = p.matcher("[3,13]\"");
		while (m.find()) {
		  //System.out.println(m.group());
		  ncciColumnIndexes1[i]=Integer.parseInt(m.group());
		  i++;
		}
		return ncciColumnIndexes1;
	}
	public void setNcciColumnIndexes(String ncciColumnIndexes) {
		this.ncciColumnIndexes = ncciColumnIndexes;
	}
	public String getFileProductDefinition() {
		return fileProductDefinition;
	}
	public void setFileProductDefinition(String fileProductDefinition) {
		this.fileProductDefinition = fileProductDefinition;
	}
	public int getStateWcRateLcSheetNo() {
		return stateWcRateLcSheetNo;
	}
	public void setStateWcRateLcSheetNo(int stateWcRateLcSheetNo) {
		this.stateWcRateLcSheetNo = stateWcRateLcSheetNo;
	}
	public int getRowReadIndexForWcSheet() {
		return rowReadIndexForWcSheet;
	}
	public void setRowReadIndexForWcSheet(int rowReadIndexForWcSheet) {
		this.rowReadIndexForWcSheet = rowReadIndexForWcSheet;
	}
	public int getStateClassVarSheetNo() {
		return stateClassVarSheetNo;
	}
	public void setStateClassVarSheetNo(int stateClassVarSheetNo) {
		this.stateClassVarSheetNo = stateClassVarSheetNo;
	}
	public int getRowReadIndexForClassVarSheet() {
		return rowReadIndexForClassVarSheet;
	}
	public void setRowReadIndexForClassVarSheet(int rowReadIndexForClassVarSheet) {
		this.rowReadIndexForClassVarSheet = rowReadIndexForClassVarSheet;
	}
	public int getStateEmpLiabilitySheetNo() {
		return stateEmpLiabilitySheetNo;
	}
	public void setStateEmpLiabilitySheetNo(int stateEmpLiabilitySheetNo) {
		this.stateEmpLiabilitySheetNo = stateEmpLiabilitySheetNo;
	}
	public int getRowReadIndexForEmpLiability() {
		return rowReadIndexForEmpLiability;
	}
	public void setRowReadIndexForEmpLiability(int rowReadIndexForEmpLiability) {
		this.rowReadIndexForEmpLiability = rowReadIndexForEmpLiability;
	}
	public boolean getIsClientSL() {
		return isClientSL;
	}
	public void setIsClientSL(boolean isClientSL) {
		this.isClientSL = isClientSL;
	}
	public String getFileClassTable() {
		return fileClassTable;
	}
	public void setFileClassTable(String fileClassTable) {
		this.fileClassTable = fileClassTable;
	}
	public boolean getFilterWithClassTable() {
		return filterWithClassTable;
	}
	public void setFilterWithClassTable(boolean filterWithClassTable) {
		this.filterWithClassTable = filterWithClassTable;
	}
	public int getStateQuickChartSheetNo() {
		return stateQuickChartSheetNo;
	}
	public void setStateQuickChartSheetNo(int stateQuickChartSheetNo) {
		this.stateQuickChartSheetNo = stateQuickChartSheetNo;
	}
	public int getRowReadIndexForClassCodes() {
		return rowReadIndexForClassCodes;
	}
	public void setRowReadIndexForClassCodes(int rowReadIndexForClassCodes) {
		this.rowReadIndexForClassCodes = rowReadIndexForClassCodes;
	}
	public String getJobClassification() {
		return jobClassification;
	}
	public void setJobClassification(String jobClassification) {
		this.jobClassification = jobClassification;
	}
	public String getJobProgramCodes() {
		return jobProgramCodes;
	}
	public void setJobProgramCodes(String jobProgramCodes) {
		this.jobProgramCodes = jobProgramCodes;
	}
	public String getEmpLiaLimits() {
		return empLiaLimits;
	}
	public void setEmpLiaLimits(String empLiaLimits) {
		this.empLiaLimits = empLiaLimits;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getLcm() {
		return lcm;
	}
	public void setLcm(String lcm) {
		this.lcm = lcm;
	}
	public String getRatingTier() {
		return ratingTier;
	}
	public void setRatingTier(String ratingTier) {
		this.ratingTier = ratingTier;
	}
	
	public Model(Model model) {
		this.productDefinition=model.productDefinition;
		this.classTable=model.classTable;
		this.ncciFile=model.ncciFile;
		this.currentState = model.currentState;
		this.useLatestRates = model.useLatestRates;
		this.fileNcciRates = model.fileNcciRates;
		this.ncciColumnIndexes = model.ncciColumnIndexes;
		this.fileProductDefinition = model.fileProductDefinition;
		this.stateWcRateLcSheetNo = model.stateWcRateLcSheetNo;
		this.rowReadIndexForWcSheet = model.rowReadIndexForWcSheet;
		this.stateClassVarSheetNo = model.stateClassVarSheetNo;
		this.rowReadIndexForClassVarSheet = model.rowReadIndexForClassVarSheet;
		this.stateEmpLiabilitySheetNo = model.stateEmpLiabilitySheetNo;
		this.rowReadIndexForEmpLiability = model.rowReadIndexForEmpLiability;
		this.isClientSL = model.isClientSL;
		this.fileClassTable = model.fileClassTable;
		this.filterWithClassTable = model.filterWithClassTable;
		this.stateQuickChartSheetNo = model.stateQuickChartSheetNo;
		this.rowReadIndexForClassCodes = model.rowReadIndexForClassCodes;
		this.jobClassification = model.jobClassification;
		this.jobProgramCodes = model.jobProgramCodes;
		this.empLiaLimits = model.empLiaLimits;
		this.carrier = model.carrier;
		this.lcm = model.lcm;
		this.ratingTier = model.ratingTier;
	}
	
	public Model(MultipartFile productDefinition,MultipartFile classTable,MultipartFile ncciFile, String currentState, boolean useLatestRates, String fileNcciRates, String ncciColumnIndexes,
			String fileProductDefinition, int stateWcRateLcSheetNo, int rowReadIndexForWcSheet,
			int stateClassVarSheetNo, int rowReadIndexForClassVarSheet, int stateEmpLiabilitySheetNo,
			int rowReadIndexForEmpLiability, boolean isClientSL, String fileClassTable, boolean filterWithClassTable,
			int stateQuickChartSheetNo, int rowReadIndexForClassCodes, String jobClassification, String jobProgramCodes,
			String empLiaLimits, String carrier, String lcm, String ratingTier) {
		super();
		this.productDefinition=productDefinition;
		this.classTable=classTable;
		this.ncciFile=ncciFile;
		this.currentState = currentState;
		this.useLatestRates = useLatestRates;
		this.fileNcciRates = fileNcciRates;
		this.ncciColumnIndexes = ncciColumnIndexes;
		this.fileProductDefinition = fileProductDefinition;
		this.stateWcRateLcSheetNo = stateWcRateLcSheetNo;
		this.rowReadIndexForWcSheet = rowReadIndexForWcSheet;
		this.stateClassVarSheetNo = stateClassVarSheetNo;
		this.rowReadIndexForClassVarSheet = rowReadIndexForClassVarSheet;
		this.stateEmpLiabilitySheetNo = stateEmpLiabilitySheetNo;
		this.rowReadIndexForEmpLiability = rowReadIndexForEmpLiability;
		this.isClientSL = isClientSL;
		this.fileClassTable = fileClassTable;
		this.filterWithClassTable = filterWithClassTable;
		this.stateQuickChartSheetNo = stateQuickChartSheetNo;
		this.rowReadIndexForClassCodes = rowReadIndexForClassCodes;
		this.jobClassification = jobClassification;
		this.jobProgramCodes = jobProgramCodes;
		this.empLiaLimits = empLiaLimits;
		this.carrier = carrier;
		this.lcm = lcm;
		this.ratingTier = ratingTier;
	}
	
	
	public Model() {

	}
	
	

}
