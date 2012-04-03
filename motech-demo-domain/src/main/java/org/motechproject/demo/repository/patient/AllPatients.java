package org.motechproject.demo.repository.patient;

import org.ektorp.CouchDbConnector;
import org.ektorp.ViewQuery;
import org.ektorp.support.GenerateView;
import org.motechproject.dao.MotechBaseRepository;
import org.motechproject.demo.domain.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AllPatients extends MotechBaseRepository<Patient> {

    @Autowired
    public AllPatients(@Qualifier("dbConnector") CouchDbConnector db) {
        super(Patient.class, db);
        initStandardDesignDocument();
    }

    @GenerateView
    public Patient findByPatientId(String patientId) {
        ViewQuery q = createQuery("by_patientId").key(patientId).includeDocs(true);
        List<Patient> patients = db.queryView(q, Patient.class);
        if (patients.isEmpty()) return null;

        return patients.get(0);
    }

}