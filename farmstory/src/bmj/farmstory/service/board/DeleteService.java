package bmj.farmstory.service.board;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmj.farmstory.config.DBConfig;
import bmj.farmstory.config.SQL;
import bmj.farmstory.controller.CommonAction;

public class DeleteService implements CommonAction{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String gr = req.getParameter("gr");
		String cate = req.getParameter("cate");
		
		req.setCharacterEncoding("UTF-8");
		String seq 	  = req.getParameter("seq");
		
		Connection conn = DBConfig.getConnection();
		
		PreparedStatement psmt = conn.prepareStatement(SQL.DELETE_BOARD);
		psmt.setString(1, seq);
		
		psmt.executeUpdate();
		psmt.close();
		
		return "redirect:/farmstory/board/list.do?gr="+gr+"&cate="+cate;
	}

}
