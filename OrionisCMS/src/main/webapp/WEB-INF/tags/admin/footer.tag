<%@tag pageEncoding="UTF-8"  trimDirectiveWhitespaces="true" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
</div><!--/row-->

  <hr>
</div><!--/.fluid-container-->
<footer class="footer">
	<div class="copyright">&copy; Company 2013</div>
</footer>
</div>
<!-- 弹窗  -->
<div id="dialog" class="modal warning bg-color-blu hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-body">
    </div>
    <div class="modal-footer">
       <button class="btn" data-dismiss="modal">关闭</button>
    </div>
 </div>
<script src="<%=basePath %>resources/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="<%=basePath %>resources/jquery/jquery.form.js" type="text/javascript"></script>
<script src="<%=basePath %>resources/bootstrap/js/bootstrap.min.js" type="text/javascript" ></script>
<script type='text/javascript' src='<%=basePath %>dwr/engine.js'></script>
<script type='text/javascript' src='<%=basePath %>dwr/interface/AdminNav.js'></script>
<script type='text/javascript' src='<%=basePath %>dwr/util.js'></script>
<script src="<%=basePath %>resources/admin/js/core.js" type="text/javascript" ></script>
<jsp:doBody></jsp:doBody>
</body>
</html>
