import com.kpsec.test.TestApplication;
import com.kpsec.test.model.BranchAccountTotalResult;
import com.kpsec.test.model.ExchangeHistoryBranchResult;
import com.kpsec.test.model.ExchangeHistoryByYearResult;
import com.kpsec.test.model.ExchangeHistoryResult;
import com.kpsec.test.repository.BranchRepository;
import com.kpsec.test.repository.ExchangeHistoryRepository;
import com.kpsec.test.utils.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

import com.kpsec.test.utils.StringUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class ApiTest {

    @Autowired
    private ExchangeHistoryRepository exchangeHistoryRepository;
    @Autowired
    private BranchRepository branchRepository;

    @Test
    public void getTotalMaxByYear() {
        String year = "2018";
        ExchangeHistoryByYearResult data = exchangeHistoryRepository.getTotalMaxByYear(year);
        assertEquals(29000000, data.getSumAmt());
        assertEquals("테드", data.getName());
        assertEquals("11111114", data.getAcctNo());
    }


    @Test
    public void getAccountListByCancelYTesting() {
        String[] year = {"2018"};
        List<ExchangeHistoryResult> data = exchangeHistoryRepository.getAccountListByCancelY(year);
        assertEquals(15, data.size());
    }

    @Test
    public void getAccountListByBranchTesting() {
        String year = "2018";
        List<ExchangeHistoryBranchResult> data = exchangeHistoryRepository.getAccountListByBranch(year);
        assertEquals(4, data.size());
    }

    @Test
    public void  getAccountSumTotalByBranch() {
        String branchCode = "A";
        BranchAccountTotalResult data = branchRepository.getAccountSumTotalByBranch(branchCode);
        assertEquals("A", data.getBranchCode());
        assertEquals("판교점", data.getBranchName());
        assertEquals(133800000, data.getSumAmt());
    }

    @Test
    public void splitBySeparatorTesting() {
        String year = "2018,2019";
        String separator = ",";
        String y[] = StringUtils.splitBySeparator(year, separator);
        assertEquals(2,y.length);
        assertEquals("2018",y[0]);
        assertEquals("2019",y[1]);
        year = "2018";
        String y_one[] = StringUtils.splitBySeparator(year, separator);
        assertEquals(2,y.length);
        assertEquals("2018",y[0]);
    }

}
