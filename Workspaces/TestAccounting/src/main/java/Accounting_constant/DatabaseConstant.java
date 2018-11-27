package Accounting_constant;

/**
 * 类名称: <br> DatabaseConstant
 * 类描述: <br> 数据库常量
 *
 * @author: jieqiong.wu
 * @since: 17/12/28 上午11:32
 * @update: 17/12/28
 * @version: 1.0.0
 */
public class DatabaseConstant {

    //订单分库分表最大容量，一旦确定，不能修改
    public static final int ORDER_SPLIT_TABLE_COUNT=10;
    public static final int ORDER_SPLIT_ORDER_COUNT=8;
    public static final int ORDER_NO_RANDOM_LENGTH=4;

    //db和表占用长度。2字段即最多99库，99表。足够用了
    public static final int DB_SHARD_LENGTH=2;
    public static final int TABLE_SHARD_LENGTH=2;

}
