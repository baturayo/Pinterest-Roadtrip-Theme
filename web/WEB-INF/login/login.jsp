<%-- 
    Document   : login
    Created on : Mar 10, 2018, 2:21:25 PM
    Author     : cekef
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

        <nav class="navbar navbar-inverse">
          <div class="container-fluid">
            <div class="navbar-header">
              <a class="navbar-brand" href="#">P4RT</a>
            </div>
            <form class="navbar-form navbar-right" action="main">
              <div class="form-group">
                <input type="text" class="form-control" placeholder="username" name="username">
              </div>
              <div class="form-group">
                <input type="password" class="form-control" placeholder="password" name="password">
              </div>
              <button type="submit" class="btn btn-success">Log in</button>
            </form>
          </div>
        </nav>        
        <div class="container">
          <div class="row">
              <div class="col-sm-6">
                  <img src="https://cf.bodyandmind.amsterdam/wp-content/uploads/2016/02/roadtrip.jpg" class="img-rounded" alt="rtimg">
              </div>
              <div class="col-sm-6">
                  <h3>Make A New Account</h3>
                <form name="registerform" action="main2" method="POST">
                    <div class="form-group">
                        First name: <input type="text" name="firstName" required />
                    </div>
                    <div class="form-group">
                        Second name: <input type="text" name="username" required/>
                    </div>
                    <div class="form-group">
                        Country: <input type="text" name="country" value="" />
                    </div>
                    <div class="form-group">
                        Gender: <select name="gender" required>
                            <option>Male</option>
                            <option>Female</option>
                            <option>Other</option>
                        </select>
                    </div>
                    <div class="form-group">
                        Username: <input type="text" name="username1" required/>
                    </div>
                    <div class="form-group">
                        e-mail: <input type="email" name="mail" required/>
                    </div>
                    <div class="form-group">
                        Password: <input type="password" name="password1" required/>
                    </div>
                    <div class=form-group">
                        <button type="submit" class="btn btn-primary">Register</button>
                    </div>
                </form>
                  
              </div>
          </div>
        </div>
    
