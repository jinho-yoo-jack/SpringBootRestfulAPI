package com.kpsec.test.service;

import com.kpsec.test.model.BranchAccountTotalResult;
import com.kpsec.test.model.ExchangeHistoryBranchResult;
import com.kpsec.test.model.ExchangeHistoryResult;
import com.kpsec.test.repository.BranchRepository;
import com.kpsec.test.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public BranchAccountTotalResult getAccountSumTotalByBranch(String branchCode) {
        BranchAccountTotalResult data = branchRepository.getAccountSumTotalByBranch(branchCode);
        return data;
    }
}
