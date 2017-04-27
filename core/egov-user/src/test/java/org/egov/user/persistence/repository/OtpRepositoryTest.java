package org.egov.user.persistence.repository;

import org.egov.user.Resources;
import org.egov.user.domain.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(MockitoJUnitRunner.class)
public class OtpRepositoryTest {

    private OtpRepository otpRepository;
    private MockRestServiceServer server;

    @Before
    public void setUp() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        server = MockRestServiceServer.bindTo(restTemplate).build();
        final String otpSearchContext = "otp/_search";
        final String otpHost = "http://otp-host.com/";
        this.otpRepository = new OtpRepository(otpHost, otpSearchContext, restTemplate);
    }

    @Test
    public void testShouldReturnTrueWhenOtpHasBeenValidated() throws Exception {
        server.expect(once(), requestTo("http://otp-host.com/otp/_search"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().string(new Resources().getFileContents("otpSearchSuccessRequest.json")))
                .andRespond(withSuccess(new Resources().getFileContents("otpSearchValidatedResponse.json"),
                        MediaType.APPLICATION_JSON_UTF8));

        Boolean isOtpValidated = otpRepository.isOtpValidationComplete(buildUser());
        server.verify();
        assertEquals(Boolean.TRUE, isOtpValidated);
    }

    @Test
    public void testShouldReturnFalseWhenOtpHasNotBeenValidated() throws Exception {
        server.expect(once(), requestTo("http://otp-host.com/otp/_search"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().string(new Resources().getFileContents("otpSearchSuccessRequest.json")))
                .andRespond(withSuccess(new Resources().getFileContents("otpSearchNonValidatedResponse.json"),
                        MediaType.APPLICATION_JSON_UTF8));

        boolean isOtpValidated = otpRepository.isOtpValidationComplete(buildUser());
        server.verify();
        assertFalse(isOtpValidated);
    }

    @Test
    public void testShouldReturnFalseWhenOtpIdentityDoesNotMatch() throws Exception {
        server.expect(once(), requestTo("http://otp-host.com/otp/_search"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().string(new Resources().getFileContents("otpSearchSuccessRequest.json")))
                .andRespond(withSuccess(new Resources().getFileContents("otpSearchIdentityDifferentResponse.json"),
                        MediaType.APPLICATION_JSON_UTF8));

        boolean isOtpValidated = otpRepository.isOtpValidationComplete(buildUser());
        server.verify();
        assertFalse(isOtpValidated);
    }

    private User buildUser() {
        return User.builder()
                .otpReference("2b936aae-c3b6-4c89-b3b3-a098cdcbb706")
                .mobileNumber("9988776655")
                .tenantId("tenantId")
                .build();
    }

}