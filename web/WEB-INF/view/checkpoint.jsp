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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>

            .card {
                position: relative;
                display: -ms-flexbox;
                display: flex;
                -ms-flex-direction: column;
                flex-direction: column;
                min-width: 0;
                word-wrap: break-word;
                background-color: #fff;
                background-clip: border-box;
                border: 1px solid rgba(0, 0, 0, 0.125);
                border-radius: 0.25rem;
            }

            .card > hr {
                margin-right: 0;
                margin-left: 0;
            }

            .card > .list-group:first-child .list-group-item:first-child {
                border-top-left-radius: 0.25rem;
                border-top-right-radius: 0.25rem;
            }

            .card > .list-group:last-child .list-group-item:last-child {
                border-bottom-right-radius: 0.25rem;
                border-bottom-left-radius: 0.25rem;
            }

            .card-body {
                -ms-flex: 1 1 auto;
                flex: 1 1 auto;
                padding: 1.25rem;
            }

            .card-title {
                margin-bottom: 0.75rem;
            }

            .card-subtitle {
                margin-top: -0.375rem;
                margin-bottom: 0;
            }

            .card-text:last-child {
                margin-bottom: 0;
            }

            .card-link:hover {
                text-decoration: none;
            }

            .card-link + .card-link {
                margin-left: 1.25rem;
            }

            .card-header {
                padding: 0.75rem 1.25rem;
                margin-bottom: 0;
                background-color: rgba(0, 0, 0, 0.03);
                border-bottom: 1px solid rgba(0, 0, 0, 0.125);
            }

            .card-header:first-child {
                border-radius: calc(0.25rem - 1px) calc(0.25rem - 1px) 0 0;
            }

            .card-header + .list-group .list-group-item:first-child {
                border-top: 0;
            }

            .card-footer {
                padding: 0.75rem 1.25rem;
                background-color: rgba(0, 0, 0, 0.03);
                border-top: 1px solid rgba(0, 0, 0, 0.125);
            }

            .card-footer:last-child {
                border-radius: 0 0 calc(0.25rem - 1px) calc(0.25rem - 1px);
            }

            .card-header-tabs {
                margin-right: -0.625rem;
                margin-bottom: -0.75rem;
                margin-left: -0.625rem;
                border-bottom: 0;
            }

            .card-header-pills {
                margin-right: -0.625rem;
                margin-left: -0.625rem;
            }

            .card-img-overlay {
                position: absolute;
                top: 0;
                right: 0;
                bottom: 0;
                left: 0;
                padding: 1.25rem;
            }

            .card-img {
                width: 100%;
                border-radius: calc(0.25rem - 1px);
            }

            .card-img-top {
                width: 100%;
                border-top-left-radius: calc(0.25rem - 1px);
                border-top-right-radius: calc(0.25rem - 1px);
            }

            .card-img-bottom {
                width: 100%;
                border-bottom-right-radius: calc(0.25rem - 1px);
                border-bottom-left-radius: calc(0.25rem - 1px);
            }

            .card-deck {
                display: -ms-flexbox;
                display: flex;
                -ms-flex-direction: column;
                flex-direction: column;
            }

            .card-deck .card {
                margin-bottom: 15px;
            }

            @media (min-width: 576px) {
                .card-deck {
                    -ms-flex-flow: row wrap;
                    flex-flow: row wrap;
                    margin-right: -15px;
                    margin-left: -15px;
                }
                .card-deck .card {
                    display: -ms-flexbox;
                    display: flex;
                    -ms-flex: 1 0 0%;
                    flex: 1 0 0%;
                    -ms-flex-direction: column;
                    flex-direction: column;
                    margin-right: 15px;
                    margin-bottom: 0;
                    margin-left: 15px;
                }
            }

            .card-group {
                display: -ms-flexbox;
                display: flex;
                -ms-flex-direction: column;
                flex-direction: column;
            }

            .card-group > .card {
                margin-bottom: 15px;
            }

            @media (min-width: 576px) {
                .card-group {
                    -ms-flex-flow: row wrap;
                    flex-flow: row wrap;
                }
                .card-group > .card {
                    -ms-flex: 1 0 0%;
                    flex: 1 0 0%;
                    margin-bottom: 0;
                }
                .card-group > .card + .card {
                    margin-left: 0;
                    border-left: 0;
                }
                .card-group > .card:first-child {
                    border-top-right-radius: 0;
                    border-bottom-right-radius: 0;
                }
                .card-group > .card:first-child .card-img-top,
                .card-group > .card:first-child .card-header {
                    border-top-right-radius: 0;
                }
                .card-group > .card:first-child .card-img-bottom,
                .card-group > .card:first-child .card-footer {
                    border-bottom-right-radius: 0;
                }
                .card-group > .card:last-child {
                    border-top-left-radius: 0;
                    border-bottom-left-radius: 0;
                }
                .card-group > .card:last-child .card-img-top,
                .card-group > .card:last-child .card-header {
                    border-top-left-radius: 0;
                }
                .card-group > .card:last-child .card-img-bottom,
                .card-group > .card:last-child .card-footer {
                    border-bottom-left-radius: 0;
                }
                .card-group > .card:only-child {
                    border-radius: 0.25rem;
                }
                .card-group > .card:only-child .card-img-top,
                .card-group > .card:only-child .card-header {
                    border-top-left-radius: 0.25rem;
                    border-top-right-radius: 0.25rem;
                }
                .card-group > .card:only-child .card-img-bottom,
                .card-group > .card:only-child .card-footer {
                    border-bottom-right-radius: 0.25rem;
                    border-bottom-left-radius: 0.25rem;
                }
                .card-group > .card:not(:first-child):not(:last-child):not(:only-child) {
                    border-radius: 0;
                }
                .card-group > .card:not(:first-child):not(:last-child):not(:only-child) .card-img-top,
                .card-group > .card:not(:first-child):not(:last-child):not(:only-child) .card-img-bottom,
                .card-group > .card:not(:first-child):not(:last-child):not(:only-child) .card-header,
                .card-group > .card:not(:first-child):not(:last-child):not(:only-child) .card-footer {
                    border-radius: 0;
                }
            }

            .card-columns .card {
                margin-bottom: 0.75rem;
            }

            @media (min-width: 576px) {
                .card-columns {
                    -webkit-column-count: 3;
                    -moz-column-count: 3;
                    column-count: 3;
                    -webkit-column-gap: 1.25rem;
                    -moz-column-gap: 1.25rem;
                    column-gap: 1.25rem;
                    orphans: 1;
                    widows: 1;
                }
                .card-columns .card {
                    display: inline-block;
                    width: 100%;
                }
            }

            .accordion .card:not(:first-of-type):not(:last-of-type) {
                border-bottom: 0;
                border-radius: 0;
            }

            .accordion .card:not(:first-of-type) .card-header:first-child {
                border-radius: 0;
            }

            .accordion .card:first-of-type {
                border-bottom: 0;
                border-bottom-right-radius: 0;
                border-bottom-left-radius: 0;
            }

            .accordion .card:last-of-type {
                border-top-left-radius: 0;
                border-top-right-radius: 0;
            }

        </style>

        <title>Checkpoint Page</title>
    </head>
    <body>
        
        <div class="col-sm-4">

            <div class="panel panel-default">
                <div class="panel-heading">${requestScope.Checkpoint.getName()}</div>
                <div class="panel-body">
                    <p>${requestScope.Checkpoint.getDescription()}</p>
                    <p> The following roads contain this checkpoint: </p>
                    <c:forEach items="${roads}" var="road">
                        <p> ${road.getName()}</p>
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
                        <img class="card-img-top" src="${photo.getUrl()}" alt="Card image cap">
                        <div class="card-body">
                            <p class="card-text">${photo.getDescription()}</p>
                            <button type="button" class="btn btn-info" data-toggle="collapse" data-target="commentmodal${photo.getId()}">Toggle Comments</button>
                            <div id="commentmodal${photo.getId()}">
                                Test
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
