package com.quiddia.model

import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by warden on 21.03.15.
 */
class Loan {
    int loanId
    double amount
    private AtomicInteger currentId = new AtomicInteger(0)

    int getNextLoanId() {
        return currentId.incrementAndGet().toString()
    }

}
