package org.green.hr.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.green.hr.dto.QualificationDTO;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static List<QualificationDTO> excelToEntities(InputStream is) {
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
}
