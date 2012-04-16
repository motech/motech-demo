package org.motechproject.demo.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.motechproject.demo.domain.patient.Patient;
import org.motechproject.demo.report.PatientReportBuilder;
import org.motechproject.demo.report.ReportBuilder;
import org.motechproject.demo.repository.patient.AllPatients;
import org.motechproject.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientService {

    private AllPatients allPatients;
    private SMSService smsService;

    @Autowired
    public PatientService(AllPatients allPatients, SMSService smsService) {
        this.allPatients = allPatients;
        this.smsService = smsService;
    }

    public void registerPatient(Patient patient) {
        patient.setRegistrationDate(DateUtil.now());
        allPatients.add(patient);
        smsService.enroll(patient);
    }

    public HSSFWorkbook buildReport() {
        PatientReportBuilder callLogReportBuilder = new PatientReportBuilder(allPatients);
        return createExcelReport(callLogReportBuilder);
    }

    protected HSSFWorkbook createExcelReport(ReportBuilder reportBuilder) {
        try {
            return reportBuilder.getExcelWorkbook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
