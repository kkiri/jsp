<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="./_aside_${gr}.jsp" />

<div id="board">
	<h3>글보기</h3>
	<div class="view">
		<form action="#" method="post">
			<table>
				<tr>
					<td>제목</td>
					<td><input type="text" name="subject" value="${vo.title}" readonly /></td>
				</tr>

				<tr>
					<td>첨부파일</td>
					<td><a href="#">테스트.hwp</a> <span>3회 다운로드</span></td>
				</tr>

				<tr>
					<td>내용</td>
					<td><textarea name="content" rows="20" readonly>${vo.content}</textarea>
					</td>
				</tr>
			</table>
			<div class="btns">
				<a href="/farmstory/board/delete.do?gr=${gr}&cate=${cate}&seq=${vo.seq}"  class="cancel del">삭제</a> 
				<a href="/farmstory/board/modify.do?gr=${gr}&cate=${cate}&seq=${vo.seq}" class="cancel mod">수정</a> 
				<a href="/farmstory/board/list.do?gr=${gr}&cate=${cate}" class="cancel">목록</a>
			</div>
		</form>
	</div>
	<!-- view 끝 -->

	<!-- 댓글리스트 -->
	<section class="comments">
		<h3>댓글목록</h3>

		<div class="comment">
			<span> <span>홍길동</span> <span>18-03-01</span>
			</span>
			<textarea>테스트 댓글입니다.</textarea>
			<div>
				<a href="#" class="del">삭제</a> <a href="#" class="mod">수정</a>
			</div>
		</div>

		<p class="empty">등록된 댓글이 없습니다.</p>

	</section>

	<!-- 댓글쓰기 -->
	<section class="comment_write">
		<h3>댓글쓰기</h3>
		<div>
			<form action="#" method="post">
				<textarea name="comment" rows="5"></textarea>
				<div class="btns">
					<a href="#" class="cancel">취소</a> 
					<input type="submit" class="submit" value="작성완료" />
				</div>
			</form>
		</div>
	</section>
</div>
<!-- board 끝 -->
<!-- 내용 끝 -->
</article>
</div>
</section>
<%@ include file="../_footer.jsp"%>

