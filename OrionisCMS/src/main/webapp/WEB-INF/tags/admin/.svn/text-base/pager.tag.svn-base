<%@tag pageEncoding="UTF-8" dynamic-attributes="dyna_attr" trimDirectiveWhitespaces="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@attribute name="total" required="true" description="总记录数目" type="java.lang.Integer" %>
<%@attribute name="cur" required="false" description="当前页" type="java.lang.Integer"  %>
<%@attribute name="perNumber" required="true" description="每页显示的记录数目" type="java.lang.Integer" %>
<%@attribute name="baseUrl" required="true" description="链接" type="java.lang.String" %>
<%@attribute name="ref" required="false" description="引用查询条件" type="java.lang.String" %>
<div class="pagination" ref="${ref }" cur="${cur }" per="${perNumber }">
	<ul>
	<%
		if(cur == null){
			cur = 1;
		}
		int pageCount = 7;
		//总页数
		int pages = total / perNumber + ((total % perNumber) > 0 ? 1 : 0);
		int begin = 0;//开始页码
		int end = 0;//结束页码
		
		//显示数字数量的一半
		int show_half = (pageCount % 2 == 0) ? (pageCount / 2) : ((pageCount + 1) / 2);
		
		if(cur <=  show_half){
			begin = 1;
			end = pages >= pageCount ? pageCount : pages;
		}else if(cur > show_half){
			begin = cur - show_half;
			end = (cur + show_half) >= pages ? pages : (cur + show_half);
		}
	%>
	<c:forEach begin="<%=begin %>" end="<%=end %>" step="1" var="i" varStatus="status">
		<c:choose>
			<c:when test="${status.first }">
				<li><a p="${cur - 1}" href="${baseUrl }${cur - 1 }" class="pageprv ${cur == i? "z-dis" :"" }">上一页</a></li>
				<li><a p="<%=begin %>" href="${baseUrl }<%=begin %>" ${cur == i ? "class='z-crt'":"" }>${i }</a></li>
			</c:when>
			<c:when test="${status.last }">
				<li><a p="<%=end %>" href="${baseUrl }<%=end %>" ${cur == i ? "class='z-crt'":"" } >${i }</a></li>
				<li><a p="${cur + 1 }" href="${baseUrl }${cur + 1}" class="pagenxt ${cur == i ? "z-dis":"" }">下一页</a></li>
			</c:when>
			<c:otherwise>
				<li><a p="${i }" href="${baseUrl }${i }" ${cur == i ? "class='z-crt'":"" }>${i }</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	</ul>
</div>