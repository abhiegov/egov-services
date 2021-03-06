package org.egov.master.validate;

import org.egov.master.validate.domain.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class AutoRunner implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ValidateService validateService;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
	validateService.validate();
    }
}