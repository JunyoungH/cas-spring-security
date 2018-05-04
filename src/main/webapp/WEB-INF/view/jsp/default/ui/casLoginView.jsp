<jsp:directive.include file="includes/top.jsp" />
	
<sf:form method="post" id="loginform" commandName="${commandName}" htmlEscape="true">
	<c:set var="errors"><sf:errors path="*" /></c:set>
	<p>
		<c:choose>
			<c:when test="${not empty errors}"> <!-- ${errors} -->
	          <sf:errors path="*" id="msg" cssClass="errors" element="div" htmlEscape="false" />
	        </c:when>
	        <c:otherwise>
                <spring:message code="screen.welcome.welcome"/>
	        </c:otherwise>
		</c:choose>
	</p>
    <div class="input-group input-sm">
        <span class="input-group-addon"><i class="fa fa-user"></i></span>
        <c:choose>
        <c:when test="${not empty sessionScope.openIdLocalId}">
          <strong>${sessionScope.openIdLocalId}</strong>
          <input type="hidden" id="username" name="username" value="${sessionScope.openIdLocalId}" />
        </c:when>
        <c:otherwise>
          <sf:input cssClass="form-control" cssErrorClass="error" id="username" size="25" tabindex="1" accesskey="${userNameAccessKey}" path="username" autocomplete="off" htmlEscape="true" placeholder="Username" />
        </c:otherwise>
      </c:choose>
    </div>
    <div class="input-group">
        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
	      <%--
	      NOTE: Certain browsers will offer the option of caching passwords for a user.  There is a non-standard attribute,
	      "autocomplete" that when set to "off" will tell certain browsers not to prompt to cache credentials.  For more
	      information, see the following web page:
	      http://www.technofundo.com/tech/web/ie_autocomplete.html
	      --%>
	      <sf:password cssClass="form-control" cssErrorClass="error" id="password" size="25" tabindex="2" path="password"  accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off" placeholder="Password" />
    </div>
    <div class="form-actions clearfix">
		<div class="pull-right">
			
			<a href="../am/account/forgotpassword.wilas" class="flip-link to-recover grey"><spring:message code="screen.welcome.link.forgotpassword"></spring:message></a>
			
        </div>
    	<input type="hidden" name="lt" value="${loginTicket}" />
		<input type="hidden" name="execution" value="${flowExecutionKey}" />
		<input type="hidden" name="_eventId" value="submit" />
        <input type="submit" class="btn btn-block btn-primary btn-default" value="Login" />
    </div>
</sf:form>
<!--
<form id="recoverform" action="#">
	<p><spring:message code="screen.recoverform.instruction"></spring:message></p>
	<div class="input-group">
		<span class="input-group-addon"><i class="fa fa-envelope"></i></span><input class="form-control" type="text" placeholder='<spring:message code="screen.recoverform.placeholder.email"/>' />
	</div>
	<div class="form-actions clearfix">
		<div class="pull-left">
			<a href="#loginform" class="grey flip-link to-login"><spring:message code="screen.recoverform.label.clicktologin"></spring:message></a>
		</div>
		<input type="submit" class="btn btn-block btn-inverse" value="Recover" />
	</div>
</form>
-->

<jsp:directive.include file="includes/bottom.jsp" />
