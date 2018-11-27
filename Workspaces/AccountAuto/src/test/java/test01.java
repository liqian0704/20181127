import utility.BusinessUtil;

import java.io.IOException;

/**
 * Created by qian.li on 18/10/11.
 */
public class test01 {

    public static void main(String[] args) throws IOException {
        try {
            System.out.println( BusinessUtil.getBillStartDate("1042781335","PAY_FEE_BILL"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(BusinessUtil.getBillEndDate("1042781335","PAY_FEE_BILL"));
    }
}
