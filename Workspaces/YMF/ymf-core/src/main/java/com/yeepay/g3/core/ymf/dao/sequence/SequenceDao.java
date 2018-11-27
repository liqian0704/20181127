package com.yeepay.g3.core.ymf.dao.sequence;

import org.springframework.stereotype.Repository;

/**
 * Created by yp-tc-m-2762 on 16/9/6.
 */
@Repository
public interface SequenceDao {
    public Long getSequenceValueByName();
}
