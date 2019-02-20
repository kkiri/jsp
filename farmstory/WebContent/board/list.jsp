<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../_header.jsp" %>
<jsp:include page="./_aside_${gr}.jsp" />
	<div id="board">
		<h3>글목록</h3>
		<!-- 리스트 -->
		<div class="list">
			
			<table>
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>글쓴이</td>
					<td>날짜</td>
					<td>조회</td>
				</tr>
			
				
				<tr>
					<td>1</td>
					<td><a href="/farmstory/board/view.do?gr=${gr}&cate=${cate}">제목입니다.</a>&nbsp;[2]</td>
					<td>홍길동</td>
					<td>19-02-19</td>
					<td>12</td>
				</tr>
				
			</table>
		</div>
		<!-- 페이징 -->
	<nav class="paging">
		<span> 
			<a href="#" class="prev">이전</a> 
			<a href="#"	class="num">1</a>
			<a href="#" class="num">2</a> 
			<a href="#" class="num">3</a>
			<a href="#" class="next">다음</a>
		</span>
	</nav>
		<a href="/farmstory/board/write.do?gr=${gr}&cate=${cate}" class="btnWrite">글쓰기</a>
	</div>
	</article>
  </div>
</section>
<%@ include file="../_footer.jsp" %>









