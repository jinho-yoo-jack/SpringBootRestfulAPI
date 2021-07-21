package com.kpsec.test.repository;

import com.kpsec.test.model.BranchAccountTotalResult;
import com.kpsec.test.model.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, String> {

    @Query(value = "SELECT * FROM (SELECT  SUM(T1.EXCHANGE_PRICE) AS sumAmt , T2.BRANCH_CODE as branchCode, T3.NAME AS branchName\n" +
            "FROM EXCHANGE_HISTORY T1\n" +
            "INNER JOIN ACCOUNT T2\n" +
            "ON T1.ACCOUNT_NO = T2.ACCOUNT_NO\n" +
            "INNER JOIN BRANCH T3\n" +
            "ON T3.CODE= T2.BRANCH_CODE\n" +
            "WHERE T1.CANCEL_YN = 'Y'\n" +
            "GROUP BY T2.BRANCH_CODE) T1 WHERE branchCode = :branchCode", nativeQuery = true)
    BranchAccountTotalResult getAccountSumTotalByBranch(@Param("branchCode") String branchCode);
}
