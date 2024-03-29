package org.motechproject.demo.repository.patient;

import org.junit.Before;
import org.junit.Test;
import org.motechproject.demo.builder.PatientBuilder;
import org.motechproject.demo.domain.patient.Patient;
import org.motechproject.demo.testutils.SpringIntegrationTest;
import org.springframework.test.context.ContextConfiguration;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(locations = "classpath*:/applicationModelContext.xml")
public class AllPatientsIT extends SpringIntegrationTest {

    private AllPatients allPatients;

    @Before
    public void setup() {
        allPatients = new AllPatients(dbConnector);
    }

    @Test
    public void shouldFindById() {
        Patient patient = PatientBuilder.startRecording().withDefaults().build();
        allPatients.add(patient);

        Patient loadedPatient = allPatients.get(patient.getId());
        assertNotNull(loadedPatient);
        assertEquals(patient.getExternalId(), loadedPatient.getExternalId());
    }

    @Test
    public void shouldFindByExternalId() {
        Patient patient = PatientBuilder.startRecording().withDefaults().build();
        allPatients.add(patient);

        Patient loadedPatient = allPatients.findByExternalId(patient.getExternalId());
        assertNotNull(loadedPatient);
        assertEquals(patient.getExternalId(), loadedPatient.getExternalId());
    }

}
