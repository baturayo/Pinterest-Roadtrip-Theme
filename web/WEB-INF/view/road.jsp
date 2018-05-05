<%-- 
    Document   : road
    Created on : Apr 14, 2018, 11:03:18 AM
    Author     : cekef
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-md-6">
        <h1>${requestScope.road.getName()}</h1>
        <p>${requestScope.road.getDescription()}</p>
        <p>Suggested Vehicles:</p>
        <div style="overflow: auto">
        <table style="overflow: scroll;">
            <tbody>
                <tr>
                    <th><a href="https://www.hertz.be">
                            <img src="https://cdn.rcstatic.com/images/supplier_logos/hertz_logo_lrg.gif" height="50">
                            <figcaption>Hertz</figcaption>
                        </a></th>
                    <th><a href="https://www.hertz.be">
                            <img src="https://cdn.rcstatic.com/images/supplier_logos/hertz_logo_lrg.gif" height="50">
                            <figcaption>Hertz</figcaption>
                        </a></th>
                    <th><a href="https://www.hertz.be">
                            <img src="https://cdn.rcstatic.com/images/supplier_logos/hertz_logo_lrg.gif" height="50">
                            <figcaption>Hertz</figcaption>
                        </a></th>
                    <th><a href="https://www.hertz.be">
                            <img src="https://cdn.rcstatic.com/images/supplier_logos/hertz_logo_lrg.gif" height="50">
                            <figcaption>Hertz</figcaption>
                        </a></th>
                    <th><a href="https://www.hertz.be">
                            <img src="https://cdn.rcstatic.com/images/supplier_logos/hertz_logo_lrg.gif" height="50">
                            <figcaption>Hertz</figcaption>
                        </a></th>
                    <th><a href="https://www.hertz.be">
                            <img src="https://cdn.rcstatic.com/images/supplier_logos/hertz_logo_lrg.gif" height="50">
                            <figcaption>Hertz</figcaption>
                        </a></th>
                    <th><a href="https://www.hertz.be">
                            <img src="https://cdn.rcstatic.com/images/supplier_logos/hertz_logo_lrg.gif" height="50">
                            <figcaption>Hertz</figcaption>
                        </a></th>
                    <th><a href="https://www.hertz.be">
                            <img src="https://cdn.rcstatic.com/images/supplier_logos/hertz_logo_lrg.gif" height="50">
                            <figcaption>Hertz</figcaption>
                        </a></th>
                    <th><a href="https://www.hertz.be">
                            <img src="https://cdn.rcstatic.com/images/supplier_logos/hertz_logo_lrg.gif" height="50">
                            <figcaption>Hertz</figcaption>
                        </a></th>
                    <th><a href="https://www.hertz.be">
                            <img src="https://cdn.rcstatic.com/images/supplier_logos/hertz_logo_lrg.gif" height="50">
                            <figcaption>Hertz</figcaption>
                        </a></th>
                    <th><a href="https://www.hertz.be">
                            <img src="https://cdn.rcstatic.com/images/supplier_logos/hertz_logo_lrg.gif" height="50">
                            <figcaption>Hertz</figcaption>
                        </a></th>
                    <th><a href="https://www.hertz.be">
                            <img src="https://cdn.rcstatic.com/images/supplier_logos/hertz_logo_lrg.gif" height="50">
                            <figcaption>Hertz</figcaption>
                        </a></th>
                    <th><a href="https://www.hertz.be">
                            <img src="https://cdn.rcstatic.com/images/supplier_logos/hertz_logo_lrg.gif" height="50">
                            <figcaption>Hertz</figcaption>
                        </a></th>
                </tr>
            </tbody>
        </table>
        </div>


    </div>
    <div class="col-md-6">
        <p> Contains:</p>

        <c:forEach items="${requestScope.checkpoints}" var="checkpoint">
            <p>
                <a href="/RoadTrip/checkpoint?id=${checkpoint.getId()}">${checkpoint.getName()}</a>
            </p>
        </c:forEach>
    </div>
</div>