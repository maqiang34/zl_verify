package com.smartcold.manage.cold.test;

import com.smartcold.manage.cold.config.InfluxDBConfig;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:config/spring-conf/appcontext*.xml" })
public class InfluxdbTests {

//	@Resource
//	InfluxDB influxDB;
//
	String sql="select time, data from store where reportId = '12' and time>=1529630580000000000 and time<=1529976180000000000 group by cardId";

	private static final int threadNum = 200;

	private Logger logger= LoggerFactory.getLogger(InfluxdbTests.class);


	@Test
	public void contextLoads() {
		//CountDownLatch cdl = new CountDownLatch(threadNum);
		for (int i = 0; i < threadNum; i++) {
			new Thread(new UserRequest()).start();
		}
		//cdl.countDown();
		try {
			Thread.sleep(300000);
		 // Thread.currentThread().join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}





	private class UserRequest implements Runnable {


		@Override
		public void run() {
			try {
				//this.cdl.await();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			InfluxDB influxDB =InfluxDBConfig.getInfluxDB();
			long starttime = System.currentTimeMillis();
			QueryResult queryResult = influxDB.query(new Query(sql, InfluxDBConfig.DATABASE));
			List<QueryResult.Result> results = queryResult.getResults();
			logger.info(Thread.currentThread().getName() + "==============>查询完成,用时"+ (System.currentTimeMillis() - starttime));
		}

	}
}

