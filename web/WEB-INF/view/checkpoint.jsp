<%-- 
    Document   : checkpoint
    Created on : Apr 6, 2018, 1:25:52 PM
    Author     : Michael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="card.css">

        <title>Checkpoint Page</title>
    </head>
    <body>
        <c:forEach items="${photos}" var="photo">
            <div class="modal fade" id="updatephotomodal${photo.getId()}">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <div class="modal-header">
                            <h4 class="modal-title">Update Photo</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <div class="modal-body">
                            <form name="updatephotoform${photo.getId()}" action="" method="POST">

                                <div class="form-group">
                                    New Description: <input type="text" name="updatedescription${photo.getId()}" required/>
                                </div>

                                <div class=form-group">
                                    <button type="submit" class="btn btn-primary">Update Photo</button>
                                </div>
                            </form>
                        </div>      
                    </div>
                </div>
            </div>
        </c:forEach>
        
        <div class="col-sm-4">

            <div class="panel panel-default">
                <div class="panel-heading">${requestScope.Checkpoint.getName()}</div>
                <div class="panel-body">
                    <p>${requestScope.Checkpoint.getDescription()}</p>
                    <p> The following roads contain this checkpoint: </p>
                    <c:forEach items="${roads}" var="road">
                        <p><a href="/RoadTrip/road/${road.getId()}"> ${road.getName()}</a></p>
                        
                    </c:forEach>

                        <form name="setvisit" action="" method="POST">
                            <div class="btn-group">
                                <button type="submit" name="cpform" value="setvisited" class="btn btn-info">Visited</button>
                                <button type="submit" name="cpform" value="setwanttovisit" class="btn btn-success">I want to visit this checkpoint</button>
                            </div>
                        </form>
                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#addphotomodal">
                            Add Photo
                        </button>
                </div>
            </div>

        </div>

        <div class="col-sm-8">

            <div class="card-columns">
                <c:forEach items="${photos}" var="photo">

                    <div class="card">
                        <c:if test = "${photo.getUser().getId() == sessionScope.userId}">
                            <div class="pull-right">
                                <button data-toggle="modal" data-target="#updatephotomodal${photo.getId()}">
                                    <i class="fa fa-gear"></i>
                                </button>
                                <form class="form-inline" name="editphoto${photo.getId()}" action="" method="POST">

                                    <button type="submit" name="photoform" value="deletephoto${photo.getId()}"><i class="fa fa-close"></i></button>
                                </form>
                            </div>
                        </c:if>

                        <img class="card-img-top" src="${photo.getUrl()}" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title"><a href="/RoadTrip/users/${photo.getUser().getUsername()}">${photo.getUser().getUsername()}</a></h5>

                            <p class="card-text">${photo.getDescription()}</p>
                            <a  data-toggle="collapse" href="#commentmodal${photo.getId()}">
                                Toggle Comments
                            </a>

                            <div id="commentmodal${photo.getId()}" class="collapse">
                                <form class="form-inline" name="addcomment${photo.getId()}" action="" method="POST">

                                    <div class="row">
                                    <div class="form-group col-sm-8">
                                        <input type="text" name="newcomment${photo.getId()}" required/>
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <button type="submit" class="btn btn-primary btn-sm">Post</button>
                                    </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
                    
                    
    <div class="modal fade" id="addphotomodal">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">Add A Photo</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <div class="modal-body">
                    <form name="addphoto" action="" method="POST">
                        <div class="form-group">
                            <input type="hidden" name="cpform" value="addphoto1" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            Description: <input type="text" name="newdescription" required/>
                        </div>
                        <div class="form-group">
                            URL: <input type="text" name="newurl" required/>
                        </div>
                        <div class=form-group">
                            <button type="submit" class="btn btn-primary">Add Photo</button>
                        </div>
                    </form>
                </div>      
            </div>
        </div>
    </div>
                    
                    
                    


    </body>
</html>
