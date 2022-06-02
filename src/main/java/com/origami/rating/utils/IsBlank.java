package com.origami.rating.utils;
import com.origami.rating.entity.Model;
import com.origami.rating.utils.Constants;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;

// function to check weather the given value is Null or not
public class IsBlank {
	

	public static Model model1;

    static DataFormatter formatter = new DataFormatter();
    static int i = 0;
    
    public static Boolean check(Cell c) {
        if(c == null || c.getCellType() == org.apache.poi.ss.usermodel.CellType.BLANK)
            return true;
        else
            return false;
    }

    public static Boolean check(Row r){
        if(IsBlank.check(r.getCell(0)) && 
        IsBlank.check(r.getCell(1)) && 
        IsBlank.check(r.getCell(2)) && 
        IsBlank.check(r.getCell(3)) && 
        IsBlank.check(r.getCell(4)) && 
        IsBlank.check(r.getCell(5)) && 
        IsBlank.check(r.getCell(6))) return true;
        else return false;
    }

    public static Boolean isZeroRow(Row r){
    	System.out.println("******************************  ");
    	System.out.println("******************************  "+model1);
    	System.out.println("******************************  "+Constants.ncci(model1));
        i++;
            Object val = formatter.formatCellValue(r.getCell(Constants.ncci(model1).get(0)));
            if(val.toString().equals("0") || val.toString().equals("0.0")) 
                return true;
            else 
                return false;
    }

    public static Boolean check(String sheetName){
        if(sheetName.equals("") || sheetName.isEmpty()) return true;
        else return false;
    }
    public static void initializeModel(Model model) {
    	model1=model;
    	//System.out.println(model);
    }
}

