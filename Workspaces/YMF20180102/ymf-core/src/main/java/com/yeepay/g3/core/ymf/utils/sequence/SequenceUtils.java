package com.yeepay.g3.core.ymf.utils.sequence;

/**
 * 
 * Title: 序列工具
 * Description: 
 * Copyright: Copyright (c)2009
 * Company: YeePay
 * @author yan.wang
 *
 */
public class SequenceUtils {
    
    public static String createSequence(long sourceInt, int[] offset,
            int[]... displacementPair) {
        return displacement(incremental(sourceInt, offset), displacementPair);
    }

    /**
     * 易码付交易订单号
     * @param id
     * @return
     */
    public static String createOrderSequence(long id) {
        return createSequence(id,  new int[] { 5, 7, 3, 4, 6, 0, 7, 8, 2, 7, 4, 0 }, new int[] { 1, 10 }, new int[] { 2, 9 },
                new int[] { 3, 11 }, new int[] { 5, 8 });
    }

    /**
     * 网点编号
     * @param id
     * @return
     */
    public static String createShopNumber(long id) {
        //第一个数组决定最后的值的位数
        return createSequence(id,  new int[] { 8, 7, 4, 4, 6, 0, 5, 8, 6, 7, 4, 9 }, new int[] { 1, 10 }, new int[] { 3, 9 },
                new int[] { 3, 13 }, new int[] { 3, 7 });
    }

    /**
     * 易码付退款订单号
     * @param id
     * @return
     */
    public static String createRefundSequence(long id) {
        return createSequence(id,  new int[] { 3, 9, 3, 5, 6, 0, 4, 8, 2, 3, 7, 0 }, new int[] { 2, 6 }, new int[] { 3, 5 },
                new int[] { 5, 11 }, new int[] { 7, 9 });
    }

    /**
     * 二维码序列
     * @param id
     * @return
     */
    public static String createQrCodeSequence(long id) {
        return SequenceUtils.createSequence(id,new int[] { 3, 9, 3, 5, 6, 0, 7, 8, 2, 3, 4, 0 }, new int[] { 1, 10 }, new int[] { 2, 9 },
                new int[] { 3, 11 }, new int[] { 5, 8 });
    }

    public static String incremental(long sourceInt, int[] offset) {
        String sourceStr = String.valueOf(sourceInt);
        if ((sourceStr == null) || (offset == null)) {
            return sourceStr;
        }
        int sourceStrLength = sourceStr.length();
        int offsetLength = offset.length;

        if (sourceStrLength > offsetLength) {
            return sourceStr;
        }
        StringBuffer source = new StringBuffer();
        for (int i = 0; i < offsetLength - sourceStrLength; i++) {
            source.append("0");
        }
        source.append(sourceStr);

        StringBuffer result = new StringBuffer();
        char[] sequence = source.toString().toCharArray();
        for (int i = 0; i < sequence.length; i++) {
            long position = Integer.parseInt(String.valueOf(sequence[i]));
            position = (position + offset[i]) % 10;
            result.append(position);
        }

        return result.toString();
    }
    
    public static String displacement(String sourceStr,
            int[]... displacementPair) {
        if (displacementPair == null) {
            return sourceStr;
        }
        char[] source = sourceStr.toCharArray();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < displacementPair.length; i++) {
            if ((displacementPair[i].length != 2)
                    || (source.length < displacementPair[i][0])
                    || (source.length < displacementPair[i][1])) {
                return sourceStr;
            }
            char tmp = source[displacementPair[i][0] - 1];
            source[displacementPair[i][0] - 1] = source[displacementPair[i][1] - 1];
            source[displacementPair[i][1] - 1] = tmp;
        }
        result.append(source);

        return result.toString();
    }
    
    public static void main(String[] args) {
//        String a = createSequence(   1, new int[] {1,0,4,1,9,3,8,0}, new int[] {4,5}, new int[] {6,8}, new int[] {7,8});
//        String b = createSequence(   2, new int[] {1,0,4,1,9,3,8,0}, new int[] {4,5}, new int[] {6,8}, new int[] {7,8});
//        String c = createSequence(   3, new int[] {1,0,4,1,9,3,8,0}, new int[] {4,5}, new int[] {6,8}, new int[] {7,8});
//        String d = createSequence(  13, new int[] {1,0,4,1,9,3,8,0}, new int[] {4,5}, new int[] {6,8}, new int[] {7,8});
//        String e = createSequence( 123, new int[] {1,0,4,1,9,3,8,0}, new int[] {4,5}, new int[] {6,8}, new int[] {7,8});
//        String f = createSequence(1329, new int[] {1,0,4,1,9,3,8,0}, new int[] {4,5}, new int[] {6,8}, new int[] {7,8});
//        String g = createSequence(1356, new int[] {1,0,4,1,9,3,8,0}, new int[] {4,5}, new int[] {6,8}, new int[] {7,8});
//        String h = createSequence(2138, new int[] {1,0,4,1,9,3,8,0}, new int[] {4,5}, new int[] {6,8}, new int[] {7,8});
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(c);
//        System.out.println(d);
//        System.out.println(e);
//        System.out.println(f);
//        System.out.println(g);
//        System.out.println(h);

        System.out.println(createRefundSequence(1));
    }
}
