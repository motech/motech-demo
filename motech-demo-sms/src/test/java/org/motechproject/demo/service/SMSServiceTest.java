package org.motechproject.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.demo.builder.PatientBuilder;
import org.motechproject.demo.domain.patient.Patient;
import org.motechproject.server.messagecampaign.domain.campaign.CampaignEnrollment;
import org.motechproject.server.messagecampaign.service.CampaignEnrollmentService;
import org.motechproject.server.messagecampaign.service.MessageCampaignService;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class SMSServiceTest {

    @Mock
    private CampaignEnrollmentService campaignEnrollmentService;

    private SMSService smsService;
    @Mock
    private MessageCampaignService messageCampaignService;

    @Before
    public void setUp() {
        initMocks(this);
        smsService = new SMSService(campaignEnrollmentService, messageCampaignService);
    }

    @Test
    public void shouldEnrollPatientToAMessageCampaign() {
        Patient patient = PatientBuilder.startRecording().withDefaults().build();

        smsService.enroll(patient);

        ArgumentCaptor<CampaignEnrollment> campaignEnrollmentArgumentCaptor = ArgumentCaptor.forClass(CampaignEnrollment.class);
        verify(campaignEnrollmentService).register(campaignEnrollmentArgumentCaptor.capture());
        assertEquals("firstDayMessages", campaignEnrollmentArgumentCaptor.getValue().getCampaignName());
        assertEquals(patient.getId(), campaignEnrollmentArgumentCaptor.getValue().getExternalId());
    }

}
