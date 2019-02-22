package bmj.farmstory.service.board;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bmj.farmstory.config.DBConfig;
import bmj.farmstory.config.SQL;
import bmj.farmstory.controller.CommonAction;
import bmj.farmstory.vo.BoardVO;



public class ListService implements CommonAction{
	String cate = null;

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String gr = req.getParameter("gr");
		cate = req.getParameter("cate");
		String pg = req.getParameter("pg");
		
		// Limit용 start 계산
		int start = getLimitStart(pg);
		
		// 페이지번호 계산
		int total = getTotal();
		int pageEnd = getPageEnd(total);
		
		// 글 카운터번호 계산
		int count = getPageCountStart(total, start);
		
		// 페이지 그룹 계산
		int[] groupStartEnd = getPageGroupStartEnd(pg, pageEnd);
		
		Connection conn = DBConfig.getConnection();
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_LIST);
		psmt.setString(1, cate);
		psmt.setInt(2, start);
		
		ResultSet rs = psmt.executeQuery();
		
		ArrayList<BoardVO> list = new ArrayList<>();
		while(rs.next()){
			BoardVO vo = new BoardVO();
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
			vo.setNick(rs.getString(12));
			list.add(vo);		
		}
		rs.close();
		psmt.close();
		conn.close();
		
		req.setAttribute("list", list);
		req.setAttribute("count", count);
		req.setAttribute("pageEnd", pageEnd);
		req.setAttribute("groupStartEnd", groupStartEnd);
		req.setAttribute("gr", gr);
		req.setAttribute("cate", cate);
		
		return "/board/list.jsp";
	}
	public int getLimitStart(String pg) {
		int start = 0;
		
		if(pg == null){
			start = 1;
		}else{
			start = Integer.parseInt(pg);
		}
			
		return (start - 1) * 10;
	}
	
	
	
	public int getPageEnd(int total) {
		int pageEnd = 0;
		
		if(total % 10 == 0){
			pageEnd = total / 10;
		}else{
			pageEnd = (total / 10) + 1; 
		}
		
		return pageEnd;
	}
	public int getPageCountStart(int total, int limit) {
		return total+1 - limit;
	}
	
	public int[] getPageGroupStartEnd(String pg, int pageEnd) {
		
		int[] groupStartEnd = new int[2];
		int current = 0;
		if(pg == null) {
			current = 1;
		}else {
			current = Integer.parseInt(pg);
		}
		int currentPage = current;
		int currentPageGroup = (int)Math.ceil(currentPage/10.0);
		int groupStart = (currentPageGroup - 1) * 10 + 1;
		int groupEnd = currentPageGroup * 10;
		if(groupEnd > pageEnd){
			groupEnd = pageEnd;
		}
		
		groupStartEnd[0] = groupStart;
		groupStartEnd[1] = groupEnd;
		
		return groupStartEnd;
	}


	public int getTotal() throws Exception {
		
		int total = 0;
		Connection conn = DBConfig.getConnection();
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_COUNT);
		psmt.setString(1, cate);

		
		ResultSet rs = psmt.executeQuery();
		
		
		if(rs.next()) {
			total = rs.getInt(1);
		}
		rs.close();
		psmt.close();
		conn.close();
		
		return total;
	}

}














