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
  <div id="msg" class="success">
    <h2><spring:message code="screen.logout.header" /></h2>
    <p><spring:message code="screen.logout.success" /></p>
    <p><spring:message code="screen.logout.security" /></p>
  </div>
  
<script type="text/javascript" src="unicorn/js/jquery.min.js"></script> 
<script type="text/javascript" src="unicorn/js/jquery.cookie.js"></script>
<script>
		
		var recenturl = $.cookie('recenturl');
		console.log("recenturl: "+recenturl);
		
		if(typeof(recenturl) == 'undefined' || recenturl == null){
			
			setTimeout(function(){ window.location = '/am' }, 1000);
			
		}else{
			
			setTimeout(function(){ window.location = '/'+recenturl }, 1000);
			
		}

</script>
<jsp:directive.include file="includes/bottom.jsp" />