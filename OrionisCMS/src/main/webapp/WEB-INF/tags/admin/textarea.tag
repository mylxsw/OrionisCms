<%@tag pageEncoding="UTF-8" dynamic-attributes="dyna_attr" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="label" required="true"  %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="id"  %>
<%@ attribute name="value" required="false" %>
<%@ attribute name="rich" type="java.lang.Boolean" %>
<%@ attribute name="clazz" required="false" description="CSS" %>
<div class="control-group" id="group-${id }">
   <label class="control-label" for="${id }">${label }</label>
   <div class="controls">
	 <textarea style="width:484px; height:94px"  id="${id }" name="${name }" placeholder="${label }"  <c:forEach items="${dyna_attr }" var="attr"> ${attr.key }="${attr.value }" </c:forEach> >${value }</textarea>
   </div>
</div>
<c:if test="${rich}">
<script type="text/javascript">
window.editor = new UE.ui.Editor({
	// toolbars:[
//           ['fullscreen', 'source', '|', 'undo', 'redo', '|',
//               'link', 'unlink','|',
//               'insertimage', 'emotion','scrawl', 'insertvideo','music','attachment', 'map', 'gmap', 'highlightcode','webapp', '|',
//               'spechars','snapscreen', 'wordimage', 
//               ]
//       ],
    initialFrameWidth:770,  //初始化编辑器宽度,默认1000
    initialFrameHeight:320, //初始化编辑器高度,默认320
    minFrameHeight:120,
    wordCount:true,          //是否开启字数统计
    maximumWords:10000,       //允许的最大字符数
});
editor.render("${id }");
</script>
</c:if>
