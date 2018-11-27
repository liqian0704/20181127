package com.yeepay.g3.facade.ymf.facade.laike;

/**
 * 打款信息查询
 * Created by dongxulu on 17/4/28.
 */
public interface  RemitInfoQueryFacade {


    /**
     * 定时跑批,查询打款信息
     *
     */
    void queryRemitInfo(int pageNo,int count);

    void doRemit( );
}
