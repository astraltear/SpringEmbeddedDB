package com.astraltear.embedded.hsql;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@EnableTransactionManagement
//Transaction 처리

@MapperScan("com.astraltear.embedded.hsql")
//@MapperScan 어노테이션을 이용해서 Mapper 인터페이스를 스캔하도록 설정한다. 설정값으로 Mapper 인터페이스가 위치한 패키명(devfun.bookstore.common.mapper)을 선언한다.

@ComponentScan(
		basePackages= {"com.astraltear.embedded.service"},
		useDefaultFilters=false,
		includeFilters= {@Filter( value=Service.class )})
/*
	생성한 서비스 클래스를 사용하려면 구성 클래스에 추가해야 한다. 각각의 Service를 Bean 형태로 선언해서 등록할 수 있지만, 번거로울 수 있으므로 @ComponentScan
	어노테이션을 이용해서 스캔하도록 한다.
	다음과 같이 AppConfig 클래스에 @ComponentScan 어노테이션을 준다. 스캔 대상이 되는 기본 패키지를 devfun.bookstore.common.service로 선언하고,
	필터(@Filter)를 사용해서 Service 클래스만 스캔한다.
*/
@Configuration
public class AppConfig implements TransactionManagementConfigurer {
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setName("testdb")
				.setType(EmbeddedDatabaseType.HSQL)
				.addScript("db/sql/HSQL/create-db.sql")
				.addScript("db/sql/HSQL/insert-data.sql")
				.build();
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		return sessionFactory.getObject();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManager();
	}
	
}
