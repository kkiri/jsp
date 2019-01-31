package cf.bmj.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {

	//데이터베이스 정보
	private static final String HOST="jdbc:mysql://192.168.0.126:3306/bmj"; //변수 이름이 대문자 -> 상수[고정된 값이다.] 
	private static final String USER="bmj";
	private static final String PASS="1234";
		
	public static Connection getConnection() throws Exception{
		
		// 1단계
		Class.forName("com.mysql.jdbc.Driver");
		// 2단계
		Connection conn = DriverManager.getConnection(HOST, USER, PASS);
		return conn;
		
	}
	
}
