package org.green.hr.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.green.hr.dto.PositionDTO;
import org.green.hr.dto.QualificationDTO;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static List<QualificationDTO> excelToQualificationEntities(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<QualificationDTO> qualificationDTOs = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            rows.next();

            while (rows.hasNext()) {
                Row row = rows.next();
                QualificationDTO dto = new QualificationDTO();

                dto.setQualificationName(row.getCell(0).getStringCellValue());
                dto.setEmployeeName(row.getCell(1).getStringCellValue());

                String expiryDateString = row.getCell(2).getStringCellValue();
                Date expiryDate = dateFormat.parse(expiryDateString);
                dto.setExpiryDate(expiryDate);
                dto.setStatus((short) 1);

                qualificationDTOs.add(dto);
            }

            workbook.close();
            return qualificationDTOs;
        } catch (Exception e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
    
    public static List<PositionDTO> excelToPositionEntities(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<PositionDTO> positionDTOs = new ArrayList<>();

            rows.next();

            while (rows.hasNext()) {
                Row row = rows.next();
                PositionDTO dto = new PositionDTO();

                dto.setPositionName(row.getCell(0).getStringCellValue());
                dto.setDepartmentName(row.getCell(1).getStringCellValue());
                dto.setStatus((short) 1);

                positionDTOs.add(dto);
            }

            workbook.close();
            return positionDTOs;
        } catch (Exception e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
