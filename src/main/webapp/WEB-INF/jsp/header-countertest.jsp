<%@page import="java.util.Set"%>
<%@page import="com.pravatpanda.onestop.bean.URLEntry"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<%
final int counter = 110;
if("glory".equals(request.getParameter("style"))){%>
	<LINK REL=StyleSheet HREF="styles/glory.css" TYPE="text/css" >
	<%
} else {%>
	<LINK REL=StyleSheet HREF="styles/basic.css" TYPE="text/css" >
	<%
}%>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="description" content="All Nielsen urls" />
	<meta name="keywords" content="Nielsen-one-stop, one stop, useful urls" />
	<script type="text/javascript" src="./js/jquery.min.js"></script>
	<style>
	.counter {
    display:block;
    float:left;
    font-size:2em;
    line-height:1.2em;
    -webkit-box-reflect: below -15px -webkit-gradient(linear, left top, left bottom, from(transparent), color-stop(60%, transparent), to(rgba(255,255,255,0.3)));
}

.counter span.digit {
    background:#161616;
    background: #3F3F3F; /* Old browsers */
    background: linear-gradient(bottom, #0A0A0A 0%, #2B2B2B 50%, #3F3F3F 100%);
    background: -o-linear-gradient(bottom, #0A0A0A 0%, #2B2B2B 50%, #3F3F3F 100%);
    background: -moz-linear-gradient(bottom, #0A0A0A 0%, #2B2B2B 50%, #3F3F3F 100%);
    background: -webkit-linear-gradient(bottom, #0A0A0A 0%, #2B2B2B 50%, #3F3F3F 100%);
    background: -ms-linear-gradient(bottom, #0A0A0A 0%, #2B2B2B 50%, #3F3F3F 100%);
    background: -webkit-gradient(linear, left bottom, left top, color-stop(0, #0A0A0A), color-stop(0.5, #2B2B2B), color-stop(1, #3F3F3F));
    filter:  progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#FF0A0A0A', endColorstr='#FF3F3F3F'); /* IE6 & IE7 */
    -ms-filter: "progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#FF0A0A0A', endColorstr='#FF3F3F3F')"; /* IE8 */
    zoom:1;    -webkit-border-radius:0.1em;
    -moz-border-radius:0.1em;
    border-radius:0.1em;
    background-clip:border;
    color:#FFF;
    display:block;
    float:left;
    height:44px;
    margin:0 1px;
    overflow:hidden;
    padding:0;
    position:relative;
    text-align:center;
    width:30px;
}

.counter span.digit span {
    line-height:44px;
    position:relative;
    top:0;
}

.counter span.digit hr {
    border-color: transparent;
    -webkit-box-shadow: inset 0 2px 1px  rgba(0,0,0,0.5);
    -moz-box-shadow: inset 0 2px 1px  rgba(0,0,0,0.5);
    box-shadow: inset 0 2px 1px   rgba(0,0,0,0.5);
    height: 3px;
    margin: -2px 0 0 0;
    position: absolute;
    top: 50%;
    width: 100%;
    z-index: 1;
}

.counter span.separator {
    display:block;
    float:left;
    font-family:Georgia, serif;
    font-size:0.5em;
    position:relative;
    top:0.5em;
}

.counter span.separator hr {
    display:none;
}
	</style>
	<script type="text/javascript">
	;(function($){
	    /*
	        Function: initCounter

	        Initializes the scrolling counter using the value currently displayed in the element.

	        Parameters:

	            $this - the counter container
	            e - jQuery Event object

	        See Also:

	            <animateDigit>
	    */
	    function initCounter($this, e){
	        $this.find('.digit').each(function(){
	            var $display = $(this);
	            var $digit = $display.find('span');

	            $digit.html([0,1,2,3,4,5,6,7,8,9,0].reverse().join('<br/>'))
	            $digit.css({ 
	                top: '-' + (parseInt($display.height()) * (10 - parseInt($digit.attr('title')))) + 'px'
	            });
	        });

	        animateDigit($this.find('.digit:last'), e);
	    }

	    /*
	        Function: animateDigit

	        Moves the digit indicated by $this one step. If the end of the counter has been reach, the subsequent digit(s) will also be rotated

	        Parameters:

	            $this - digit to be rotated
	            e - jQuery Event object
	    */
	    function animateDigit($this, e){
	        var $counter = $this.closest('.counter');
	        var $display = $this;
	        var $digit = $display.find('span');

	        // If we've reached the end of the counter, tick the previous digit
	        if(parseInt($digit.css('top')) == -1 * parseInt($display.height())){
	            animateDigit($display.prevAll('.digit:first'), e);
	        }

	        $digit.animate({
	            top: '+=' + $display.height() + 'px'
	        }, 500, function(){
	            // Repeat the animation on a semi-random interval
	            //if($display.index('.counter .digit') == $counter.find('.digit').length - 1){
	             //   setTimeout(function(){
	             //       animateDigit($display, e);
	             //   }, Math.max(550, Math.random() * 10000));
	            //}

	            // If we've reached the end of the counter, loop back to the top
	            if(parseInt($digit.css('top')) > -1 * parseInt($display.height())){
	                $digit.css({
	                    top: '-' + (parseInt($display.height()) * 10) + 'px'
	                });
	            }
	        });
	    }

	    $(function(){
	        initCounter($('.counter'), $.Event('load'));
	    });
	})(jQuery);</script>
</head>
<body>
<%
    String successMsg = (String)request.getAttribute("SCSS_MSG");
    String errorMsg = (String)request.getAttribute("ERR_MSG");

	boolean isAdmin = false;
	Boolean authorised = (Boolean)request.getSession().getAttribute("AUTH");
	if(null != authorised) {
		isAdmin = authorised;
	}
	if(null != errorMsg) {
		%>
		<div class="errMsg"><%=errorMsg %></div>
		<%
	}
	if(null != successMsg) {
		%>
		<div class="successMsg"><%=successMsg %></div>
		<%
	}
%>
<div> 
		<div>
			<% 
			if(isAdmin) {
					String name = (String)request.getSession().getAttribute("USERNAME");
					%> 
						Welcome, <b><%=name %></b> [<a href="<%=request.getContextPath()%>/logout.nos">Logout</a>]
					<%
									
			} else {
				%>
					<a href="<%=request.getContextPath()%>/showLogin.nos">Admin Login</a>
				<%
			}
			%>
			<span class="counter" style="float:right">
			    <span class="digit">
			        <span title="3">3</span>
			        <hr />
			    </span>
			    
			    <span class="digit">
			        <span title="2">2</span>
			        <hr />
			    </span>
			    <span class="digit">
			        <span title="4">4</span>
			        <hr />
			    </span>
			    <span class="digit">
			        <span title="4">4</span>
			        <hr />
			    </span>
			    
			    <span class="digit">
			        <span title="4">4</span>
			        <hr />
			    </span>
			    <span class="digit">
			        <span title="1">1</span>
			        <hr />
			    </span>
			    <span class="digit">
			        <span title="3">3</span>
			        <hr />
			    </span>
			</span>
			</div>
			
		<!-- div style="position:absolute;left:10px;top:150px;width:100px;border:1px solid black;">
			<div>Choose A Style</div>
			<div>Choose A Style</div>
			<div>Choose A Style</div>
			<div>Choose A Style</div>
		</div-->
		<div class="center pageTitle">
			<div class="pageHead">Welcome To Nielsen-One-Stop</div>
			<div class="pageQuote">Your ultimate destination to all Nielsen Urls !</div>
		</div>
		<form action="<%=request.getContextPath()%>/displayAddUI.nos" id="aForm">
			<div class="right">
				<div>
				<% if(isAdmin) { %>
					<input type="submit" class="blueBut" value="Add New Entry" id="B1" />
					<%}
					%>
					<input type="button" class="blueBut" value="Home" onclick="javascript:location.replace('<%=request.getContextPath() %>/showResult.nos')" />
				</div>
			</div>
		</form>
</div>
</body>
</html>