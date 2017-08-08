package org.egov.egf.instrument.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.egov.common.contract.request.RequestInfo;
import org.egov.common.domain.model.Pagination;
import org.egov.egf.instrument.domain.model.InstrumentAccountCode;
import org.egov.egf.instrument.domain.model.InstrumentAccountCodeSearch;
import org.egov.egf.instrument.persistence.entity.InstrumentAccountCodeEntity;
import org.egov.egf.instrument.persistence.queue.repository.InstrumentAccountCodeQueueRepository;
import org.egov.egf.instrument.persistence.repository.InstrumentAccountCodeJdbcRepository;
import org.egov.egf.instrument.web.contract.InstrumentAccountCodeSearchContract;
import org.egov.egf.instrument.web.mapper.InstrumentAccountCodeMapper;
import org.egov.egf.instrument.web.requests.InstrumentAccountCodeRequest;
import org.egov.egf.master.web.repository.FinancialConfigurationContractRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstrumentAccountCodeRepository {

    private InstrumentAccountCodeJdbcRepository instrumentAccountCodeJdbcRepository;

    private InstrumentAccountCodeQueueRepository instrumentAccountCodeQueueRepository;

    private String persistThroughKafka;

    private InstrumentAccountCodeESRepository instrumentAccountCodeESRepository;

    private FinancialConfigurationContractRepository financialConfigurationContractRepository;

    @Autowired
    public InstrumentAccountCodeRepository(InstrumentAccountCodeJdbcRepository instrumentAccountCodeJdbcRepository,
            InstrumentAccountCodeQueueRepository instrumentAccountCodeQueueRepository,
            @Value("${persist.through.kafka}") String persistThroughKafka,
            InstrumentAccountCodeESRepository instrumentAccountCodeESRepository,
            FinancialConfigurationContractRepository financialConfigurationContractRepository) {
        this.instrumentAccountCodeJdbcRepository = instrumentAccountCodeJdbcRepository;
        this.instrumentAccountCodeQueueRepository = instrumentAccountCodeQueueRepository;
        this.persistThroughKafka = persistThroughKafka;
        this.financialConfigurationContractRepository = financialConfigurationContractRepository;
        this.instrumentAccountCodeESRepository = instrumentAccountCodeESRepository;

    }

    public InstrumentAccountCode findById(InstrumentAccountCode instrumentAccountCode) {
        InstrumentAccountCodeEntity entity = instrumentAccountCodeJdbcRepository
                .findById(new InstrumentAccountCodeEntity().toEntity(instrumentAccountCode));
        if (entity != null)
            return entity.toDomain();

        return null;

    }

    @Transactional
    public List<InstrumentAccountCode> save(List<InstrumentAccountCode> instrumentAccountCodes,
            RequestInfo requestInfo) {

        InstrumentAccountCodeMapper mapper = new InstrumentAccountCodeMapper();

        if (persistThroughKafka != null && !persistThroughKafka.isEmpty()
                && persistThroughKafka.equalsIgnoreCase("yes")) {

            InstrumentAccountCodeRequest request = new InstrumentAccountCodeRequest();
            request.setRequestInfo(requestInfo);
            request.setInstrumentAccountCodes(new ArrayList<>());

            for (InstrumentAccountCode iac : instrumentAccountCodes) {

                request.getInstrumentAccountCodes().add(mapper.toContract(iac));

            }

            instrumentAccountCodeQueueRepository.addToQue(request);

            return instrumentAccountCodes;
        } else {

            List<InstrumentAccountCode> resultList = new ArrayList<InstrumentAccountCode>();

            for (InstrumentAccountCode iac : instrumentAccountCodes) {

                resultList.add(save(iac));
            }

            InstrumentAccountCodeRequest request = new InstrumentAccountCodeRequest();
            request.setRequestInfo(requestInfo);
            request.setInstrumentAccountCodes(new ArrayList<>());

            for (InstrumentAccountCode iac : resultList) {

                request.getInstrumentAccountCodes().add(mapper.toContract(iac));

            }

            instrumentAccountCodeQueueRepository.addToSearchQue(request);

            return resultList;
        }

    }

    @Transactional
    public List<InstrumentAccountCode> update(List<InstrumentAccountCode> instrumentAccountCodes,
            RequestInfo requestInfo) {

        InstrumentAccountCodeMapper mapper = new InstrumentAccountCodeMapper();

        if (persistThroughKafka != null && !persistThroughKafka.isEmpty()
                && persistThroughKafka.equalsIgnoreCase("yes")) {

            InstrumentAccountCodeRequest request = new InstrumentAccountCodeRequest();
            request.setRequestInfo(requestInfo);
            request.setInstrumentAccountCodes(new ArrayList<>());

            for (InstrumentAccountCode iac : instrumentAccountCodes) {

                request.getInstrumentAccountCodes().add(mapper.toContract(iac));

            }

            instrumentAccountCodeQueueRepository.addToQue(request);

            return instrumentAccountCodes;
        } else {

            List<InstrumentAccountCode> resultList = new ArrayList<InstrumentAccountCode>();

            for (InstrumentAccountCode iac : instrumentAccountCodes) {

                resultList.add(update(iac));
            }

            InstrumentAccountCodeRequest request = new InstrumentAccountCodeRequest();
            request.setRequestInfo(requestInfo);
            request.setInstrumentAccountCodes(new ArrayList<>());

            for (InstrumentAccountCode iac : resultList) {

                request.getInstrumentAccountCodes().add(mapper.toContract(iac));

            }

            instrumentAccountCodeQueueRepository.addToSearchQue(request);

            return resultList;
        }

    }

    @Transactional
    public InstrumentAccountCode save(InstrumentAccountCode instrumentAccountCode) {
        InstrumentAccountCodeEntity entity = instrumentAccountCodeJdbcRepository
                .create(new InstrumentAccountCodeEntity().toEntity(instrumentAccountCode));
        return entity.toDomain();
    }

    @Transactional
    public InstrumentAccountCode update(InstrumentAccountCode instrumentAccountCode) {
        InstrumentAccountCodeEntity entity = instrumentAccountCodeJdbcRepository
                .update(new InstrumentAccountCodeEntity().toEntity(instrumentAccountCode));
        return entity.toDomain();
    }

    public Pagination<InstrumentAccountCode> search(InstrumentAccountCodeSearch domain) {

        if (financialConfigurationContractRepository.fetchDataFrom() != null
                && financialConfigurationContractRepository.fetchDataFrom().equalsIgnoreCase("es")) {

            InstrumentAccountCodeSearchContract instrumentAccountCodeSearchContract = new InstrumentAccountCodeSearchContract();
            ModelMapper mapper = new ModelMapper();
            mapper.map(domain, instrumentAccountCodeSearchContract);
            return instrumentAccountCodeESRepository.search(instrumentAccountCodeSearchContract);
        } else {

            return instrumentAccountCodeJdbcRepository.search(domain);
        }

    }

}