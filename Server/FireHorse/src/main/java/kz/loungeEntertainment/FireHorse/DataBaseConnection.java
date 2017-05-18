package kz.loungeEntertainment.FireHorse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class DataBaseConnection {
	
private JdbcTemplate jt; 
	
	public DataBaseConnection(){
		DriverManagerDataSource  dbcp = new DriverManagerDataSource (); //HARD WRITING I KNOW, i will fix its later	
		
		dbcp.setDriverClassName("com.mysql.jdbc.Driver");
		//dbcp.setUrl("jdbc:mysql://mysql30493-salamchik.j.dnr.kz/firehorse");
		dbcp.setUrl("jdbc:mysql://localhost:3306/firehorse");
		dbcp.setUsername("root");
		//dbcp.setPassword("VCdN38o0lr");
				
		//dbcp.setUrl("jdbc:mysql://localhost:3306/firehorse?useUnicode=true&characterEncoding=utf8");
		dbcp.setPassword("bagdaulet5618A");
					
		jt = new JdbcTemplate(dbcp);
	}
	
	
	public void insert (String setter, String getter, String text){
		
		ArrayList<String> params = new ArrayList<String>();
		params.add(setter);
		
		String sql = "SELECT users.id FROM firehorse.users where users.name = '<0>';";
		int seeterId = jt.queryForObject(DinamiTableCreater.replace(sql, params), Integer.class);
		
		params.add(0, getter);
		int getterId = jt.queryForObject(DinamiTableCreater.replace(sql, params), Integer.class);
		
		params = new ArrayList<String>();
		
		params.add(text);
		params.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		params.add(String.valueOf(getterId));
		params.add(String.valueOf(seeterId));
		
		
		
		sql = " INSERT INTO firehorse.messagemap (text, data, ToUser, FromUser) "
				+ " VALUES('<0>', '<1>', <2>, <3>);";	

		jt.update(DinamiTableCreater.replace(sql, params));
		
		
		params = new ArrayList<String>();
		params.add(getter);
		sql = "UPDATE firehorse.users SET hasNewMessage = 1 WHERE identifier = '<0>'; ";
		jt.update(DinamiTableCreater.replace(sql, params));
		
	}


	public String getAll(String setter, String getter) {
		 
		
		//first we ged id of users
		ArrayList<String> params = new ArrayList<String>();
		params.add(setter);

		String sql = "SELECT users.id FROM firehorse.users where users.name = '<0>';";

		int seeterId = jt.queryForObject(DinamiTableCreater.replace(sql, params), Integer.class);
		
		params.add(0, getter);
		
		int getterId = jt.queryForObject(DinamiTableCreater.replace(sql, params), Integer.class);
		/*
		 * SELECT messagemap.text,
	(SELECT users.name FROM firehorse.users where users.id  = FromUser.id) setter, 
    (SELECT users.name FROM firehorse.users where users.id  = ToUser.id) getter 
FROM	firehorse.messagemap
  
where	
(messagemap.FromUser = 0
and messagemap.ToUser = 1)
or
(messagemap.FromUser = 1
and messagemap.ToUser = 0)
order by messagemap.data;
		 * 
		 * 
		 * 
		 * */
		
		String row = new String(); 
		params.removeAll(params);
		
		params.add(String.valueOf(seeterId));
		params.add(String.valueOf(getterId));
		
		sql = " SELECT messagemap.text," +
				" (SELECT users.name FROM firehorse.users where users.id  = messagemap.FromUser) setter " +
		//		" (SELECT users.name FROM firehorse.users where users.id  = messagemap.ToUser) getter  " +
				" FROM	firehorse.messagemap " +
				" where	" +
				" (messagemap.FromUser = <0> " +
				" and messagemap.ToUser = <1>) " +
				" or " +
				" (messagemap.FromUser = <1> " +
				" and messagemap.ToUser = <0>) " +
				" order by messagemap.data; ";
		
		List<Map<String, Object>> rows = jt.queryForList(DinamiTableCreater.replace(sql, params));

		String holder = "";
		for (Map<String, Object> map : rows) {
			
			holder = map.get("setter").toString();
			
			if(setter.equals(holder))
				row += holder + " : " + map.get("text") + "\n";
			else
				row += "\t" + holder + " : " + map.get("text") + "\n";
			
		}
			
		return row;
	
	}

	public List<ArrayList<String>> getAllUsers() {
		 
		List<ArrayList<String>> table = new ArrayList<ArrayList<String>>(); 
		ArrayList<String> row;
		
		String sql = " SELECT id, identifier, name, password FROM users ";
		
		List<Map<String, Object>> rows = jt.queryForList(sql);

		for (Map<String, Object> map : rows) {
			
			row  = new ArrayList<String>();	
			row.add(map.get("name").toString());
			row.add(map.get("identifier").toString());
			row.add(map.get("id").toString());
			table.add(row);
		}
			
		//System.out.println(row);
		return table;
	
	}


	public String getPassword(String login) {

		String sql = "SELECT users.password FROM firehorse.users where users.identifier = '<0>'; ";

		ArrayList<String> params = new ArrayList<String>();
		params.add(login);
		 
		return jt.queryForObject(DinamiTableCreater.replace(sql, params), String.class);
	}	
	
	
	public String doIHaveNewMessage(String setter) {

		String sql = "SELECT users.hasNewMessage FROM firehorse.users where users.identifier = '<0>';";

		ArrayList<String> params = new ArrayList<String>();
		params.add(setter);
		
		byte result = jt.queryForObject(DinamiTableCreater.replace(sql, params), Byte.class);
		
		if(result == 0) 
			return "false";
		else{
			sql = "UPDATE firehorse.users SET hasNewMessage = 0 WHERE identifier = '<0>'; ";
			jt.update(DinamiTableCreater.replace(sql, params));	
			return "true";
		}
		
	}


	public void insertPssword(String name, String login, String password) {
		
		ArrayList<String> params = new ArrayList<String>();
		params.add(name);
		params.add(login);
		params.add(password);
		
		String sql = " INSERT INTO `firehorse`.`users` "
				+ " (`identifier`,`name`,`password`) "
				+ " VALUES "
				+ " ('<1>','<0>','<2>'); ";	

		jt.update(DinamiTableCreater.replace(sql, params));
		
	}	
	
	
	
	
	
	
}
