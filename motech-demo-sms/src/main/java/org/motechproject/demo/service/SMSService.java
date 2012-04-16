package org.motechproject.demo.service;

import org.motechproject.demo.domain.patient.Patient;
import org.motechproject.model.Time;
import org.motechproject.server.messagecampaign.contract.CampaignRequest;
import org.motechproject.server.messagecampaign.domain.campaign.CampaignEnrollment;
import org.motechproject.server.messagecampaign.service.CampaignEnrollmentService;
import org.motechproject.server.messagecampaign.service.MessageCampaignService;
import org.motechproject.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SMSService {

    private CampaignEnrollmentService campaignEnrollmentService;
    private MessageCampaignService messageCampaignService;

    @Autowired
    public SMSService(CampaignEnrollmentService campaignEnrollmentService, MessageCampaignService messageCampaignService) {
        this.campaignEnrollmentService = campaignEnrollmentService;
        this.messageCampaignService = messageCampaignService;
    }

    public void enroll(Patient patient) {
        campaignEnrollmentService.register(new CampaignEnrollment(patient.getId(), "firstDayMessages"));
        messageCampaignService.startFor(new CampaignRequest(patient.getId(), "firstDayMessages", new Time(10, 10), DateUtil.today()));
    }

}
