package bmj.farmstory.service.board;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmj.farmstory.controller.CommonAction;



public class ListService implements CommonAction{


	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
	String gr = req.getParameter("gr");	
	String cate = req.getParameter("cate");
	
	req.setAttribute("gr", gr);
	req.setAttribute("cate", cate);

	return "/board/list.jsp";
}

}








