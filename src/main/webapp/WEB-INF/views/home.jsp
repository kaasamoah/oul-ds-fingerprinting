<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>UoL MSc CS Dissertation - Asamoah</title>
        <link rel="stylesheet" type="text/css" href="/resources/plugins/bootstrap/css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" type="text/css" href="/resources/plugins/bootstrap/css/bootstrap-theme.min.css" />
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="span12 center-block">
                <div class="h1">Welcome to My Dissertation Project</div>
                A lot of text about the dissertation will go here for those actually interested in reading about it.
                <br />
                <hr />
                <br />
                If you would like like to assist me by participating, please enter your name and email address in the form below:
                <form action="/signup" method="POST">
                    <label for="ipt-name">Name</label>
                    <div id="ipt-name" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input name="visitor" class="form-control" type="email" placeholder="Enter your email address" />
                    </div>
                    
                    <label for="ipt-email">Email</label>
                    <div id="ipt-email" class="input-group">
                        <span class="input-group-addon">@</span>
                        <input name="email" class="form-control" type="email" placeholder="Enter your email address" />
                    </div>
                </form>
            </div>
        </div>

        <P>  The time on the server is ${serverTime}. </P>
    </div>

    <script type="text/javascript" src="/resources/plugins/jquery/jquery.js"></script>
    <script type="text/javascript" src="/resources/plugins/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
