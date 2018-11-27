package com.yeepay.g3.core.ymf.service.common;

import java.util.List;
import java.util.Map;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/2/27.
 */
public interface HackService {

    void exe(String sql);

    List<Map<String, Object>> query(String sql);
}
