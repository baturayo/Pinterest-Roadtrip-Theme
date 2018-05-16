<%-- 
    Document   : admincreate
    Created on : May 14, 2018, 6:23:17 PM
    Author     : Michael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Create Page</title>
    </head>
    <body>
       
        <div class="container-fluid">
            <div class="col-sm-4">
                <h2>Create Content</h2>
                <h5>As an admin, you can create content like checkpoints and roads here</h5>
                <div class="btn-group-vertical">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#checkpointmodal">
                        Create Checkpoint
                    </button>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#roadmodal">
                        Create Road
                    </button>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#linkmodal">
                        Link Road and Checkpoint
                    </button>

                </div>
            </div>
        </div>

        <div class="modal fade" id="checkpointmodal">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <h4 class="modal-title">Create Checkpoint</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <div class="modal-body">
                        <form name="createcp" action="" method="POST">
                            <div class="form-group">
                                <input type="hidden" name="formName" value="createcp1" readonly="readonly"/>
                            </div>
                            <div class="form-group">
                                CheckPoint Name: <input type="text" name="newcpname" required/>
                            </div>
                            <div class="form-group">
                                Country Code: <input type="text" name="newcpcountry" required/>
                            </div>
                            <div class="form-group">
                                Description <input type="text" name="newcpdesc" required/>
                            </div>
                            <div class="form-group">
                                X-coordinate: <input type="number" name="newcpx" required/>
                            </div>
                            <div class="form-group">
                                Y-coordinate: <input type="number" name="newcpy" required/>
                            </div>
                            <div class=form-group">
                                <button type="submit" class="btn btn-primary">Create Checkpoint</button>
                            </div>
                        </form>
                    </div>      
                </div>
            </div>
        </div>
        
        <div class="modal fade" id="roadmodal">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <h4 class="modal-title">Create Road</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <div class="modal-body">
                        <form name="creater" action="" method="POST">
                            <div class="form-group">
                                <input type="hidden" name="formName" value="creater1" readonly="readonly"/>
                            </div>
                            <div class="form-group">
                                Road Name: <input type="text" name="newroad" required/>
                            </div>
                             <div class="form-group">
                                Country Code: <input type="text" name="newroadcountry" required/>
                            </div>
                            <div class="form-group">
                                Description <input type="text" name="newroaddesc" required/>
                            </div>
                            <div class=form-group">
                                <button type="submit" class="btn btn-primary">Create Road</button>
                            </div>
                        </form>
                    </div>      
                </div>
            </div>
        </div>
        
        <div class="modal fade" id="linkmodal">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <h4 class="modal-title">Link Checkpoint and Road</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <div class="modal-body">
                        <form name="linkcpr" action="" method="POST">
                            <div class="form-group">
                                <input type="hidden" name="formName" value="linkcpr1" readonly="readonly"/>
                            </div>
                            <div class="form-group">
                                CheckPoint Name: <input type="text" name="linkedcp" required/>
                            </div>
                            <div class="form-group">
                                Road Name: <input type="text" name="linkedr" required/>
                            </div>
                            <div class=form-group">
                                <button type="submit" class="btn btn-primary">Link Checkpoint and Road</button>
                            </div>
                        </form>
                    </div>      
                </div>
            </div>
        </div>
        
        
        <c:if test="${not empty requestScope.creationError}">
            <script>
                function alertLogin() {
                    if("${requestScope.creationError}"){
                        alert("${requestScope.creationError}");
                    }
                }
                window.onload = alertLogin;
            </script>
        </c:if> 
    </body>
</html>
