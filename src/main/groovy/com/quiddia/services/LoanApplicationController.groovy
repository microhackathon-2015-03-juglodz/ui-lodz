package com.quiddia.services

import com.nurkiewicz.asyncretry.AsyncRetryExecutor
import com.ofg.infrastructure.discovery.ServiceUnavailableException
import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient
import com.ofg.twitter.config.Collaborators
import com.quiddia.model.Client
import com.quiddia.model.Loan
import groovy.json.JsonBuilder
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by warden on 21.03.15.
 */

@Slf4j
@Service
class LoanApplicationService {

    @Autowired
    ServiceRestClient serviceRestClient

    @Autowired
    AsyncRetryExecutor executor

    void applyForLoan(Loan loan) {

        try {
            doCallLoanApplicationService(loan)
        } catch (ServiceUnavailableException e) {
            log.info("Collabolator unresponsive", e)
        }

    }

    void sendClientDetails(Client client) {
        doCallClientService(client)
    }

    private doCallLoanApplicationService(Loan loan) {
        serviceRestClient
                .forService(Collaborators.CLIENT_SERVICE)
                .retryUsing(executor.withMaxRetries(3))
                .post()
                .withCircuitBreaker(HystrixCommand.Setter.withGroupKey({ 'sendingLoanDetails' }),
                { log.info("Breaking circuit") })
                .onUrl("/api/loanApplication")
                .body(new JsonBuilder(loan).toString())
                .withHeaders().contentTypeJson()
                .andExecuteFor()
                .ignoringResponse()
    }

    private doCallClientService(Client client) {
        serviceRestClient.forService(Collaborators.CLIENT_SERVICE)
                .retryUsing(executor.withMaxRetries(3))
                .post()
                .withCircuitBreaker(HystrixCommand.Setter.withGroupKey({ 'sendingClientDetails' }),
                { log.info("Breaking circuit") })
                .onUrl("/api/client")
                .body(new JsonBuilder(client).toString())
                .withHeaders().contentTypeJson()
                .andExecuteFor()
                .ignoringResponse()
    }

}
