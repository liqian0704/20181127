package com.yeepay.g3.core.ymf.utils.common;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 精度浮点数操作类
 */
public class Amount implements Serializable {
    private static final long serialVersionUID = -2695438548697471721L;

    // 默认精度2
    private static final int DEF_DIV_SCALE = 2;

    /**
     * 累加
     * @param doubles 累加数
     * @return 和
     */
    public static double add(double...doubles) {
        BigDecimal add = BigDecimal.ZERO;
        for (double d : doubles) {
            add = add.add(new BigDecimal(d));
        }
        return round(add.doubleValue());
    }

    /**
     * 累加
     * @param bigDecimals 累加数
     * @return 和
     */
    public static BigDecimal add(BigDecimal...bigDecimals) {
        BigDecimal add = BigDecimal.ZERO;
        for (BigDecimal bigDecimal : bigDecimals) {
            if (null != bigDecimal) { // null
                add = add.add(bigDecimal);
            }
        }
        return add;

    }

    /**
     * 累减
     * @param doubles 第一个数是减数 后边的数是被减数
     * @return 差
     */
    public static double sub(double...doubles) {
        if (doubles.length < 2) {
            throw new IllegalArgumentException("参数个数必须大于2");
        }
        BigDecimal sub = new BigDecimal(doubles[0]);
        for (int i = 1, length = doubles.length; i < length; i ++) {
            sub = sub.subtract(new BigDecimal(doubles[i]));
        }
        return round(sub.doubleValue());
    }

    /**
     * 累减
     * @param bigDecimals 第一个数是减数 后边的数是被减数
     * @return 差
     */
    public static BigDecimal sub(BigDecimal...bigDecimals) {
        if (bigDecimals.length < 2) {
            throw new IllegalArgumentException("参数个数必须大于2");
        }
        BigDecimal sub = bigDecimals[0];
        for (int i = 1, length = bigDecimals.length; i < length; i ++) {
            sub = sub.subtract(bigDecimals[i]);
        }
        return sub;

    }

    /**
     * 累积
     * @param doubles 累积数
     * @return 乘积
     */
    public static double mul(double...doubles) {
        BigDecimal mul = BigDecimal.ZERO;
        for (double d : doubles) {
            mul = mul.multiply(new BigDecimal(d));
        }
        return round(mul.doubleValue());

    }

    /**
     * 累除
     * @param doubles 第一个是被除数 后边是除数
     * @return 乘积
     */
    public static double div(double...doubles) {
        BigDecimal mul = BigDecimal.ZERO;
        for (double d : doubles) {
            mul = mul.divide(new BigDecimal(d));
        }
        return round(mul.doubleValue());

    }

    /**
     * 四舍五入
     * @param v
     * @param scale
     * @return
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        return b.divide(BigDecimal.ONE, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 四舍五入，精度为2
     */
    public static BigDecimal setScale(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            bigDecimal = new BigDecimal(0);
        }
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 获取0
     */
    public static BigDecimal getZeroAmount() {
        return Amount.setScale(null);
    }

    /**
     * 四舍五入
     * @param v
     * @return
     */
    public static double round(double v) {
        return round(v, DEF_DIV_SCALE);
    }

    /**
     * a > b
     * @param a
     * @param b
     * @return
     */
    public static boolean bigger(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) >= 1;
    }

    /**
     * a >= b
     * @param a
     * @param b
     * @return
     */
    public static boolean biggerThan(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) >= 0;
    }



    // forbid init
    private Amount() {

    }

}
