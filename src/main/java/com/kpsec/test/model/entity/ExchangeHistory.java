package com.kpsec.test.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ExchangeHistory {
    //거래일자,계좌번호,거래번호,금액,수수료,취소여부
    //20180102,11111111,1,1000000,0,N
    @EmbeddedId
    private ExchangeHistoryPK pk;

    private int exchangePrice;

    private int price;

    private String cancelYn;

}
