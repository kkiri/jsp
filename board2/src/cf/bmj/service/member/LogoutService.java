package cf.bmj.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cf.bmj.controller.CommonAction;

public class LogoutService implements CommonAction{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		HttpSession session = req.getSession();
		session.invalidate();  // invalidate = ¹«È¿È­
		
		return "redirect:/board2/member/login.do";
	}
	
}
