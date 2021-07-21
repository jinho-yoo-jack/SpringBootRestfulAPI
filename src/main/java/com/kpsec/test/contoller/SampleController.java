package com.kpsec.test.contoller;

import com.google.gson.JsonObject;
import com.kpsec.test.model.*;
import com.kpsec.test.service.AccountService;
import com.kpsec.test.service.BranchService;
import com.kpsec.test.service.ExchangeHistoryService;
import com.kpsec.test.utils.ErrorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Api(tags = "Sample")
@RestController
@RequestMapping("/test/")
public class SampleController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ExchangeHistoryService exchangeHistoryService;
    @Autowired
    private BranchService branchService;



    @ApiOperation(value = "sample")
    @GetMapping(value = "/acount")
    public List<AccountResult> getAccountInfo(String branchCode) {
        return accountService.getAccountByBranchCode(branchCode);
    }

    @ApiOperation(value = "exchangePriceMaxByYear")
    @GetMapping(value = "/exchange_year")
    public List<ExchangeHistoryByYearResult> getAccountListByYear(String year) {
        return exchangeHistoryService.getTotalMaxByYear(year);
    }

    @ApiOperation(value = "exchangeHistory")
    @GetMapping(value = "/exchange")
    public List<ExchangeHistoryResult> getAccountListByCancelY(String year) {
        return exchangeHistoryService.getAccountListByCancelY(year);
    }

    @ApiOperation(value = "exchangeHistoryByBranch")
    @GetMapping(value = "/exchange_branch")
    public List<Object> getAccountListByBranch(String year) {
        List<Object> result = new ArrayList<>();

        HashMap<String, List<ExchangeHistoryBranchResult>> data = exchangeHistoryService.getAccountListByBranch(year);
        data.forEach((key, value) -> {
            HashMap<String, Object> data_group = new HashMap<String, Object>();
            data_group.put("year", key);
            data_group.put("dataList", value);
            result.add(data_group);
        });

        return result;
    }

    @ApiOperation(value = "AccountTotalByBranchCode")
    @GetMapping(value = "/account_total_branch")
    public ResponseEntity<?> getAccountTotalByBranch(String branch_code) {
        if(branch_code.equals("B")){
            int error_code = 404;
            String message = "br code not found error";
            return ErrorUtils.makeErrorMessage(error_code, message);
        }else{
            BranchAccountTotalResult data = branchService.getAccountSumTotalByBranch(branch_code);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }

    }
}
