package com.astral.embeddb;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"/applicationContext.xml"})
public class EmbeddedDB_XMLTest {

	private static final Logger log = LoggerFactory.getLogger(EmbeddedDB_XMLTest.class);
	
	private DataSource dataSource;

	private Connection connection;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		assertNotNull(context);
		this.dataSource = (DataSource) context.getBean("dataSource-H2");
		assertNotNull(dataSource);
	}
	

	@Test
	public void testConnection() throws SQLException {
		connection = dataSource.getConnection();
		log.debug("testConnection"+connection.toString());
	}
	
	@Test
	public void findByIdWithJDBC() throws SQLException {
		connection = dataSource.getConnection();
		PreparedStatement pstmt ;
		ResultSet rs ;
		
		String query="select id,name,email from users ";
		pstmt = connection.prepareStatement(query);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			log.debug(rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3));
			
		}
		
		rs.close();
		pstmt.close();
	}
	
	@After
	public void tearDown() throws SQLException {
		connection.close();
	}

}
