package cf.bmj.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cf.bmj.controller.CommonAction;

public class WriteService implements CommonAction{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return "/write.jsp";
	}
		
}
