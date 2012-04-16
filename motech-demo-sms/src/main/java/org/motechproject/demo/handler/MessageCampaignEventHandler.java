package org.motechproject.demo.handler;

import org.motechproject.demo.domain.patient.Patient;
import org.motechproject.demo.repository.patient.AllPatients;
import org.motechproject.model.MotechEvent;
import org.motechproject.server.event.annotations.MotechListener;
import org.motechproject.server.messagecampaign.EventKeys;
import org.motechproject.sms.api.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MessageCampaignEventHandler {

    private SmsService smsService;
    private AllPatients allPatients;

    @Autowired
    public MessageCampaignEventHandler(SmsService smsService, AllPatients allPatients) {
        this.smsService = smsService;
        this.allPatients = allPatients;
    }

    @MotechListener(subjects = EventKeys.MESSAGE_CAMPAIGN_FIRED_EVENT_SUBJECT)
    public void handle(MotechEvent event) {
        Map<String,Object> parameters = event.getParameters();
        String patientId = (String) parameters.get(EventKeys.EXTERNAL_ID_KEY);
        String campaignName = (String) parameters.get(EventKeys.MESSAGE_NAME_KEY);
        String messageFormat = ((List<String>) parameters.get(EventKeys.MESSAGE_FORMATS)).get(0);
        List<String> languages = (List<String>) parameters.get(EventKeys.MESSAGE_LANGUAGES);

        Patient patient = allPatients.get(patientId);

        String message = String.format("%s (%s):%s:%s, trying to contact. %s. %s", patient.getId(), "", patient.getPhoneNumber(), "", "", "");

        smsService.sendSMS(patient.getPhoneNumber(), message);
//        smsService.sendSMS(patient.getPhoneNumber(), campaignName + ":"  + messageFormat);
    }
}
