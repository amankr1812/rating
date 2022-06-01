package com.origami.rating.utils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.origami.rating.entity.*;


public final class Constants {
	
	public static Model model;
    
    //***************************************** File path Configuration **********************************************//
    public static String dir = System.getProperty("user.dir");
    public static final String FILEPATH = dir + "\\src\\data\\States\\";
    public static final String FILETYPE = ".xlsx";




    //********************** Job classification, job program, emp liability variables declaration *********************************//
    public static Map<String, String> classCodes = new LinkedHashMap<String, String>();
    public static Map<String, String> jobClassMapValues = new LinkedHashMap<String, String>();
    public static Map<String, String> jobProgramMapValues = new LinkedHashMap<String, String>();
    public static Map<String, String> empLiaLimits = new LinkedHashMap<String, String>();


    //********************** Job classification, job program, emp liability datatypes for excel outputs ****************************//
    public static String[] ASAjobClassExlColDatatypes = {"Text1","Text2","Number1","Number2","Number5","Number3","Text3","Text4","Number4"};
    public static String[] SLjobClassExlColDatatypes = {"Text1","Text2","Number1","Number2","Number3","Text3","Text4","Number4"};    
    public static String[] jobClassExlColDatatypes;    
    public static String[] jobProgExlColDatatypes = {"Text1","Text2","Text3","Text4"};
    public static String[] empLiaExlColDatatypes = {"Number1","Number2","Number3","Number4","Number5","Text1"};

    
    //******************************* For NCCI latest LCR rates ****************************************//
    public static List<Integer> indexesForNCCIRates = Arrays.asList(model.getNcciColumnIndexes());

   
       

    public static void initialize(boolean isClientSL, boolean isLatestRates){
        classCodes.put("Class Code", "Class Code");
        jobClassMapValues.put("Classification Code","Job Classification");
        jobClassMapValues.put("Class Suffix Description Code","Class Suffix");
        if(!isLatestRates) jobClassMapValues.put("Classification Manual/Loss Cost Rate","Loss Cost Rate");
        else jobClassMapValues.put("Latest Rates","Loss Cost Rate");
        jobClassMapValues.put("Classification Minimum Premium Amount","Job Class Min Premium");
        if(!isClientSL) jobClassMapValues.put("Loss Constant Amount","Loss Constant Premium");
        jobClassMapValues.put("Disease Load","Disease Load");
        jobClassMapValues.put("Contracting Class Ind","Contracting Classification");
        jobClassMapValues.put("Rating Tier","Rating Tier");
        jobClassMapValues.put("Loss Cost Multiplier","Loss Cost Multiplier");
        jobProgramMapValues.put("Classification Code","Job Classification");
        jobProgramMapValues.put("Hazard Group Code","HazardGroup");
        jobProgramMapValues.put("Program","USLH Program");
        jobProgramMapValues.put("Federal Act Status","Federal Act Status");
        empLiaLimits.put("Bodily Injury by Accident each accident", "Bodily Injury by Accident");
        empLiaLimits.put("Bodily Injury by Disease policy limit", "Bodily Injury Limit by Disease Policy Limit");
        empLiaLimits.put("Bodily Injury by Disease each employee", "Bodily Injury by Disease each Employee");
        empLiaLimits.put("Rate", "Rate");
        empLiaLimits.put("Minimum Premium", "Limit Minimum Premium");
        empLiaLimits.put("Stat Code", "Stat Code");
        if(!isClientSL) jobClassExlColDatatypes = ASAjobClassExlColDatatypes;
        else jobClassExlColDatatypes = SLjobClassExlColDatatypes;
    }
}