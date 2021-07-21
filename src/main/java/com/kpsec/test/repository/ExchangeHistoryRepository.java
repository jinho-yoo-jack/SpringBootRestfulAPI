package com.kpsec.test.repository;

import com.kpsec.test.model.ExchangeHistoryBranchResult;
import com.kpsec.test.model.ExchangeHistoryByYearResult;
import com.kpsec.test.model.ExchangeHistoryResult;
import com.kpsec.test.model.entity.ExchangeHistory;
import com.kpsec.test.model.entity.ExchangeHistoryPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExchangeHistoryRepository extends JpaRepository<ExchangeHistory, ExchangeHistoryPK> {
    @Query(value = "SELECT exchange_date as exchangeDate, account_no as accountNo, exchange_no as exchangeNo," +
            "exchange_price as exchangePrice, price as price, cancel_yn as cancelYn  FROM exchange_history", nativeQuery = true)
    List<ExchangeHistoryResult> getExchangeHistoryByAll();


    @Query(value = "SELECT year,name,acctNo,sum(price) as sumAmt \n" +
            "FROM(\n" +
            "select left(T1.EXCHANGE_DATE, 4) as year, T1.ACCOUNT_NO as acctNo, T2.ACCOUNT_NAME as name, T1.EXCHANGE_PRICE as price \n" +
            "from EXCHANGE_HISTORY T1 \n" +
            "INNER JOIN ACCOUNT T2 \n" +
            "ON T1.ACCOUNT_NO  = T2.ACCOUNT_NO \n" +
            "WHERE T1.CANCEL_YN ='N'\n" +
            ") T3\n" +
            "WHERE T3.year = :year \n" +
            "GROUP BY T3.acctNo, T3.name \n" +
            "ORDER BY sumAmt desc \n" +
            "LIMIT 1; ", nativeQuery = true)
    ExchangeHistoryByYearResult getTotalMaxByYear(@Param("year") String year);

    @Query(value = "SELECT year, acctNo,name " +
            "FROM(" +
            "   SELECT left(T1.EXCHANGE_DATE, 4) as year, T1.ACCOUNT_NO as acctNo, T2.ACCOUNT_NAME AS name\n" +
            "   FROM exchange_history T1\n" +
            "   INNER JOIN account T2\n" +
            "   ON T1.account_no = T2.account_no\n" +
            "   WHERE T1.CANCEL_YN = 'Y'" +
            ") T3 " +
            "WHERE year IN (:year) ", nativeQuery = true)
    List<ExchangeHistoryResult> getAccountListByCancelY(@Param("year") String[] year);

    @Query(value = "SELECT * " +
            "FROM (SELECT LEFT(T1.EXCHANGE_DATE, 4) AS year, SUM(T1.EXCHANGE_PRICE) AS sumAmt , T2.BRANCH_CODE as branchCode, T3.NAME AS branchName\n" +
            "FROM EXCHANGE_HISTORY T1\n" +
            "INNER JOIN ACCOUNT T2\n" +
            "ON T1.ACCOUNT_NO = T2.ACCOUNT_NO\n" +
            "INNER JOIN BRANCH T3\n" +
            "ON T3.CODE= T2.BRANCH_CODE\n" +
            "WHERE T1.CANCEL_YN = 'Y'\n" +
            "GROUP BY YEAR,T2.BRANCH_CODE) T1 " +
            "WHERE year = :year", nativeQuery = true)
    List<ExchangeHistoryBranchResult> getAccountListByBranch(@Param("year") String year);

}
