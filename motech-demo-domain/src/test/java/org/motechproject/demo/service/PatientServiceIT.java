package org.motechproject.demo.service;

import org.junit.Ignore;
import org.junit.Test;
import org.motechproject.demo.builder.PatientBuilder;
import org.motechproject.demo.domain.patient.Patient;
import org.motechproject.demo.testutils.SpringIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(locations = "classpath*:/applicationDomainContext.xml")
public class PatientServiceIT extends SpringIntegrationTest {

    @Autowired
    private PatientService patientService;

    @Test @Ignore
    public void should() {
        Patient patient = PatientBuilder.startRecording().withDefaults().withPhoneNumber("9611809293").build();
        patientService.registerPatient(patient);
    }

}
