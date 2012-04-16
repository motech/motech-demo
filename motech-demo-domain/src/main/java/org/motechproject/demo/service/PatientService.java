package org.motechproject.demo.service;

import org.motechproject.demo.domain.patient.Patient;
import org.motechproject.demo.repository.patient.AllPatients;
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
        allPatients.add(patient);
        smsService.enroll(patient);
    }

}
