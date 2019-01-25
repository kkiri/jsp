<%@page import="kr.co.board1.service.BoardService"%>
<%@page import="kr.co.board1.config.SQL"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.board1.config.DBConfig"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="sun.java2d.pipe.BufferedOpCodes"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	
	request.setCharacterEncoding("UTF-8");
	String seq = request.getParameter("seq");
	String newName = request.getParameter("newName");
	String oldName = request.getParameter("oldName");
	
	
	// 파일다운로드 카운트 업데이트
	BoardService service = BoardService.getInstance();
	service.updateDownHit(seq);
	
	
	// 경로 구하기
	
	String path = request.getServletContext().getRealPath("/data");
	File file = new File(path+"/"+newName);
	String name = new String(oldName.getBytes("utf-8"),"iso-8859-1");
	
	// response 준비
	response.setContentType("application/octet-stream");
	response.setHeader("Content-Disposition", "attachment; filename="+name);
	response.setHeader("Content-Transfer-Encoding", "binary");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "private");
	
	//스트링 연결 : 파일 --- response객체	
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
	BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());

	byte b[] = new byte[1024];
	
	int read = 0;
	while(true){
		// Input 스트링으로 데이터 읽어오기
		read = bis.read(b);
		if(read == -1){
			break;
		}
		// Output 스티링으로 데이터쓰기
		bos.write(b, 0, read);
	}
	
	bos.flush();
	bos.close();
	bis.close();
	
%>