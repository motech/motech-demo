package org.motechproject.demo.web.controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.motechproject.demo.repository.patient.AllPatients;
import org.motechproject.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/patients")
@Controller
public class PatientController {

    public static final String LIST_VIEW = "patients/list";

    AllPatients allPatients;
    PatientService patientService;

    @Autowired
    public PatientController(AllPatients allPatients, PatientService patientService) {
        this.allPatients = allPatients;
        this.patientService = patientService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel, HttpServletRequest request) {
        uiModel.addAttribute("patients", allPatients.getAll());
        return LIST_VIEW;
    }

    @RequestMapping(value = "patientReport.xls", method = RequestMethod.GET)
    public void buildCallLogExcelReport(HttpServletResponse response) {
        HSSFWorkbook patientReport = patientService.buildReport();
        writeExcelToResponse(response, patientReport, "PatientReport.xls");
    }

    private void writeExcelToResponse(HttpServletResponse response, HSSFWorkbook excelWorkbook, String fileName) {
        try {
            initializeExcelResponse(response, fileName);
            ServletOutputStream outputStream = response.getOutputStream();
            if (null != excelWorkbook)
                excelWorkbook.write(outputStream);
            outputStream.flush();
        } catch (IOException e) {
        }
    }

    private void initializeExcelResponse(HttpServletResponse response, String fileName) {
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        response.setContentType("application/vnd.ms-excel");
    }

}
