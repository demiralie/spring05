// bean
package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;

@Component
@Getter
@CommonsLog
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class CountryDao {

	@Autowired
	DataSource ds; // 필드 주입

	@PostConstruct
	public void PostConstruct(){
		log.info("######################");
		log.info("### @PostConstruct ###");
		log.info("######################");
	}
	
	@PreDestroy
	public void PreDestroy() {
		log.info("###################");
		log.info("### @PreDestroy ###");
		log.info("###################");
	}
	
	
	public List<String> selectAll() throws SQLException {
		
		Connection conn =  ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select name from country");
		ResultSet rs = pstmt.executeQuery();
		
		List<String> list = new ArrayList<>();
		while (rs.next()) {
			list.add(rs.getString("name"));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return list;
	}
}
