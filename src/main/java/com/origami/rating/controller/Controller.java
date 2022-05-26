package com.origami.rating.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.origami.rating.service.Ops;
import com.origami.rating.utils.Constants;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController              
@RequestMapping("/origami")
public class Controller {
	

	@GetMapping("/test") // Test function to return a string
	public String Test() {
		return "Hello World!";
		
		
	}
	
	@GetMapping("/test2") // Test function to generate the consolidated excel
    public static void perform(){
        try {
        	
            Constants.initialize(Constants.isClientSL,Constants.useLatestRates);      // Function call to put the value in respective Map
            Map<Integer, List<String>> list2Print;
            Ops instance;
            Ops newRates;            
            Ops readJobClassWCSheet = new Ops(Constants.STATE_WCRATE_LC_SHEET_NO, "", Constants.ROW_READ_INDEX_FOR_WC_SHEET, Constants.FILEPATH + Constants.FILE_PRODUCT_DEFINITION);
            Ops readJobProgWCSheet = new Ops(Constants.STATE_WCRATE_LC_SHEET_NO, "", Constants.ROW_READ_INDEX_FOR_WC_SHEET, Constants.FILEPATH + Constants.FILE_PRODUCT_DEFINITION);
            Ops readJobClassVarSheet = new Ops(Constants.STATE_CLASS_VAR_SHEET_NO, "", Constants.ROW_READ_INDEX_FOR_CLASS_VAR_SHEET, Constants.FILEPATH + Constants.FILE_PRODUCT_DEFINITION);
            Ops readJobProgVarSheet = new Ops(Constants.STATE_CLASS_VAR_SHEET_NO, "", Constants.ROW_READ_INDEX_FOR_CLASS_VAR_SHEET, Constants.FILEPATH + Constants.FILE_PRODUCT_DEFINITION);
            Ops empSheet = new Ops(Constants.STATE_EMP_LIABILITY_SHEET_NO, "", Constants.ROW_READ_INDEX_FOR_EMP_LIABILITY, Constants.FILEPATH + Constants.FILE_PRODUCT_DEFINITION);
            readJobClassWCSheet.getColumnIndexesFromExcelAndSet(Constants.jobClassMapValues);
            readJobProgWCSheet.getColumnIndexesFromExcelAndSet(Constants.jobProgramMapValues);
            readJobClassVarSheet.getColumnIndexesFromExcelAndSet(Constants.jobClassMapValues);
            readJobProgVarSheet.getColumnIndexesFromExcelAndSet(Constants.jobProgramMapValues);
            empSheet.getColumnIndexesFromExcelAndSet(Constants.empLiaLimits);
            readJobClassWCSheet.getFromExcelAndSet();
            readJobProgWCSheet.getFromExcelAndSet();
            readJobClassVarSheet.getFromExcelAndSet();
            readJobProgVarSheet.getFromExcelAndSet();
            empSheet.getFromExcelAndSet();
            Map<Integer, List<String>> dataFromWcSheetForJobClass = readJobClassWCSheet.getData();
            Map<Integer, List<String>> dataFromWcSheetForJobProg = readJobProgWCSheet.getData();
            Map<Integer, List<String>> dataFromClassVarForJobClass = readJobClassVarSheet.getData();
            Map<Integer, List<String>> dataFromClassVarForJobProg = readJobProgVarSheet.getData();
            Map<Integer, List<String>> mappedList = Ops.mapData(dataFromWcSheetForJobClass,dataFromWcSheetForJobProg);
            Map<Integer, List<String>> mappedList1 = Ops.mapData(dataFromClassVarForJobClass,dataFromClassVarForJobProg);
            Map<Integer, List<String>> mappedList2 = Ops.mapData(mappedList,mappedList1);
            if(Constants.useLatestRates){
                newRates = new Ops(0,"",0,Constants.FILEPATH+ Constants.FILE_NCCI_RATES);  
                newRates.useLatestRates();
                newRates.getColumnIndexesFromExcelAndSet(Constants.indexesForNCCIRates);
                newRates.getFromExcelAndSet();
                mappedList2 = Ops.mapData(mappedList2,newRates.getData());
            }
            if(Constants.isClientSL){
                list2Print = mappedList2;
            } else { // For ASA
                if(Constants.FILTER_WITH_CLASS_TABLE){
                    instance = new Ops(Constants.STATE_QUICK_CHART_SHEET_NO, Constants.CURRENT_STATE, Constants.ROW_READ_INDEX_FOR_CLASS_CODES, Constants.FILEPATH + Constants.FILE_CLASS_TABLE);
                    instance.getColumnIndexesFromExcelAndSet(Constants.classCodes);
                    instance.getFromExcelAndSet();
                    list2Print = Ops.filterData(instance.getData(),mappedList2);
                } else {
                    list2Print = mappedList2;
                }
                
            }            
            if(Constants.useLatestRates) Ops.writeToExcel(Constants.jobClassExlColDatatypes, Constants.jobClassMapValues, list2Print, " -" + Constants.CARRIER + " " + Constants.JOB_CLASSIFICATION + " New Rates");
            else Ops.writeToExcel(Constants.jobClassExlColDatatypes, Constants.jobClassMapValues, list2Print, " -" + Constants.CARRIER + " " + Constants.JOB_CLASSIFICATION);
            Ops.writeToExcel(Constants.jobProgExlColDatatypes, Constants.jobProgramMapValues, list2Print, " -" + Constants.CARRIER + " " + Constants.JOB_PROGRAM_CODES);
            Ops.writeToExcel(Constants.empLiaExlColDatatypes , Constants.empLiaLimits, empSheet.getData(), " -" + Constants.CARRIER + " " + Constants.EMP_LIA_LIMITS);
            System.out.println();
        } catch (Exception e) {
            System.out.println("Exception in controller");
            e.printStackTrace();
        }
	}
}
