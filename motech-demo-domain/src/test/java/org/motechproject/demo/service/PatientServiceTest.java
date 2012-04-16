package org.motechproject.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.demo.builder.PatientBuilder;
import org.motechproject.demo.domain.patient.Patient;
import org.motechproject.demo.repository.patient.AllPatients;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class PatientServiceTest {
    @Mock
    private AllPatients allPatients;
    @Mock
    private SMSService smsService;

    private PatientService patientService;

    @Before
    public void setUp() {
        initMocks(this);
        patientService = new PatientService(allPatients, smsService);
    }

    @Test
    public void shouldEnrollPatientToMessageCampaign() {
        Patient patient = PatientBuilder.startRecording().withDefaults().build();
        patientService.registerPatient(patient);
        verify(allPatients).add(patient);
        verify(smsService).enroll(patient);
    }

}
