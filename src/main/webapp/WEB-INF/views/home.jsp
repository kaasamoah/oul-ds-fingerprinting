<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<html>
    <head>
        <title>Browser Fingerprinting - Home</title>
        <link rel="stylesheet" type="text/css" href="<spring:url value='/plugins/bootstrap/css/bootstrap.min.css'/>" />
        <link rel="stylesheet" type="text/css" href="<spring:url value='/plugins/bootstrap/css/bootstrap-responsive.min.css'/>" />
        <link rel="stylesheet" type="text/css" href="<spring:url value='/plugins/jquery/css/jquery.loadmask.css'/>" />
        <link rel="stylesheet" type="text/css" href="<spring:url value='/styles/main.css'/>" />
    </head>
    <body>
        <div id="modal-loader" class="modal fade" data-keyboard="false" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true" show="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Please wait...</h4>
                    </div>
                    <div class="modal-body"> 
                        <p id="mask-msg"></p>
                        <div class="progress progress-striped active">
                            <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="100" style='width:100%'></div>                                                            
                        </div>    
                    </div>
                </div>
            </div>
        </div>
        <div id="main-container" class="container-fluid">
            <div id="header" class="row-fluid">
                <div class="span2">
                    <div class="fp-image"></div>
                </div>
                <div class="span8">
                    <h1 class="header-text">Device Fingerprinting <small>- A new way to identify users online</small></h1>
                </div>
                <div class="span2">
                    <div class="fp-image"></div>
                </div>
            </div>
            <br />
            <div class="row-fluid">
                <div id="content" class="container">
                    <div class="row">
                        <div class="span12">
                            <div class="container">
                                <div class="row">
                                    <div class="span4">                                                                             
                                        <div class="panel panel-primary">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">Returning Participant? Login!</h3>
                                            </div>
                                            <div class="panel-body">
                                                If you're a returning participant, please log in here:
                                                <br /><br />
                                                <div id='dv-login-err' role="alert"></div>
                                                <form class='well'>
                                                    <label for="ipt-returning-email">Email</label>
                                                    <div id="ipt-returning-email" class="input-group">
                                                        <span class="input-group-addon">@</span>
                                                        <input id="login-email" name="returning-email" class="form-control" type="email" placeholder="Enter your email address" />
                                                    </div> 
                                                    <br />
                                                    <label for="ipt-login-pass">Password</label>
                                                    <div id="ipt-login-pass" class="input-group">
                                                        <span class="input-group-addon">***</span>
                                                        <input id="login-pass" name="password" class="form-control" type="password" placeholder="Enter your password" />
                                                    </div>
                                                    <br />
                                                    <input type="button" class="btn btn-success pull-right" value="Log In!" onclick="login();" />
                                                    <br />
                                                    <br />
                                                </form>
                                            </div>
                                        </div>
                                        <div class="panel panel-info">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">New Participant? Signup!</h3>
                                            </div>
                                            <div class="panel-body">
                                                To participate in this project, please sign up below:
                                                <br /><br />
                                                <div id='dv-signup-err' role="alert"></div>
                                                <form class="well">
                                                    <label for="ipt-name">Name</label>
                                                    <div id="ipt-name" class="input-group">
                                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                        <input id="signup-name" name="visitor" class="form-control" type="email" placeholder="Enter your name" />
                                                    </div>
                                                    <br />
                                                    <label for="ipt-email">Email</label>
                                                    <div id="ipt-email" class="input-group">
                                                        <span class="input-group-addon">@</span>
                                                        <input id="signup-email" name="email" class="form-control" type="email" placeholder="Enter your email address" />
                                                    </div>
                                                    <br />
                                                    <label for="ipt-signup-pass">Password</label>
                                                    <div id="ipt-signup-pass" class="input-group">
                                                        <span class="input-group-addon">***</span>
                                                        <input id="signup-pass" name="password" class="form-control" type="password" placeholder="Enter your password" />
                                                    </div>
                                                    <br />
                                                    <input type="button" class="btn btn-warning pull-right" value="Sign Up!" onclick="signup();" />
                                                    <br />
                                                    <br />
                                                </form>
                                            </div>
                                        </div> 
                                    </div>
                                    <div class="span7">  
                                        <div  class="jumbotron">
                                            <p>Many websites desire a way to keep track of their visitors for purposes such as security, marketing
                                                customization and performance measurement to name a few. Over the years, many different techniques
                                                for keeping track of visitors have been developed. One of the most popular methods is the use of
                                                cookies. However, cookies can easily be disabled by users. A number of other techniques have been
                                                developed to get around this challenge such as the use of the
                                                <a target="_blank" href="http://en.wikipedia.org/wiki/Local_shared_object">Flash cookie or Local Shared Object (LSO)</a>
                                                but these still require flash support and, invariable, require some data to be stored on the client device.
                                                Enter the <a target="_blank" href='http://en.wikipedia.org/wiki/Device_fingerprint'>device/browser/machine fingerprint</a>.</p>
                                            <br />
                                            <p>Whenever a client device accesses a server it provides some identification and configuration
                                                information, in order to ensure smooth communication between the two machines. Studies such as the
                                                <a target="_blank" href="https://panopticlick.eff.org/">Panopticlick project</a> carried out by the Electronic
                                                Frontier Foundation have shown that this information can be gathered and processed in such a way
                                                as to enable identification and possibly even tracking of user devices!</p>
                                            <br />
                                            <p>Understandably, these findings have raised privacy concerns within the tech community. However, as
                                                with any other technology, it is merely a tool which can be used for helpful or harmful purposes. This
                                                project examines the use of this technology in online payment systems to help prevent fraud. It
                                                will serve as a second line of defence in the event that your account details are compromised. In order
                                                to carry out this research, I need data from volunteers. Please note that your data will only be collected
                                                if you fill in and submit the participants' form. Your data will not be used strictly for this research and
                                                will neither be sold nor given out for marketing or any other purposes. Your participation is greatly appreciated. Thank you.</p>                    
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

<!--            <P>  The time on the server is ${serverTime}. </P>-->
                    </div>
                </div>
            </div>
        </div>
        <div id="footer" class="row-fluid footer">
            <div class="span12 text-center">
                <footer>
                    This website was developed as part of a project carried out by Kofi A. Asamoah
                    for the University of Liverpool towards the awardance of the degree MSc. Computer Security.
                </footer>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<spring:url value='/plugins/jquery/jquery.js'/>"></script>
    <script type="text/javascript" src="<spring:url value='/plugins/bootstrap/js/bootstrap.min.js'/>"></script>
    <script type="text/javascript" src="<spring:url value='/plugins/jquery/jquery.loadmask.min.js'/>"></script>
    <script type="text/javascript" src="<spring:url value='/scripts/home-functions.js'/>"></script>
    <script type="text/javascript" src="<spring:url value='/scripts/fingerprint/fp_os.js'/>"></script>
    <script type="text/javascript" src="<spring:url value='/scripts/fingerprint/fp_fonts.js'/>"></script>
    <script type="text/javascript" src="<spring:url value='/scripts/fingerprint/fp_canvas.js'/>"></script>
    <script type="text/javascript" src="<spring:url value='/scripts/fingerprint/fp_true_browser.js'/>"></script>
</body>
</html>
