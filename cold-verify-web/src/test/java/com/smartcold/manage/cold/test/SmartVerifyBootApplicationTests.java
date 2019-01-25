package com.smartcold.manage.cold.test;

import com.smartcold.manage.cold.config.InfluxDBConfig;
import okhttp3.OkHttpClient;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath*:config/spring-conf/appcontext*.xml" })
public class SmartVerifyBootApplicationTests {

//	@Resource
//	InfluxDB influxDB;
//
	String sql="select time, data from store where reportId = '12' and time>=1529630580000000000 and time<=1529976180000000000 group by cardId";

	private static final int threadNum = 500;

	private Logger logger= LoggerFactory.getLogger(SmartVerifyBootApplicationTests.class);


	@Test
	public void contextLoads() {
		//CountDownLatch cdl = new CountDownLatch(threadNum);
		for (int i = 0; i < threadNum; i++) {
			new Thread(new UserRequest()).start();
		}
		//cdl.countDown();
		try {
			Thread.sleep(30000);
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

			OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(0, TimeUnit.SECONDS).readTimeout(0, TimeUnit.SECONDS).writeTimeout(0, TimeUnit.SECONDS);
			InfluxDB influxDB = InfluxDBFactory.connect("http://192.168.1.199:8086/", "admin", "sc12qwaszx",builder).setDatabase("verifydata").enableBatch().enableGzip();

			long starttime = System.currentTimeMillis();
			QueryResult queryResult = influxDB.query(new Query(sql, InfluxDBConfig.DATABASE));
			List<QueryResult.Result> results = queryResult.getResults();
			logger.info(Thread.currentThread().getName() + "==============>查询完成,用时"+ (System.currentTimeMillis() - starttime));
		}

	}
}

