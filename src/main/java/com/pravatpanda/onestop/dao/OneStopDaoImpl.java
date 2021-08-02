/*
 * Copyright (c) 2021. Pravat Panda
 * All rights reserved
 */

package com.pravatpanda.onestop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.pravatpanda.onestop.bean.AddRequest;
import com.pravatpanda.onestop.bean.URLEntry;
import com.pravatpanda.onestop.bean.URLinfo;
import com.pravatpanda.onestop.bean.UpdateRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * The type One stop dao.
 */
@Repository
public class OneStopDaoImpl implements OneStopDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * The Log.
	 */
	Logger log = Logger.getLogger(getClass());
	
	public List<URLinfo> displayGroup(boolean splUser) {

		String sql = null;
		if(splUser) {
			sql =
				"SELECT ID,GROUPNAME,TEXT, URL, ISLINK " +
				"FROM ONESTOP";
		} else {
			sql =
				"SELECT ID,GROUPNAME,TEXT, URL, ISLINK " +
				"FROM ONESTOP " +
				"WHERE ISSPL='false'";
		}
        log.info(sql);
        List<URLinfo> matchedRequests = jdbcTemplate.query(sql, new RowMapper<URLinfo>() {
        	public URLinfo mapRow(ResultSet rs , int index ) throws SQLException {

        		URLinfo uinfo = new URLinfo();

        		int id = rs.getInt("ID");
        		String groupname = rs.getString("GROUPNAME");
        		String text = rs.getString("TEXT");
        		String url = rs.getString("URL");
        		boolean islink = Boolean.parseBoolean(rs.getString("ISLINK"));

        		uinfo.setId(id);
        		uinfo.setGroupname(groupname);
        		uinfo.setText(text);
        		uinfo.setUrl(url);
        		uinfo.setLink(islink);

        		return uinfo;
        	}
        });
        return matchedRequests;
    }


	public boolean updateEntry(UpdateRequest updRequest) {

		System.out.println("u r in update mode");
		String sql2="Update ONESTOP set GROUPNAME= ? , TEXT= ? ,URL= ? ,ISLINK= ?, ISSPL = ? where ID= ? ";
		Object[] params = {
				updRequest.getGroupName(), updRequest.getUrlText(), 
				updRequest.getUrl(), String.valueOf(updRequest.isUrl()),
				String.valueOf(updRequest.isSplUser()) , updRequest.getId()};
		int i = jdbcTemplate.update(sql2, params);
		if(i == 1) {
			System.out.println( i +"number of rows updated");
			return true;
		}
		else {
			return false;
		}
	}



	public URLEntry getInfo(int id) {
		String sql3 = "select * from ONESTOP where ID=" +id;
		
		//  URLEntry uentry = jdbcTemplate.query(sql3, rowMapper)
		
		 URLEntry query = (URLEntry)jdbcTemplate.queryForObject(sql3, new RowMapper<URLEntry>() {
			 public URLEntry mapRow(ResultSet rs , int index ) throws SQLException {
	               
	               URLEntry uentry = new URLEntry();

	                int Id = rs.getInt("ID");
	                String Groupname = rs.getString("GROUPNAME");
	                String Text = rs.getString("TEXT");
	                String URL = rs.getString("URL");
	                boolean islink = Boolean.parseBoolean(rs.getString("ISLINK"));
	                boolean isSpl = Boolean.parseBoolean(rs.getString("ISSPL"));
	                
	                uentry.setId(Id);
	                uentry.setGroupName(Groupname);
	                uentry.setText(Text);
	                uentry.setUrl(URL);
	                uentry.setIslink(islink);
	                uentry.setSpl(isSpl);
	                
	                return uentry;
			 }
		} );
	                
       return query;
	}



	public int addEntry(AddRequest request) {
		System.out.println("u r in addition mode");
		// get the max id
		String getMaxId = "Select max(ID) from ONESTOP";
		int maxId = jdbcTemplate.queryForObject(getMaxId, Integer.class);
		
		String sql = "Insert into ONESTOP(ID, GROUPNAME, TEXT, URL, ISLINK, ISSPL) values("+(maxId + 1)+ ",? , ?, ? , ?, ?)";
		Object[] params = {
				request.getGroupName(), request.getUrlText(), 
				request.getUrl(), String.valueOf(request.isUrl()), 
				String.valueOf(request.isSplUser())
				};
		int i = jdbcTemplate.update(sql, params);
		if(i == 1) {
			System.out.println( i +" number of rows added");
		}
		else {
			System.out.println(" no rows added");
		}
		return i;
	}



	public List<String> getAvailableGroups() {
		String getSql = "Select distinct(GROUPNAME) from ONESTOP";
		List<String> query = jdbcTemplate.query(getSql, new RowMapper<String>() {

			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				String string = rs.getString("GROUPNAME");
				
				if(null != string) {
					return string.trim();
					
				}
				return "null";
			}
			
		});
		
		return query;
	}


	public void removeEntry(int id) {
		String sql = "delete from ONESTOP where id=" + id;
		jdbcTemplate.update(sql);
	}
}
	
	
