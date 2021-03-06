package org.egov.eis.service.helper;

import org.egov.eis.config.ApplicationProperties;
import org.egov.eis.config.PropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class HolidaySearchURLHelper {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private PropertiesManager propertiesManager;

    public String searchURL(final String tenantId) {
        final String BASE_URL = propertiesManager.getCommonMastersServiceHostName()
                + propertiesManager.getCommonMastersServiceHolidayBasePath()
                + propertiesManager.getCommonMastersServiceHolidaysSearchPath();
        final StringBuilder searchURL = new StringBuilder(BASE_URL + "?");

        if (tenantId == null)
            return searchURL.toString();
        else
            searchURL.append("tenantId=" + tenantId);

        searchURL.append("&pageSize=" + applicationProperties.attendanceSearchPageSizeMax());

        return searchURL.toString();
    }

    public String searchByDateURL(final String tenantId, Date validFromdate) {
        String url = searchURL(tenantId);
        final StringBuilder searchURL = new StringBuilder(url);



        searchURL.append("&fromDate=" + validFromdate);
        searchURL.append("&toDate=" + getToDate());

        return searchURL.toString();
    }

    private Date getToDate() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date toDate = null;

        try {
            toDate = formatter.parse(formatter.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return toDate;
    }


}