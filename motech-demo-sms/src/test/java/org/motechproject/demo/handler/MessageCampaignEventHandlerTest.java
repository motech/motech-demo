package org.motechproject.demo.handler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.demo.builder.PatientBuilder;
import org.motechproject.demo.domain.patient.Patient;
import org.motechproject.demo.repository.patient.AllPatients;
import org.motechproject.model.MotechEvent;
import org.motechproject.server.messagecampaign.EventKeys;
import org.motechproject.sms.api.service.SmsService;

import java.util.Arrays;
import java.util.HashMap;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MessageCampaignEventHandlerTest {

    public static final String PATIENT_ID = "patientId";
    @Mock
    SmsService smsService;
    @Mock
    private AllPatients allPatients;

    MessageCampaignEventHandler messageCampaignEventHandler;


    @Before
    public void setUp() {
        initMocks(this);
        messageCampaignEventHandler = new MessageCampaignEventHandler(smsService, allPatients);
    }

    @Test
    public void shouldSendSMSWhenMessageCampaignEventIsRaised() {
        Patient patient = PatientBuilder.startRecording().withDefaults().withId(PATIENT_ID).build();
        when(allPatients.get(PATIENT_ID)).thenReturn(patient);
        MotechEvent eventRaisedByMotechMessageCampaign = messageCampaignEvent(PATIENT_ID);

        messageCampaignEventHandler.handle(eventRaisedByMotechMessageCampaign);

        verify(smsService).sendSMS(patient.getPhoneNumber(), patient.getId() +  " ():"+patient.getPhoneNumber()+":, trying to contact. . ");
    }

    private MotechEvent messageCampaignEvent(String patientId) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(EventKeys.EXTERNAL_ID_KEY, patientId);
        parameters.put(EventKeys.MESSAGE_NAME_KEY, "campaignName");
        parameters.put(EventKeys.MESSAGE_FORMATS, Arrays.asList("SMS"));
        parameters.put(EventKeys.MESSAGE_LANGUAGES, Arrays.asList("en"));

        return new MotechEvent("EventKeys.MESSAGE_CAMPAIGN_FIRED_EVENT_SUBJECT", parameters);
    }

}
