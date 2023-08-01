package com.backend.constant;

public class AppConstant {

    public static String typeIndividual = "Individual";
    public static String[] typeEntity = {"Private Limited", "LLP", "State PSU (eg. State Forest Corp., TANGEDCO, Mineral development Corp)", "Central PSU (eg. NHAI, AAI, NTP, CIL, SAIL)", "Joint Venture (Govt. + Govt.)", "Joint Venture (Govt. + Pvt.)", "Joint Venture (Pvt. + Pvt.)", "Trust/Association/Society"};

    public static String[] typeGovernment = {"State Government (Department/Autonomous body)", "Central Government (Department/Autonomous body)"};

    public static String typeOthers = "Others";

    public static enum legalEntity {
        Central_Gov("Central Government (Department/Autonomous body)"), State_Gov("State Government (Department/Autonomous body)"), Central_PSU("Central PSU (eg. NHAI, AAI, NTP, CIL, SAIL)"), State_PSU("State PSU (eg. State Forest Corp., TANGEDCO, Mineral development Corp)"), Joint_Venture_Pvt_Gov("Joint Venture (Govt. + Pvt.)"), Private_Limited("Private Limited"), Others("Others"), Limited("Limited"), LLP("LLP"), Individual("Individual"), Joint_Venture_Gov_Gov("Joint Venture (Govt. + Govt.)"), Joint_Venture_Pvt_Pvt("Joint Venture (Pvt. + Pvt.)");

        private final String text;

        legalEntity(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public static enum bool {
        YES, NO
    }

    public static enum kml_type {
        PRIMARY, ALTERNATIVE1, ALTERNATIVE2, ALTERNATIVE3, ALTERNATIVE4, ALTERNATIVE5
    }

    public static enum Caf_Status {
        DRAFT, SUBMITTED, APPROVED, REJECTED, COMPLETED, EDS_RAISED, RESUBMITTED, EDS_REPLIED
    }

    public static enum Form_for {
        New, Expansion, Amendment
    }

    public static enum priorApproval_type {
        FC_PRIOR_APPROVAL, EC_PRIOR_APPROVAL
    }

    public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCX5QXZXmiwf73G7XMggdKYW7WFxtptJ38RnUMz4rv5xOJJGP0TyyzibLdhhiSSzIspVmAopL4h3FVO7KAp0TemRW6e/lSDMVoTeVPipde8r6bwjrhnYEapUiDMAfal6ah4ZlOTjwWx3OwXg3kmbdV/1JffVYVA7kI8ZswuyNfZ5wIDAQAB";
    public static final String confirmMailSubject = "Registration Confirmation for PARIVESH portal as project proponent.";
    public static final String confirmMailSubjectConsultant = "Successful submission of application.";
    public static final String inProcessSubject = "Registration Application is in approval process for PARIVESH portal as project proponent.";
    public static final String confirmTemplate = "template.vm";
    public static final String confirmTemplateConsultant = "template_consultant.vm";
    public static final String inProcessTemplate = "process.vm";
    public static final String tokenUrl = "token.url";
    public static final String tokenUrlKyc = "token.kyc.url";
    public static final String imageUrl = "image.url";
    public static final String warFile = "war.file";
    public static final String proposalSubmissionSubject = "Confirmation for Proposal submission.";
    public static final String simpleSubject = "Simple Subject";
    public static final String publicHearing = "Public Hearing Scheduled";
    public static final String proposalSubmittedTemplate = "proposal_submitted_template.vm";
    public static final String simpleTemplate = "simple_content.vm";
    public static final String SuccessfulSubmissionOfApplication = "1107165969703528586";
    public static final String notifyTypeEmail = "EMAIL";
    public static final String ekycTemplate = "ekyc_template.vm";
    public static final String ekycMailSubject = "Registration Confirmation for PARIVESH portal as project proponent.";
    public static final String ekycConfirmTemplate = "ekyc_confirmation.vm";
    public static final String ekycConfirmMailSubject = "Registration Confirmation for PARIVESH portal as project proponent.";
    public static final String pendingMailSubject = "Registration Confirmation for PARIVESH portal as project proponent.";
    public static final String pendingTemplate = "pending_template.vm";
    public static final String rejectMailSubject = "Registration Update for PARIVESH portal as project proponent.";
    public static final String rejectTemplate = "reject_template.vm";
    public static final String verifyMailSubject = "Registration Confirmation for PARIVESH portal as project proponent.";
    public static final String verifyTemplate = "pending_template.vm";
    
    public static final String CERTIFICATE_URL = "certificate.url";
    public static final String CERTIFICATE_SERVER_PATH = "server.path";

    public static final Integer APPLICATION_SUBMISSION_EC = 18;

    public static final Integer ACKNOWLEDGEMENT_EC = 53;
    public static final Integer APPLICATION_SUBMISSION_FC = 35;

    public static final Integer SubmissionOfApplication = 20;

    public static final String paymentNotificationVm = "payment_acknowledgement.vm";
    public static final String paymentNotificationSubject = "CAMPA Payment Confirmation ";
    
    public static final Integer REMOVE_PROPOSAL_TEMPLATE_ID = 56;

}
