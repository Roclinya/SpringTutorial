package leetCode;

import com.google.cloud.recaptchaenterprise.v1.RecaptchaEnterpriseServiceClient;
import com.google.recaptchaenterprise.v1.Assessment;
import com.google.recaptchaenterprise.v1.CreateAssessmentRequest;
import com.google.recaptchaenterprise.v1.Event;
import com.google.recaptchaenterprise.v1.ProjectName;
import com.google.recaptchaenterprise.v1.RiskAnalysis.ClassificationReason;
import java.io.IOException;

public class CreateAssessment {

    public static void main(String[] args) throws IOException {
        // TODO(developer): Replace these variables before running the sample.
        String projectID = "recaptchaproject-443108";//"project-id";
        String recaptchaSiteKey = "6LeHgIwqAAAAAJLTblHhg7t06wb2qvEfTuFr-BNx";//"recaptcha-site-key";
        String token = "03AFcWeA4fowAagekxduAdyBMmXqc0GfRp2xTNFqiLMGttcluzUNrVtMSgvZ2AkO1ueI13WP9ekM9wHY-s7x2tw6pnPy46aUi9So_lcsar8aUFCiyyIQZGYzKlsdMiPAXPb5TmvAVz36cDEK4P3I5JDHtmVtwyr3tubASVr99jRSxVv6dss6AToiylP29K1ANPwuU21OovC8aCJc8QdyH8z5F22kCUOVkqTMT_8sOUb1U4ygxfp31CHc9JBrmA3QmRZDHgfp15DyEgSp83Ak-olH7277rBspylXFUmz16v7y8ICllPxf4kv7fFAqspdEaGnGza1T22sAUSWlAC2NzsbZfCnh58E8ONnXnAjMi-pop-RnjFdzfVWUGbiRemStphK4mSUjkwP1qSnq26TLypKCs24s5FwWq0pAbg7ffceHAa9uEXm0T4cxmglfBWRhs25vAy4pv-Sw7ELiWX0hAA1wTRl1I7s10bWpwvlj8JtEOoXO-3qis6pXPWBOYpRL23Dyr0M4_tGtN2dMPCJxeBAKH9pI7GjVuVRMEJfPYpY8u-WqkIQ66z8Mf5LVPYauFr_-5_9jN3YLhMI2XNOtWj8q9dDaJbZUmzRyk580p1O8jkyu5AkiinLpFDB6vD7IJd_xPBlTMl4vzN1C1AOUzI7CwJNeogUAGYKyyU9HGkaUBfWuYFmTbzvK8GJWvb-eu9MQ8h0_SbJHoeJe5yeZicFabzhbTVt3qrILhbHBXVaFOs6cKTu7OyRwuAlPJxIUy6uVqGAeigyO5MXCIV8Ci4rHsxWNoLd_34jvxhUJ8k345O6rvqHm8EXi5GOMFbohR89FoQ6JNJcfMoEC6dLAHg67YDCeTC1aDrOtoJ3Xj4wA8j8ABVK5RdxvSQIIcRn5wTi82KXlxbbt97G4n7x5XXBrd4ZI7Nlu2_aoIdme2ahOCn3escVAHUtUqZi59leShdF_7ImROqtGc7275DnWZmZo0LauRQjAKcDOyqLbIJfI6W0_fvPP6N59-4Av04A8RilGcJC6eFkv2yVFPblrmpzn2oiQe-W9YIEI1X08MZpA7SH2Uqq7PgZDDE-cnDTyV-PVnxMbBKGkGC8Y9zhTytqqNJ7DVpdjakunZhxb2aoU5J61daNIgPmC3k0X9qPsDtujimHMdxVKlQqnHiYGzSLv2lCUoQQmcGC3pfEaZGZvXd8Gca-67rReU_49idhgdsSaoz36I4kSp5l5jcYCRSW7kM_X18AxfnIcCbYy2BAIDeBtDZ0C8V6cdJtFszQl0bT7aSkeoe31QD_iLBbJnPxcmYaYzJXkbwa1pwmciOh8ooRlEHZPwBZo457NIbjh3fVjHAuxD1k82Rr17dEEHOYtypn4tQ29FcSBrjmHDkrARRPCMdOsaqeoep4XyAe3BQXhpvae1c8nsnRf6TKqXFaC-wVWK1BgM6AOyuMjarRipKg-vRAOVs03Nqrta0h1bmwmLX9rWcPWCTyDyXQxMhRcgDcpsfsF1lWK6mv5g_HFbrL7ogtUNAuOn9JE57de6BgPAB6cSYbkyLyOyM6m3SjKw6wreTRcNzwqFaV1ipDHcDhEVwtFaJ2TkYlsSWZfTCFkNn4uN0vO2vuB5KgABlISJg8xBTeCqChGntbqNfsHqX5X2ubVTdwC0ZiZ7eJBCALh0be-jh6Rl9QZ0-HzdXghhF4nxihcjYmk_WJR5rzd1exG96oQTI21aOd_yNrZG6Ch3DXk3QcHXIKzpG2dtjA8qH8QUPoWaN8Sz6_QNXJT-qajZowm7C740YAUMPRbZkL2r6fb__gLWGEHs2aAS-4WWkKN-qSzFpIKgsLZJjugRfX1Cvi3FyxPOpNm3ggq1kvaKWpZvIW1Nbvj0xfDII4MHs2PKVzc-u4g3mZHgOpqXzVJx7yZSoAkS3mDLNLVlmyN8zo9b7q4Bv1PsdeMWofkfNrhXI9QRtadGtVlQz9bDqxDHMsY0vBl5HBGe195tUtTuKo3L3J1wkCM74O-tGLSwdJ0qy5Ui1dlypBUAoH4yBqB2RxlreuT4GTSHl2-tBZn0Ku7nB6QbrBxhXkr42CJNlZaBioCtnfQ6AIS9L2FBPHYoz9-_L4w7esTZ7tVtuHaVtywBU20zaM4k03Mq1xaJu_TLvV_EA1iNZMd2XZE1PTIM2Bd7cejdMIzR7512SWepNLtXJk9A39OZAzcaM1_aUQg_-YEYM0Tw7pguIjuc5olrglJWZj20";//"action-token";
        String recaptchaAction = "action-name";
        String userIpAddress = "125.227.72.79";//"user-ip-address";
        String userAgent = "\n" +
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36";
        String ja3 = "ja3";

        createAssessment(projectID, recaptchaSiteKey, token, recaptchaAction, userIpAddress, userAgent, ja3);
    }

    /**
     * Create an assessment to analyze the risk of an UI action. Assessment approach is the same for
     * both 'score' and 'checkbox' type recaptcha site keys.
     *
     * @param projectID : GCloud Project ID
     * @param recaptchaSiteKey : Site key obtained by registering a domain/app to use recaptcha
     *     services. (score/ checkbox type)
     * @param token : The token obtained from the client on passing the recaptchaSiteKey.
     * @param recaptchaAction : Action name corresponding to the token.
     * @param userIpAddress: IP address of the user sending a request.
     * @param userAgent: User agent is included in the HTTP request in the request header.
     * @param ja3: JA3 associated with the request.
     */
    public static void createAssessment(
            String projectID, String recaptchaSiteKey, String token, String recaptchaAction, String userIpAddress, String userAgent, String ja3)
            throws IOException {
        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the `client.close()` method on the client to safely
        // clean up any remaining background resources.
        try (RecaptchaEnterpriseServiceClient client = RecaptchaEnterpriseServiceClient.create()) {

            // Set the properties of the event to be tracked.
            Event event = Event.newBuilder()
                    .setSiteKey(recaptchaSiteKey)
                    .setToken(token)
                    .setUserIpAddress(userIpAddress)
                    .setJa3(ja3)
                    .setUserAgent(userAgent)
                    .build();

            // Build the assessment request.
            CreateAssessmentRequest createAssessmentRequest =
                    CreateAssessmentRequest.newBuilder()
                            .setParent(ProjectName.of(projectID).toString())
                            .setAssessment(Assessment.newBuilder().setEvent(event).build())
                            .build();

            Assessment response = client.createAssessment(createAssessmentRequest);

            // Check if the token is valid.
            if (!response.getTokenProperties().getValid()) {
                System.out.println(
                        "The CreateAssessment call failed because the token was: "
                                + response.getTokenProperties().getInvalidReason().name());
                return;
            }

            // Check if the expected action was executed.
            // (If the key is checkbox type and 'action' attribute wasn't set, skip this check.)
            if (!response.getTokenProperties().getAction().equals(recaptchaAction)) {
                System.out.println(
                        "The action attribute in reCAPTCHA tag is: "
                                + response.getTokenProperties().getAction());
                System.out.println(
                        "The action attribute in the reCAPTCHA tag "
                                + "does not match the action ("
                                + recaptchaAction
                                + ") you are expecting to score");
                return;
            }

            // Get the reason(s) and the risk score.
            // For more information on interpreting the assessment,
            // see: https://cloud.google.com/recaptcha-enterprise/docs/interpret-assessment
            for (ClassificationReason reason : response.getRiskAnalysis().getReasonsList()) {
                System.out.println(reason);
            }

            float recaptchaScore = response.getRiskAnalysis().getScore();
            System.out.println("The reCAPTCHA score is: " + recaptchaScore);

            // Get the assessment name (id). Use this to annotate the assessment.
            String assessmentName = response.getName();
            System.out.println(
                    "Assessment name: " + assessmentName.substring(assessmentName.lastIndexOf("/") + 1));
        }
    }
}