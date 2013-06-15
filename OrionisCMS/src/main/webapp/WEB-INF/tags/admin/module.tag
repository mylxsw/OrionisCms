<%@tag pageEncoding="UTF-8" dynamic-attributes="dyna_attr" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="true" description="Title" %>
<div class="body">
	<div class="row-fluid ">
		<div class="span12">
			<div class="widget">
				<div class="widget-title">
					<div class="icon">
						<i class="icon-th-list"></i>
					</div>
					<h4>${title }</h4>
					<a href="#" class="minimize"><i class="icon-chevron-up"></i></a>
				</div>
				<div class="widget-content">
					<jsp:doBody></jsp:doBody>
				</div>
			</div>
		</div>
	</div>
</div>