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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class EmbeddedDB_JDBCTest {

	private static final Logger log = LoggerFactory.getLogger(EmbeddedDB_JDBCTest.class);

	
	private DataSource dataSource;


	private EmbeddedDatabase database;
	
	
	@Before
	public void setUp() throws Exception {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		database = builder.setType(EmbeddedDatabaseType.H2) // HSQL or DERBY
									.addScript("db/sql/H2/create-db.sql")
									.addScript("db/sql/H2/insert-data.sql")
									.build();
		this.dataSource = database;
		
	}

	@Test
	public void testConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
		log.debug("testConnection"+connection.toString());
		connection.close();
	}
	
	@Test
	public void findByIdWithJDBC() throws SQLException {
		Connection connection = dataSource.getConnection();
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
		connection.close();
	}
	
	@After
	public void tearDown() {
		database.shutdown();
	}

}
