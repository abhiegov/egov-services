package org.egov.pgr.consumer;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.egov.pgr.contract.EmailRequest;
import org.egov.pgr.contract.SMSRequest;
import org.egov.pgr.contract.ServiceReq;
import org.egov.pgr.contract.ServiceReqRequest;
import org.egov.pgr.producer.PGRProducer;
import org.egov.pgr.utils.PGRConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PGRNotificationConsumer {
	
	@Autowired
	private PGRProducer pGRProducer;
			
	@Value("${kafka.topics.notification.sms}")
	private String smsNotifTopic;
	
	@Value("${kafka.topics.notification.email}")
	private String emailNotifTopic;
	
	@Value("${text.for.sms.email.notif}")
	private String textForNotif;
	
	@Value("${text.for.subject.email.notif}")
	private String subjectForEmail;
	
	@Value("${kafka.topics.notification.create.complaint}")
	private String createComplaintTopic;
	
	@Value("${kafka.topics.notification.assign.complaint}")
	private String assignComplaintTopic;
	
	@Value("${kafka.topics.notification.close.complaint}")
	private String closeComplaintTopic;
		
    @KafkaListener(topics = {"${kafka.topics.notification.create.complaint}", "${kafka.topics.notification.assign.complaint}",
    		"${kafka.topics.notification.close.complaint}"})
    
	public void listen(final HashMap<String, Object> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
		ObjectMapper mapper = new ObjectMapper();
		ServiceReqRequest serviceReqRequest = new ServiceReqRequest();
		try{
			log.info("Consuming record: "+record);
			serviceReqRequest = mapper.convertValue(record, ServiceReqRequest.class);
		}catch(final Exception e){
			log.error("Error while listening to value: "+record+" on topic: "+topic+": ", e.getMessage());
		}
		process(topic, serviceReqRequest);		
	}
    
    public void process(String topic, ServiceReqRequest serviceReqRequest) {
    	for(ServiceReq serviceReq: serviceReqRequest.getServiceReq()) {
    		SMSRequest smsRequest = prepareSMSRequest(serviceReq, topic);
        	log.info("SMS: "+smsRequest.getMessage()+" | MOBILE: "+smsRequest.getMobileNumber());
			pGRProducer.push(smsNotifTopic, smsRequest);
			if(null != serviceReq.getEmail() && !serviceReq.getEmail().isEmpty()) {
				EmailRequest emailRequest = prepareEmailRequest(serviceReq, topic);
	        	log.info("EMAIL: "+emailRequest.getBody()
	        	+"| SUBJECT: "+emailRequest.getSubject()
	        	+"| ID: "+emailRequest.getEmail());
				pGRProducer.push(emailNotifTopic, emailRequest);	
			}
		}
    }
    
    public SMSRequest prepareSMSRequest(ServiceReq serviceReq, String topic) {
		String phone = serviceReq.getPhone();
		String message = getMessageForSMS(serviceReq, topic);
		SMSRequest smsRequest = SMSRequest.builder().mobileNumber(phone).message(message).build();
		
		return smsRequest;
    }
    
    public EmailRequest prepareEmailRequest(ServiceReq serviceReq, String topic) {
		String email = serviceReq.getEmail();
		StringBuilder subject = new StringBuilder();
		String body = getBodyAndSubForEmail(serviceReq, topic, subject);
		EmailRequest emailRequest = EmailRequest.builder().email(email).subject(subject.toString()).body(body)
				.isHTML(true).build();
		
		return emailRequest;
    }
    
    public String getBodyAndSubForEmail(ServiceReq serviceReq, String topic, StringBuilder subject) {
    	Map<String, Object> map = new HashMap<>();
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        VelocityContext context = new VelocityContext();
    	map.put("name", serviceReq.getFirstName());
    	map.put("id", serviceReq.getServiceRequestId());
		if(topic.equals(createComplaintTopic)) {
        	map.put("status", "created");
		}else if(topic.equals(assignComplaintTopic)) {
        	map.put("status", "assgined to Mr."+serviceReq.getAssignedTo());
		}else {
        	map.put("status", "closed");
		}
        context.put("params", map);
        Template t = ve.getTemplate(PGRConstants.TEMPLATE_COMPLAINT_EMAIL);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
    	String message = writer.toString();
    	String subjectText = subjectForEmail;
    	subject.append(subjectText.replace("<id>", serviceReq.getServiceRequestId()));
    	
    	return message;    	
    }
    
    public String getMessageForSMS(ServiceReq serviceReq, String topic) {
    	String message = textForNotif;
		message = message.replace("<name>", serviceReq.getFirstName())
				.replace("<id>", serviceReq.getServiceRequestId());
		if(topic.equals(createComplaintTopic)) {
    		message = message.replaceAll("<status>", "created");
		}else if(topic.equals(assignComplaintTopic)) {
    		message = message.replaceAll("<status>", "assgined to Mr."+serviceReq.getAssignedTo());
		}else {
    		message = message.replaceAll("<status>", "closed");
		}
    	
    	return message;
    	
    }
    
}
