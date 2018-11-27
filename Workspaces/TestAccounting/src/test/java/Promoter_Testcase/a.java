package Promoter_Testcase;

import org.testng.annotations.Test;
import utility.BusinessUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ${qian.li_3} on 18/7/4.
 */
public class a {

    @Test
    public void test(){
        BusinessUtil.printDATE();
    }
    @Test
    public void testDate(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar cal1=Calendar.getInstance();
        cal1.add(cal1.MONTH,1);
        System.out.println(sdf.format(cal1.getTime()));
    }
    @Test
    public void testbillNo() throws IOException, ParseException {
        String billNo=BusinessUtil.selectTotalBillNoByDate("1042781335","2018-08-01 00:00:00");

         System.out.println(billNo);

    }
}
