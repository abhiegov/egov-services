package org.egov.workflow.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.egov.workflow.contract.WorkFlowRequest;
import org.egov.workflow.entity.State;
import org.egov.workflow.model.StateBuilder;
import org.egov.workflow.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class WorkFlowPersistenceListener {

    @Autowired
    private StateService stateService;

    @KafkaListener(id = "workFlowPersister", topics = "egov-workflow-create11", group = "workflowGroup")
    public void processMessage(ConsumerRecord<String, WorkFlowRequest> record) {
        WorkFlowRequest workFlowRequest = record.value();
        State state = persistState(workFlowRequest);

    }

    private State persistState(WorkFlowRequest workFlowRequest) {
        State state = new StateBuilder().build(workFlowRequest.getProcessInstance());
        return stateService.create(state);
    }

}
