package bmj.farmstory.service.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bmj.farmstory.controller.CommonAction;
import bmj.farmstory.config.DBConfig;
import bmj.farmstory.config.SQL;
import bmj.farmstory.vo.MemberVO;

public class LoginService implements CommonAction{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		
		HttpSession session = req.getSession();
		if(req.getMethod().equals("POST")) {
		//POST요청일 경우
				
		String redirectUrl = null;
		
		String uid = req.getParameter("uid");
		String pass= req.getParameter("pass");
		
		
		
		Connection conn = DBConfig.getConnection();
		
		// 3단계
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_LOGIN);
		psmt.setString(1, uid);
		psmt.setString(2, pass);
		
		// 4단계
		ResultSet rs = psmt.executeQuery();
		
		// 5단계
		if(rs.next()){
			// 아이디와 비밀번호가 일치하는 회원이 테이블에 있을 경우
			MemberVO vo = new MemberVO();
			vo.setSeq(rs.getInt(1));
			vo.setId(rs.getString(2));
			vo.setPw(rs.getString(3));
			vo.setName(rs.getString(4));
			vo.setNick(rs.getString(5));
			vo.setEmail(rs.getString(6));
			vo.setHp(rs.getString(7));
			vo.setGrade(rs.getInt(8));
			vo.setZip(rs.getString(9));
			vo.setAddr1(rs.getString(10));
			vo.setAddr2(rs.getString(11));
			vo.setRegip(rs.getString(12));
			vo.setRdate(rs.getString(13));
			
			
			session.setAttribute("member", vo);
			
			redirectUrl = "/farmstory/index.do";
		}else{
			// 아이디와 비밀번호가 일치하는 회원이 테이블에 없을 경우
			redirectUrl = "/farmstory/member/login.do?result=fail";
		}
		
		// 6단계
		rs.close();
		psmt.close();
		conn.close();
		
		
		return "redirect:"+redirectUrl;
	}else{
		// 로그인이 되는지 체크
		if(session.getAttribute("member") != null) {
		return "redirect:/farmstory/index.do";
	}else{
		
		
		return "/member/login.jsp";
	}

}
	}}
