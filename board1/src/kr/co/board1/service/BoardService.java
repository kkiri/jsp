package kr.co.board1.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.board1.config.DBConfig;
import kr.co.board1.config.SQL;
import kr.co.board1.vo.BoardVO;
import kr.co.board1.vo.MemberVO;

public class BoardService {

	private static BoardService service = new BoardService();
	
	public static BoardService getInstance() {
		return service;
	}
	
	private BoardService() {}
	
	public MemberVO getMember(HttpSession session) {
		MemberVO vo = (MemberVO) session.getAttribute("member");
		return vo;		
	}
	
	public void insertBoard() throws Exception {}
	public void list() throws Exception {}
	
	public void updateHit(int seq) throws Exception {
		
		Connection conn = DBConfig.getConnection();
		
		PreparedStatement psmt = conn.prepareStatement(SQL.UPDATE_HIT);
		psmt.setInt(1, seq);
		
		psmt.executeUpdate();
		psmt.close();
	}
	
	public BoardVO view(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String seq = request.getParameter("seq");
		
		Connection conn = DBConfig.getConnection();
		
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_VIEW);
		psmt.setString(1, seq);
		
		ResultSet rs = psmt.executeQuery();
		
		BoardVO vo = new BoardVO();
		
		if(rs.next()){
			vo.setSeq(rs.getInt(1));
			vo.setParent(rs.getInt(2));
			vo.setComment(rs.getInt(3));
			vo.setCate(rs.getString(4));
			vo.setTitle(rs.getString(5));
			vo.setContent(rs.getString(6));
			vo.setFile(rs.getInt(7));
			vo.setHit(rs.getInt(8));
			vo.setUid(rs.getString(9));
			vo.setRegip(rs.getString(10));
			vo.setRdate(rs.getString(11));
		}
		
		rs.close();
		psmt.close();		
		conn.close();
		
		return vo;
	}
	
	public String modify(HttpServletRequest request) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String title 	= request.getParameter("subject");
		String content 	= request.getParameter("content");
		String seq 		= request.getParameter("seq");
		
		Connection conn = DBConfig.getConnection();
		
		PreparedStatement psmt = conn.prepareStatement(SQL.UPDATE_BOARD);
		psmt.setString(1, title);
		psmt.setString(2, content);
		psmt.setString(3, seq);
		
		psmt.executeUpdate();
		psmt.close();
		conn.close();
		
		return seq;
	}
	
	public String delete(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String seq 	  = request.getParameter("seq");
		String parent = request.getParameter("parent");
		
		Connection conn = DBConfig.getConnection();
		
		PreparedStatement psmt = conn.prepareStatement(SQL.DELETE_BOARD);
		psmt.setString(1, seq);
		
		psmt.executeUpdate();
		psmt.close();
		
		return parent;
	}
	
	public String insertComment(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String parent 	= request.getParameter("parent");
		String content 	= request.getParameter("comment");
		String uid 		= request.getParameter("uid");
		String regip 	= request.getRemoteAddr();

		Connection conn = DBConfig.getConnection();

		// 3
		CallableStatement call = conn.prepareCall(SQL.INSERT_COMMENT);
		call.setString(1, parent);
		call.setString(2, content);
		call.setString(3, uid);
		call.setString(4, regip);
		
		// 4
		call.execute();
		
		// 5	
		// 6
		call.close();
		conn.close();
		
		return parent;
	}
	
	public ArrayList<BoardVO> listComment(String parent) throws Exception {
		
		Connection conn = DBConfig.getConnection();
		// 3
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_COMMENT);
		psmt.setString(1, parent);
		
		// 4
		ResultSet rs = psmt.executeQuery();
		
		// 5
		ArrayList<BoardVO> list = new ArrayList<>();
		
		while(rs.next()){
			BoardVO vo = new BoardVO();
			vo.setSeq(rs.getInt("seq"));
			vo.setParent(rs.getInt(2));
			vo.setComment(rs.getInt(3));
			vo.setContent(rs.getString(6));
			vo.setFile(rs.getInt(7));
			vo.setHit(rs.getInt(8));
			vo.setUid(rs.getString(9));
			vo.setRegip(rs.getString(10));
			vo.setRdate(rs.getString("rdate"));
			vo.setNick(rs.getString("nick"));
			
			list.add(vo);		
		}
		
		// 6
		rs.close();
		psmt.close();
		conn.close();
		
		return list;
	}
	
	public void updateCommentCount() throws Exception {}
}




