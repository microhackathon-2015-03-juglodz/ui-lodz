package com.quiddia.controllers

import com.quiddia.model.Client
import com.quiddia.model.Loan
import com.quiddia.services.LoanApplicationService
import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.validation.constraints.NotNull

import static org.springframework.web.bind.annotation.RequestMethod.POST

@Slf4j
@RestController
@RequestMapping('/loan')
class LoanApplicationController {

    @Autowired
    LoanApplicationService loanApplicationService;

    @RequestMapping(method = POST)
    void applyForLoan(@RequestBody Client client) {

        log.debug("Client = {}", client)

        loanApplicationService.sendClientDetails(
                new Client(firstName: client.name, lastName: client.surName, age: client.age, loanId: 1))

        loanApplicationService.applyForLoan(
                new Loan(amount: client.amount))

    }

}
