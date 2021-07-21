package com.kpsec.test.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ExchangeHistoryPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "exchangeDate", nullable = false)
    private String exchangeDate;

    @Column(name = "accountNo", nullable = false)
    private String accountNo;

    @Column(name = "exchangeNo", nullable = false)
    private int exchangeNo;

    public ExchangeHistoryPK() {}

    public ExchangeHistoryPK(String exchangeDate, String accountNo, int exchangeNo) {
        super();
        this.exchangeDate = exchangeDate;
        this.accountNo = accountNo;
        this.exchangeNo = exchangeNo;
    }

}
