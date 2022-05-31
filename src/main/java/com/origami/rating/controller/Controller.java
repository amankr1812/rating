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
import org.springframework.web.multipart.MultipartFile;

import com.origami.rating.service.*;
import com.origami.rating.payload.*;
import com.origami.rating.controller.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController              
@RequestMapping("/origami")
public class Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private FileStorageService fileStorageService;

	@GetMapping("/test") // Test function to return a string
	public String Test() {
		return "Hello World!";
		
		
	}
	
	@GetMapping("/excel") // Test function to generate the consolidated excel
    public static String perform(){
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
          
            return "Excel Conversion completed. Output Files Created Successfully!";
        } catch (Exception e) {
            System.out.println("Exception in controller");
            e.printStackTrace();
            return "Excel Conversion Issue!";
        }
	}
	


    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/origami/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
	
}
