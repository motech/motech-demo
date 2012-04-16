package org.motechproject.demo.handlers;

import org.motechproject.demo.domain.patient.Gender;
import org.motechproject.demo.domain.patient.Patient;
import org.motechproject.demo.domain.patient.Status;
import org.motechproject.demo.formbean.PatientForm;
import org.motechproject.demo.repository.patient.AllPatients;
import org.motechproject.demo.service.PatientService;
import org.motechproject.mobileforms.api.callbacks.FormPublishHandler;
import org.motechproject.model.MotechEvent;
import org.motechproject.server.event.annotations.MotechListener;
import org.motechproject.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientFormHandler implements FormPublishHandler {

    public static final String FORM_BEAN = "formBean";

    AllPatients allPatients;

    PatientService patientService;

    @Autowired
    public PatientFormHandler(AllPatients allPatients, PatientService patientService) {
        this.allPatients = allPatients;
        this.patientService = patientService;
    }

    @Override
    @MotechListener(subjects = "form.validation.successful.Patient.registerPatient")
    public void handleFormEvent(MotechEvent event) {
        PatientForm form = (PatientForm) event.getParameters().get(FORM_BEAN);
        Patient patient = buildPatient(form);
        patientService.registerPatient(patient);
    }

    private Patient buildPatient(PatientForm form) {
        Patient patient = new Patient();
        patient.setExternalId(form.getExternalId());
        patient.setName(form.getName());
        patient.setGender(Gender.get(form.getGender()));
        patient.setPhoneNumber(form.getPhoneNumber());
        patient.setDateOfBirth(DateUtil.newDate(form.getDateOfBirth()));
        patient.setStatus(Status.Active);
        patient.setDateOpened(DateUtil.newDateTime(form.getDateOpened()));
        return patient;
    }
}
