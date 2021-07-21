package com.kpsec.test.infra;

import com.kpsec.test.model.entity.Account;
import com.kpsec.test.model.entity.Branch;
import com.kpsec.test.model.entity.ExchangeHistory;
import com.kpsec.test.model.entity.ExchangeHistoryPK;
import com.kpsec.test.repository.AccountRepository;
import com.kpsec.test.repository.BranchRepository;
import com.kpsec.test.repository.ExchangeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InitData {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ExchangeHistoryRepository exchangeHistoryRepository;
    @Autowired
    BranchRepository BranchRepository;

    @PostConstruct
    private void initAccount() throws IOException {
        if (accountRepository.count() == 0) {
            Resource resource = new ClassPathResource("계좌정보.csv");
            List<Account> accountList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8)
                    .stream().skip(1).map(line -> {
                        String[] split = line.split(",");
                        return Account.builder().accountNo(split[0]).accountName(split[1]).branchCode(split[2])
                                .build();
                    }).collect(Collectors.toList());
            accountRepository.saveAll(accountList);
        }
    }

    @PostConstruct
    private void initExchangeHistory() throws IOException {
        if (BranchRepository.count() == 0) {
            Resource resource = new ClassPathResource("데이터_관리점정보.csv");
            List<Branch> branchList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8)
                    .stream().skip(1).map(line -> {
                        String[] split = line.split(",");
                        return Branch.builder()
                                .code(split[0])
                                .name(split[1])
                                .build();
                    }).collect(Collectors.toList());
            BranchRepository.saveAll(branchList);
        }
    }

    @PostConstruct
    private void initBranch() throws IOException {
        if (exchangeHistoryRepository.count() == 0) {
            Resource resource = new ClassPathResource("데이터_거래내역.csv");
            List<ExchangeHistory> exchangeHistoryList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8)
                    .stream().skip(1).map(line -> {
                        String[] split = line.split(",");
                        return ExchangeHistory.builder()
                                .pk(new ExchangeHistoryPK(split[0], split[1], Integer.parseInt(split[2])))
                                .exchangePrice(Integer.parseInt(split[3])).price(Integer.parseInt(split[4]))
                                .cancelYn(split[5])
                                .build();
                    }).collect(Collectors.toList());
            exchangeHistoryRepository.saveAll(exchangeHistoryList);
        }
    }

}
