package com.yeepay.g3.core.ymf.utils.sequence;

import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.core.ymf.utils.web.IpUtils;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 序列生成器 --单例
 * <p/>
 * 机器ID为 0-10的随机数
 * <p/>
 * 数据中心为 0-10的随机数
 */
public class SequenceGenerator {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SequenceGenerator.class);

	// id生成器列表
	private List<IdWorker> idWorkers;

	private SequenceGenerator() {
		// 获取本机IP地址
		String hostIp = IpUtils.getHostIp();
		int datacenterId = 0;
		int datacenterIdMax = 31;
		int workerIdMax = 31;
		// 获取应用服务器所在的数据中心
		Map<String, String> sourceIp2Datacenter = ConfigureSetting
				.getSequenceSourceIp2Datacenter();
		LOGGER.info("sourceIp2Datacenter:"
				+ JSONUtils.toJsonString(sourceIp2Datacenter) + ",hostIP:"
				+ hostIp);
		if (null != sourceIp2Datacenter) {
			String seedStr = sourceIp2Datacenter.get(hostIp);
			if (!StringUtils.isBlank(seedStr)) {
				datacenterId = Integer.valueOf(seedStr);
				datacenterIdMax = datacenterId;
			}
		}
		// 如果服务器没有配置数据中心,则默认31个数据中心(可能两台服务器datacenterId、workerId都一样,在同一毫秒并发会产生重复)
		// 模拟(datacenterId + datacenterIdIncr)个数据中心,每个数据中心31台序列服务器
		idWorkers = new ArrayList<IdWorker>();
		for (; datacenterId <= datacenterIdMax; datacenterId++) {
			for (int workerId = 0; workerId <= workerIdMax; workerId++) {
				idWorkers.add(new IdWorker(datacenterId, workerId));
			}
		}
	}

	private static class SingletonHolder {
		private static SequenceGenerator instance = new SequenceGenerator();
	}

	public static SequenceGenerator getInstance() {
		return SingletonHolder.instance;
	}

	private static final Random random = new Random();

	public Long generate() {
		// 随机选其中一个序列生成器
		IdWorker idWorker = idWorkers.get(random.nextInt(idWorkers.size()));
		return idWorker.nextId();
	}

	public static void main(String[] args) {

		int i = 0;
		while (i < 10) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						System.out.println(getInstance().generate());
						try {
							Thread.sleep(500L);
						} catch (InterruptedException e) {

						}
					}

				}
			});
			thread.start();
			i++;

		}

	}
}
