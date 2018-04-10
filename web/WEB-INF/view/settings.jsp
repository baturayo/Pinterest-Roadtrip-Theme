<%-- 
    Document   : settings
    Created on : Mar 14, 2018, 1:01:07 PM
    Author     : Michael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<div class="container-fluid">
    <div class="col-sm-4">
        <h2>Settings</h2>
        <div class="btn-group-vertical">

            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#emailmodal">
                Change Email
            </button>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#usernamemodal">
                Change Username
            </button>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#passwordmodal">
                Change Password
            </button>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#firstnamemodal">
                Change First Name
            </button>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#lastnamemodal">
                Change Last Name
            </button>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#countrymodal">
                Change Country
            </button>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#gendermodal">
                Change Gender
            </button>
        </div>
    </div>

    <div class="col-sm-8">

        <div class="panel panel-default">
            <div class="panel-heading">User Info</div>
            <div class="panel-body">
                <p>${requestScope.settingsusername}</p>
                <p>email: ${requestScope.settingsemail}</p>

                <p>first name: ${requestScope.settingsfirstname}</p>
                <p>last name: ${requestScope.settingslastname}</p>

                <p>country: ${requestScope.settingscountry}</p>
                <p>gender: ${requestScope.settingsgender}</p>

            </div>
        </div>

    </div>




    <div class="modal fade" id="emailmodal">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">Change Your Email</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <div class="modal-body">
                    <form name="changeemail" action="settings" method="POST">
                        <div class="form-group">
                            <input type="hidden" name="formName" value="changeemail1" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            New Email: <input type="text" name="newemail" required/>
                        </div>
                        <div class=form-group">
                            <button type="submit" class="btn btn-primary">Change Email</button>
                        </div>
                    </form>
                </div>      
            </div>
        </div>
    </div>

    <div class="modal fade" id="usernamemodal">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">Change Your Username</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <div class="modal-body">
                    <form name="changeusername" action="settings" method="POST">
                        <div class="form-group">
                            <input type="hidden" name="formName" value="changeusername1" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            New Username <input type="text" name="newusername" required/>
                        </div>
                        <div class=form-group">
                            <button type="submit" class="btn btn-primary">Change Username</button>
                        </div>
                    </form>
                </div>      
            </div>
        </div>
    </div>

    <div class="modal fade" id="passwordmodal">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">Change Your Password</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <div class="modal-body">
                    <form name="changepassword" action="settings" method="POST">
                        <div class="form-group">
                            <input type="hidden" name="formName" value="changepassword1" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            Old Password <input type="password" name="oldpassword" required />
                        </div>
                        <div class="form-group">
                            New Password <input type="password" name="newpassword" required/>
                        </div>
                        <div class=form-group">
                            <button type="submit" class="btn btn-primary">Change Password</button>
                        </div>
                    </form>
                </div>      
            </div>
        </div>
    </div>

    <div class="modal fade" id="firstnamemodal">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">Change Your First Name</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <div class="modal-body">
                    <form name="changefirstname" action="settings" method="POST">
                        <div class="form-group">
                            <input type="hidden" name="formName" value="changefirstname1" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            New First Name <input type="text" name="newfirstname" required/>
                        </div>
                        <div class=form-group">
                            <button type="submit" class="btn btn-primary">Change First Name</button>
                        </div>
                    </form>
                </div>      
            </div>
        </div>
    </div>

    <div class="modal fade" id="lastnamemodal">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">Change Your Last Name</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <div class="modal-body">
                    <form name="changelastname" action="settings" method="POST">
                        <div class="form-group">
                            <input type="hidden" name="formName" value="changelastname1" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            New Last Name <input type="text" name="newlastname" required/>
                        </div>
                        <div class=form-group">
                            <button type="submit" class="btn btn-primary">Change Last Name</button>
                        </div>
                    </form>
                </div>      
            </div>
        </div>
    </div>

    <div class="modal fade" id="countrymodal">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">Change Your Country</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <div class="modal-body">
                    <form name="changecountry" action="settings" method="POST">
                        <div class="form-group">
                            <input type="hidden" name="formName" value="changecountry1" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            New Country <input type="text" name="newcountry" required/>
                        </div>
                        <div class=form-group">
                            <button type="submit" class="btn btn-primary">Change Country</button>
                        </div>
                    </form>
                </div>      
            </div>
        </div>
    </div>

    <div class="modal fade" id="gendermodal">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">Change Your Gender</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <div class="modal-body">
                    <form name="changegender" action="settings" method="POST">
                        <div class="form-group">
                            <input type="hidden" name="formName" value="changegender1" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            New Gender: <select name="newgender" required>
                                <option>Male</option>
                                <option>Female</option>
                                <option>Other</option>
                            </select>
                        </div>
                        <div class=form-group">
                            <button type="submit" class="btn btn-primary">Change Gender</button>
                        </div>
                    </form>
                </div>      
            </div>
        </div>
    </div>
                
<c:if test="${not empty requestScope.verificationError}">
    <script>
        function alertLogin() {
            if("${requestScope.verificationError}"){
                alert("${requestScope.verificationError}");
            }
        }
        window.onload = alertLogin;
    </script>
</c:if>
