package org.motechproject.demo.web.controller;

import org.motechproject.demo.repository.patient.AllPatients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/patients")
@Controller
public class PatientController {

    public static final String LIST_VIEW = "patients/list";

    AllPatients allPatients;

    @Autowired
    public PatientController(AllPatients allPatients) {
        this.allPatients = allPatients;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel, HttpServletRequest request) {
        uiModel.addAttribute("patients", allPatients.getAll());
        return LIST_VIEW;
    }

}
