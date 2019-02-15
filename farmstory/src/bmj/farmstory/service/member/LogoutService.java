package bmj.farmstory.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bmj.farmstory.controller.CommonAction;

public class LogoutService implements CommonAction{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		HttpSession session = req.getSession();
		session.invalidate();  // invalidate = ��ȿȭ
		
		return "redirect:/farmstory/member/index.do";
	}
	
}
