<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License.  You may obtain a
    copy of the License at the following location:

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

--%>
<jsp:directive.include file="includes/top.jsp" />
	<div id="msg" class="errors">
		<h2 style="font-size:16px; font-weight:bold; margin-top:60px; padding-bottom:20px;"><spring:message code="screen.expiredpass.heading" /></h2>
		<p id="errorMessage"><input id="resetBtn" type="submit" class="btn btn-block btn-primary btn-default" style="width:70%; margin-left:auto; margin-right:auto;" value="<spring:message code="screen.expiredpass.message" />"/>
		</p>
	</div>
	
	<div id="msg2" class="errors" style="display:none;">
		<h2 style="font-size:16px; font-weight:bold; margin-top:45px; padding-bottom:20px;"><spring:message code="screen.reset.heading" /></h2>
		<p id="success" style="display:none;"><spring:message code="screen.reset.success.message" /></p>
		<p id="failed" style="display:none;"><spring:message code="screen.reset.failed.message" /></p>
	</div>
	
	<script type="text/javascript">
		$( document ).ready(function() {
			
			var passwordPolicyUrl = "<c:out value='${passwordPolicyUrl}'/>";
			
			$("#resetBtn").click(function(){
				$("#resetBtn").prop( "disabled", true );
				$.ajax({
					type : 'GET',
					url : passwordPolicyUrl,
					dataType : 'json',
					success : function(data) {
						$("#resetBtn").prop( "disabled", false );
						if(data){
							$("#msg").hide();
							$("#msg2").show();
							$("#success").show();
							$("#failed").hide();
							// $("#errorMessage").html("A Password Reset Email has sent to your registered email address. Please follow the instructions to reset your password.");
						}else{
							$("#msg").hide();
							$("#msg2").show();
							$("#success").hide();
							$("#failed").show();
							// $("#errorMessage").html("A Password Reset Email sending failed. Please contact the system administrator or email to support@twilas.com.");
						}
					}
				});
			});
		});
	</script>
<jsp:directive.include file="includes/bottom.jsp" />
