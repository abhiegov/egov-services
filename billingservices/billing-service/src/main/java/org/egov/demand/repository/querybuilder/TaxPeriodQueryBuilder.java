/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2015>  eGovernments Foundation
 *
 *     The updated version of eGov suite of products as by eGovernments Foundation
 *     is available at http://www.egovernments.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see http://www.gnu.org/licenses/ or
 *     http://www.gnu.org/licenses/gpl.html .
 *
 *     In addition to the terms of the GPL license to be adhered to in using this
 *     program, the following additional terms are to be complied with:
 *
 *         1) All versions of this program, verbatim or modified must carry this
 *            Legal Notice.
 *
 *         2) Any misrepresentation of the origin of the material is prohibited. It
 *            is required that all modified versions of this material be marked in
 *            reasonable ways as different from the original version.
 *
 *         3) This license does not grant any rights to any user of the program
 *            with regards to rights under trademark law for use of the trade names
 *            or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */
package org.egov.demand.repository.querybuilder;

import org.apache.commons.lang3.StringUtils;
import org.egov.demand.repository.TaxPeriodRepository;
import org.egov.demand.web.contract.TaxPeriodCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaxPeriodQueryBuilder {

    private static final Logger logger = LoggerFactory.getLogger(TaxPeriodRepository.class);

    private static final String BASE_QUERY = "SELECT * FROM EGBS_TAXPERIOD taxperiod ";

    public String prepareSearchQuery(final TaxPeriodCriteria taxPeriodCriteria, final List preparedStatementValues) {
        final StringBuilder selectQuery = new StringBuilder(BASE_QUERY);
        logger.info("prepareSearchQuery --> ");
        prepareWhereClause(selectQuery, preparedStatementValues, taxPeriodCriteria);
        logger.info("Search tax periods query from TaxPeriodQueryBuilder -> " + selectQuery);
        return selectQuery.toString();
    }

    private void prepareWhereClause(final StringBuilder selectQuery, final List preparedStatementValues,
          final TaxPeriodCriteria taxPeriodCriteria) {

        selectQuery.append(" WHERE ");

        if (StringUtils.isNotBlank(taxPeriodCriteria.getTenantId())) {
            selectQuery.append(" taxperiod.tenantId = ? ");
            preparedStatementValues.add(taxPeriodCriteria.getTenantId());
        }

        if (StringUtils.isNotBlank(taxPeriodCriteria.getService())) {
            selectQuery.append(" and taxperiod.service = ? ");
            preparedStatementValues.add(taxPeriodCriteria.getService());
        }

        if (StringUtils.isNotBlank(taxPeriodCriteria.getCode())) {
            selectQuery.append(" and taxperiod.code = ? ");
            preparedStatementValues.add(taxPeriodCriteria.getCode());
        }
    }
}
