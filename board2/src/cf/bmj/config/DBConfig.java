package cf.bmj.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {

	//�����ͺ��̽� ����
	private static final String HOST="jdbc:mysql://192.168.0.126:3306/bmj"; //���� �̸��� �빮�� -> ���[������ ���̴�.] 
	private static final String USER="bmj";
	private static final String PASS="1234";
		
	public static Connection getConnection() throws Exception{
		
		// 1�ܰ�
		Class.forName("com.mysql.jdbc.Driver");
		// 2�ܰ�
		Connection conn = DriverManager.getConnection(HOST, USER, PASS);
		return conn;
		
	}
	
}
