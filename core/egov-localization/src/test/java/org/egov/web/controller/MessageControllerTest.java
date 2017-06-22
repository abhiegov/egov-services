package org.egov.web.controller;

import org.apache.commons.io.IOUtils;
import org.egov.TestConfiguration;
import org.egov.domain.model.Message;
import org.egov.domain.model.MessageIdentity;
import org.egov.domain.model.Tenant;
import org.egov.domain.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MessageController.class)
@Import(TestConfiguration.class)
public class MessageControllerTest {

    private static final String TENANT_ID = "default";
    private static final String LOCALE = "kn_IN";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Test
    public void test_should_fetch_messages_for_given_locale_via_get_endpoint() throws Exception {
        final List<Message> modelMessages = getModelMessages();
        when(messageService.getMessages(LOCALE, new Tenant(TENANT_ID)))
            .thenReturn(modelMessages);
        mockMvc.perform(get("/messages")
            .param("tenantId", TENANT_ID)
            .param("locale", LOCALE))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(getFileContents("messagesResponse.json")));
    }

    @Test
    public void test_should_fetch_messages_for_given_locale_via_search_endpoint() throws Exception {
        final List<Message> modelMessages = getModelMessages();
        when(messageService.getMessages(LOCALE, new Tenant(TENANT_ID)))
            .thenReturn(modelMessages);
        mockMvc.perform(post("/messages/v1/_search")
            .param("tenantId", TENANT_ID)
            .param("locale", LOCALE))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(getFileContents("messagesResponse.json")));
    }

    @Test
    public void test_should_save_new_messages() throws Exception {
        final MessageIdentity messageIdentity1 = MessageIdentity.builder()
            .code("wcms.create.connection.login")
            .locale("kr_IN")
            .module("wcms")
            .tenant(new Tenant("default"))
            .build();
        final Message message1 = Message.builder()
            .messageIdentity(messageIdentity1)
            .message("kannada message for login")
            .build();
        final MessageIdentity messageIdentity2 = MessageIdentity.builder()
            .code("wcms.create.connection.logout")
            .locale("kr_IN")
            .module("wcms")
            .tenant(new Tenant("default"))
            .build();
        final Message message2 = Message.builder()
            .messageIdentity(messageIdentity2)
            .message("kannada message for logout")
            .build();
        List<Message> expectedMessages = Arrays.asList(message1, message2);
        mockMvc.perform(post("/messages/v1/_create")
            .content(getFileContents("createMessageRequest.json"))
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(getFileContents("messagesResponse.json")));

        verify(messageService).create(anyList());
    }

    @Test
    public void test_should_return_bad_request_when_mandatory_fields_are_not_present_when_creating_messages()
        throws Exception {
        mockMvc.perform(post("/messages/v1/_create").content(getFileContents
            ("createNewMessageRequestMissingMandatoryFields.json"))
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(getFileContents("createMessageRequestWithMissingMandatoryFieldsResponse.json")));
    }

    @Test
    public void test_should_update_messages() throws Exception {
        mockMvc.perform(post("/messages/v1/_update")
            .content(getFileContents("updateMessageRequest.json"))
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(getFileContents("createNewMessageResponse.json")));
        verify(messageService).update(anyListOf(Message.class));
    }

    @Test
    public void test_should_give_bad_request_message_when_mandatory_fields_not_available_update_messages()
        throws Exception {
        mockMvc.perform(post("/messages/v1/_update")
            .content(getFileContents("updateMessageRequestWithMissingMandatoryFields.json"))
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(getFileContents("updateMessageRequestWithMissingMandatoryFieldsResponse.json")));
    }

    private String getFileContents(String fileName) {
        try {
            return IOUtils.toString(this.getClass().getClassLoader()
                .getResourceAsStream(fileName), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Message> getModelMessages() {
        final MessageIdentity messageIdentity1 = MessageIdentity.builder()
            .code("wcms.create.connection.login")
            .locale("kn_IN")
            .module("wcms")
            .tenant(new Tenant(TENANT_ID))
            .build();
        final Message message1 = Message.builder()
            .messageIdentity(messageIdentity1)
            .message("kannada message for login")
            .build();
        final MessageIdentity messageIdentity2 = MessageIdentity.builder()
            .code("wcms.create.connection.logout")
            .locale("kn_IN")
            .module("wcms")
            .tenant(new Tenant(TENANT_ID))
            .build();
        final Message message2 = Message.builder()
            .messageIdentity(messageIdentity2)
            .message("kannada message for logout")
            .build();
        return Arrays.asList(message1, message2);
    }
}