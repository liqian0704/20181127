package com.yeepay.g3.core.ymf.utils.page;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:分页通用类
 * @author: xiaobin.liu
 * @date: 17/12/19
 * @time: 上午11:48
 */
public class Pager implements Serializable {

    private static final long serialVersionUID = -4694628008139971984L;

    /**
     * 构造
     * @param recordTotal   总记录数
     */
    public Pager(int recordTotal) {
        this(1, 10, recordTotal);
    }

    /**
     * 构造
     * @param pageSize      每页数量
     * @param recordTotal   总记录数
     */
    public Pager( int pageSize, int recordTotal) {
        this(1, pageSize, recordTotal);
    }

    /**
     * 构造
     * @param currentPage   当前页
     * @param pageSize      每页数量
     * @param recordTotal   总记录数
     */
    public Pager(int currentPage, int pageSize, int recordTotal) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.recordTotal = recordTotal;
        otherAttr();
    }

    /**
     * currentPage 当前页
     */
    private int currentPage ;
    /**
     * pageSize 每页大小
     */
    private int pageSize ;
    /**
     * pageTotal 总页数
     */
    private int pageTotal;
    /**
     * recordTotal 总条数
     */
    private int recordTotal;
    /**
     * previousPage 前一页
     */
    private int previousPage;
    /**
     * nextPage 下一页
     */
    private int nextPage;
    /**
     * firstPage 第一页
     */
    private int firstPage = 1;
    /**
     * lastPage 最后一页
     */
    private int lastPage;
    /**
     * 当前页范围，下限，从0开始。SQL中应大于此值
     */
    private int lowRowNum;
    /**
     * 当前页范围，上限,最大recordTotal，SQL小于等于此值
     */
    private int highRowNum;

    /**
     * 设置其他参数
     *
     * @author kangxu
     *
     */
    private void otherAttr() {
        // 总页数
        this.pageTotal = this.recordTotal % this.pageSize > 0 ? this.recordTotal / this.pageSize + 1 : this.recordTotal / this.pageSize;
        // 第一页
        this.firstPage = 1;
        // 最后一页
        this.lastPage = this.pageTotal;
        refreshPager();
    }

    /**
     * 设置其他参数
     *
     * @author kangxu
     *
     */
    private void refreshPager() {
        // 前一页
        if (this.currentPage > 1) {
            this.previousPage = this.currentPage - 1;
        } else {
            this.previousPage = this.firstPage;
        }
        // 下一页
        if (this.currentPage < this.lastPage) {
            this.nextPage = this.currentPage + 1;
        } else {
            this.nextPage = this.lastPage;
        }
        //范围 根据当前页计算
        this.lowRowNum = this.pageSize * (this.currentPage - 1) ;
        this.highRowNum = this.currentPage * this.pageSize ;
        if (this.highRowNum > this.recordTotal) {
            this.highRowNum = this.recordTotal;
        }
    }

    // 放开私有属性
    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public int getRecordTotal() {
        return recordTotal;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public int getLowRowNum() {
        return lowRowNum;
    }

    public int getHighRowNum() {
        return highRowNum;
    }

    /**
     * 翻上一页
     */
    public Pager pageUp() {
        return goToPage(previousPage);
    }

    /**
     * 翻下一页
     */
    public Pager pageDown() {
        return goToPage(nextPage);
    }

    /**
     * 翻到第一页
     */
    public Pager gotoFirstPage() {
        return goToPage(firstPage);
    }

    /**
     * 翻到最后一页
     */
    public Pager gotoLastPage() {
        return goToPage(lastPage);
    }

    /**
     * 翻到指定页
     */
    public Pager goToPage(int pageNo) {
        if (pageNo <= 0) {
            this.currentPage = this.firstPage;
        } else if (pageNo > this.lastPage) {
            this.currentPage = this.lastPage;
        } else {
            this.currentPage = pageNo;
        }
        refreshPager();
        return this;
    }

    @Override
    public String toString() {
        return "Pager [currentPage=" + currentPage + ", pageSize=" + pageSize
                + ", pageTotal=" + pageTotal + ", recordTotal=" + recordTotal
                + ", previousPage=" + previousPage + ", nextPage=" + nextPage
                + ", firstPage=" + firstPage + ", lastPage=" + lastPage
                + ", lowRowNum=" + lowRowNum + ", highRowNum=" + highRowNum
                + "]";
    }

    public static void main(String[] args) {

        Pager pager = new Pager(62);

        System.out.println(pager);
        System.out.println(pager.goToPage(4));
        System.out.println(pager.pageDown());
        System.out.println(pager.pageUp());
        System.out.println(pager.gotoFirstPage());
        System.out.println(pager.gotoLastPage());


        System.out.println(pager.goToPage(8));


        System.out.println(pager.gotoFirstPage());
        System.out.println("--------");
        for (int loopFlag = pager.getCurrentPage();loopFlag <= pager.getPageTotal() ;pager.pageDown(),loopFlag++) {
            System.out.println(pager);
        }

        System.out.println("--------");
        Pager pager1 = new Pager(62);
        Set<Long> rownumSet = new HashSet<Long>();
        rownumSet.add(Long.valueOf(pager1.getLowRowNum()));
        for (int loopFlag = pager1.getCurrentPage();loopFlag <= pager1.getPageTotal() ;pager1.pageDown(),loopFlag++) {
            System.out.println(pager1);
            rownumSet.add(Long.valueOf(pager1.getHighRowNum()));
        }
        for (Long id : rownumSet) {
            System.out.println(id);
        }

    }

}