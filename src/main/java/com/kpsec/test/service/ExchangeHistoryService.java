package com.kpsec.test.service;

import com.kpsec.test.model.ExchangeHistoryBranchResult;
import com.kpsec.test.model.ExchangeHistoryByYearResult;
import com.kpsec.test.model.ExchangeHistoryResult;
import com.kpsec.test.repository.ExchangeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kpsec.test.utils.StringUtils;

@Service
@Transactional
public class ExchangeHistoryService {

    @Autowired
    private ExchangeHistoryRepository exchangeHistoryRepository;

    public List<ExchangeHistoryByYearResult> getTotalMaxByYear(String year) {
        List<ExchangeHistoryByYearResult> result = new ArrayList<ExchangeHistoryByYearResult>();
        String separator = ",";
        String y[] = StringUtils.splitBySeparator(year, separator);
        for (String e : y) {
            result.add(exchangeHistoryRepository.getTotalMaxByYear(e));
        }

        return result;
    }

    public List<ExchangeHistoryResult> getAccountListByCancelY(String year) {
        String separator = ",";
        String y[] = StringUtils.splitBySeparator(year, separator);
        List<ExchangeHistoryResult> result = exchangeHistoryRepository.getAccountListByCancelY(y);
        return result;
    }

    public HashMap<String, List<ExchangeHistoryBranchResult>> getAccountListByBranch(String year) {
        String separator = ",";
        String y[] = StringUtils.splitBySeparator(year, separator);
        HashMap<String, List<ExchangeHistoryBranchResult>> result
                = new HashMap<String, List<ExchangeHistoryBranchResult>>();
        for (String e : y) {
            System.out.println(e);
            List<ExchangeHistoryBranchResult> data = exchangeHistoryRepository.getAccountListByBranch(e);
            result.put(e, data);
        }
        return result;
    }
}
