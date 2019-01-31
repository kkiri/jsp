package cf.bmj.service.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cf.bmj.controller.CommonAction;
import cf.bmj.config.DBConfig;
import cf.bmj.config.SQL;
import cf.bmj.vo.MemberVO;

public class LoginService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		if(req.getMethod().equals("POST")) {
		//POST��û�� ���
				
		String redirectUrl = null;
		
		String uid = req.getParameter("uid");
		String pass= req.getParameter("pass");
		
		
		
		Connection conn = DBConfig.getConnection();
		
		// 3�ܰ�
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_LOGIN);
		psmt.setString(1, uid);
		psmt.setString(2, pass);
		
		// 4�ܰ�
		ResultSet rs = psmt.executeQuery();
		
		// 5�ܰ�
		if(rs.next()){
			// ���̵�� ��й�ȣ�� ��ġ�ϴ� ȸ���� ���̺� ���� ���
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
			
			redirectUrl = "/board2/list.do";
		}else{
			// ���̵�� ��й�ȣ�� ��ġ�ϴ� ȸ���� ���̺� ���� ���
			redirectUrl = "/board2/member/login.do?result=fail";
		}
		
		// 6�ܰ�
		rs.close();
		psmt.close();
		conn.close();
		
		
		return "redirect:"+redirectUrl;
	}else{
		// �α����� �Ǵ��� üũ
		if(session.getAttribute("member") != null) {
		return "redirect:/board2/list.do";
	}else{
		return "/login.jsp";
	}
	}
		
	}
}