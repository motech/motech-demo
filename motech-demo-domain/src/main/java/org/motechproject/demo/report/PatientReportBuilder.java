package org.motechproject.demo.report;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.motechproject.demo.domain.patient.Patient;
import org.motechproject.demo.report.model.ExcelColumn;
import org.motechproject.demo.repository.patient.AllPatients;

import java.util.LinkedList;
import java.util.List;

public class PatientReportBuilder extends BatchReportBuilder {

    private AllPatients allPatients;
    private String startDocId;

    public PatientReportBuilder(AllPatients allPatients) {
        super();
        this.allPatients = allPatients;
    }

    @Override
    protected String getWorksheetName() {
        return "PatientReport";
    }

    @Override
    protected String getTitle() {
        return "Patient Report";
    }

    @Override
    protected void initializeColumns() {
        columns = new LinkedList<ExcelColumn>();
        columns.add(new ExcelColumn("Patient ID", Cell.CELL_TYPE_STRING, 8000));
        columns.add(new ExcelColumn("Name", Cell.CELL_TYPE_STRING, 8000));
        columns.add(new ExcelColumn("Gender", Cell.CELL_TYPE_STRING, 8000));
        columns.add(new ExcelColumn("Phone Number", Cell.CELL_TYPE_STRING, 8000));
        columns.add(new ExcelColumn("Date of Birth (yyyy-mm-dd)", Cell.CELL_TYPE_STRING, 10000));
        columns.add(new ExcelColumn("Date of Registration (yyyy-mm-dd hh:mm)", Cell.CELL_TYPE_STRING, 10000));
    }

    @Override
    protected List<Object> getRowData(Object object) {
        Patient patient = (Patient) object;
        List<Object> row = new LinkedList<Object>();
        row.add(patient.getExternalId());
        row.add(patient.getName());
        row.add(patient.getGender().name());
        row.add(patient.getPhoneNumber());
        row.add(patient.getDateOfBirth());
        row.add(patient.getRegistrationDate().toString("yyyy-MM-dd HH:mm"));
        return row;
    }

    @Override
    protected List fetchData() {
        List<Patient> patients = allPatients.getAll();
        removeDocumentMatchingStartKey(patients);
        incrementIndexOfBatch(patients);
        return patients;
    }

    private void removeDocumentMatchingStartKey(List<Patient> patients) {
        if (startDocId != null) patients.remove(0);
    }

    private void incrementIndexOfBatch(List<Patient> patients) {
        if (CollectionUtils.isNotEmpty(patients)) {
            final Patient patient = patients.get(patients.size() - 1);
            startDocId = patient.getId();
        }
    }

    @Override
    protected void buildSummary(HSSFSheet worksheet) {
    }

}
