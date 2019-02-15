package bmj.farmstory.service.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import bmj.farmstory.config.DBConfig;
import bmj.farmstory.config.SQL;
import bmj.farmstory.controller.CommonAction;

public class UserCheckService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String uid = req.getParameter("uid"); //json데이터
		
		Connection conn = DBConfig.getConnection();
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_UID_COUNT);
		psmt.setString(1,  uid);
		
		ResultSet rs = psmt.executeQuery();
		
		int count = 0;
		if(rs.next()) {
			count = rs.getInt(1);
		}
		rs.close();
		conn.close();
		psmt.close();
		
		// JSON format으로 변환
		JSONObject json = new JSONObject();
		json.put("result",+count);
		
		String result = json.toString();
		//String result2 = json.toJSONString();
		
		//System.out.println("result1 : "+result1);
		//System.out.println("result2 : "+result2);
		return result;
	}

}
