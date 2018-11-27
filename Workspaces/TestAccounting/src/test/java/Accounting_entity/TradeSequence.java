package Accounting_entity;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 类名称: <br>
 * 类描述: <br>
 *
 * @author: jieqiong.wu
 * @since: 17/12/27 下午9:27
 * @update: 17/12/27
 * @version: 1.0.0
 */
public class TradeSequence {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    private String seqName;

    private String currentVal;

    private String incrementVal;

    public String getSeqName() {
        return seqName;
    }

    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }

    public String getCurrentVal() {
        return currentVal;
    }

    public void setCurrentVal(String currentVal) {
        this.currentVal = currentVal;
    }

    public String getIncrementVal() {
        return incrementVal;
    }

    public void setIncrementVal(String incrementVal) {
        this.incrementVal = incrementVal;
    }
}
