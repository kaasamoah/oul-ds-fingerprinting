<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Browser Fingerprinting - Returning</title>
        <link rel="stylesheet" type="text/css" href="<spring:url value='/plugins/bootstrap/css/bootstrap.min.css'/>" />
        <link rel="stylesheet" type="text/css" href="<spring:url value='/plugins/bootstrap/css/bootstrap-responsive.min.css'/>" />
        <link rel="stylesheet" type="text/css" href="<spring:url value='/styles/main.css'/>" />
        
        <script type="text/javascript">
            var register = false;
        </script>
    </head>
    <body>
        <div id="modal-loader" class="modal fade" data-keyboard="false" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true" show="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Please wait...</h4>
                    </div>
                    <div class="modal-body"> 
                        <div class="progress progress-striped active">
                            <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="100" style='width:100%'></div>                                                            
                        </div>    
                    </div>
                </div>
            </div>
        </div>

        <c:if test="${not empty device}">

            <div class="modal fade" id="modal-register" tabindex="-1" role="dialog" aria-hidden="true" data-keyboard="false" data-backdrop="static">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Register Device</h4>
                        </div>
                        <div class="modal-body center"> 
                            <form>
                                <div id="dv-reg-error" role="alert"></div>
                                <h4>Are you using a new device or a previously registered one?</h4>
                                <br />
                                If new, please name your device:
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>                                
                                    <input id="txtDevice" name="name" class="form-control" placeholder="Name your device" />
                                </div>
                                <br />
                                If registered, please choose:
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>  
                                    <select id="ddDevice" name="device" class="form-control">
                                        <option value="">Choose your device</option>
                                        <optgroup data-bind="foreach: devices">
                                            <option data-bind="value: id, text: name"></option>
                                        </optgroup>
                                    </select>
                                </div>
                                <input id="txtConfig" type="hidden" value="${device.id}" />
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-success" onclick="registerDevice();">
                                Save
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <script type="text/javascript">
                register = true;
            </script>
        </c:if>

        <div class="container-fluid">
            <div id="header" class="row-fluid">
                <div class="span2">
                    <div class="fp-image"></div>
                </div>
                <div class="span8">
                    <h1 class="header-text">Device Fingerprinting <small>- A new way to identify users online</small>
                        <a class='btn btn-danger pull-right' href="<spring:url value="/logout" />">Logout <i class="glyphicon glyphicon-log-out"></i></a>
                    </h1>                    
                </div>
                <div class="span2">
                    <div class="fp-image"></div>
                </div>
            </div>
            <br />
            <div class="row-fluid">
                <div class="container">
                    <div class="row">
                        <div class="span12">
                            <!--                            <div class="page-header">
                                                            <h1 class="header-text">Device Fingerprinting <small>- A new way to identify users online</small>
                                                                <a class='btn btn-danger pull-right' href="<spring:url value="/logout" />">Logout <i class="glyphicon glyphicon-log-out"></i></a>
                                                            </h1>
                                                        </div>-->
                            <div class="alert alert-success"><b>Welcome ${firstName}!</b> Thank you for taking part in this project. Your details are listed below.</div>
<!--                            <p class="h4">Visitor Number: <span class="label label-primary">${user.password}</span></p>-->
                            <p class="h4">Name: <span class="label label-primary">${user.fullname}</span></p>
                            <p class="h4">Email Address: <span class="label label-primary">${user.username}</span></p>
                        </div>
                    </div>
                    <br />
                    <div id="device-div" class="row">                
                        <div class="span12">
                            <div class="page-header">
                                <h1>My Devices <small>Your registered devices are listed below:</small></h1>
                            </div> 
                            <div id="dv-notification" role="alert"></div>
                            <table id="device-list" class="table table-bordered table-striped table-responsive">
                                <thead>
                                    <tr>
                                        <th>Device</th>
                                        <th>Date Added</th>
                                    </tr>
                                </thead>
                                <tbody data-bind="foreach: devices">
                                    <tr>
                                        <td data-bind="text: name"></td>
                                        <td data-bind="text: dateAdded"></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="visit-div" class="row">                
                        <div class="span12">
                            <div class="page-header">
                                <h1>My Visits <small>Below is a list of your last 10 visits:</small></h1>
                            </div>  
                            <table id="visit-list" class="table table-bordered table-striped table-responsive">
                                <thead>
                                    <tr>
                                        <th>Session ID</th>
                                        <th>Device</th>
                                        <th>Browser</th>
                                        <th>Date</th>
                                    </tr>
                                </thead>
                                <tbody data-bind="foreach: sessions">
                                    <tr>
                                        <td data-bind="text: id"></td>
                                        <td data-bind="text: config.device.name"></td>
                                        <td data-bind="text: browser.name"></td>
                                        <td data-bind="text: date"></td>
                                    </tr>
                                </tbody>
                                <!--                        <tfoot>                                        
                                                            <tr>
                                                                <td colspan="8" class="well">
                                                                    <div class="btn-toolbar" role="toolbar">
                                                                        <div class="btn-group">
                                                                            <button type="button" class="btn btn-default">&lt;&lt;</button>
                                                                            <button type="button" class="btn btn-default">1</button>
                                                                            <button type="button" class="btn btn-default">2</button>
                                                                            <button type="button" class="btn btn-default">3</button>
                                                                            <button type="button" class="btn btn-default">&gt;&gt;</button>
                                                                        </div>
                                                                        <span class="pull-right">Showing visits 1 - 10 of 26</span>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </tfoot>-->
                            </table>
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
        <script type="text/javascript" src="<spring:url value='/plugins/knockout/knockout-3.2.0.js'/>"></script>
        <script type="text/javascript" src="<spring:url value='/scripts/visitor-functions.js'/>"></script>
        <script type="text/javascript" src="<spring:url value='/scripts/model/session-model.js'/>"></script>
        <script type="text/javascript" src="<spring:url value='/scripts/model/device-model.js'/>"></script>
    </body>
</html>
