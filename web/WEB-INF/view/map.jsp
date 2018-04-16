<!-- amCharts javascript sources -->
<link href="../ammap/ammap.css" rel="stylesheet" type="text/css">

<script src="../ammap/ammap.js" type="text/javascript"></script>
<script src="../ammap/maps/js/${requestScope.country.getFilename()}.js" type="text/javascript"></script>




<!-- amCharts javascript code -->
<script type="text/javascript">
    var map;

    AmCharts.ready(function () {
        map = new AmCharts.AmMap();
        console.log("HERE");
        var dataProvider = {
            mapVar: AmCharts.maps.${requestScope.country.getFilename()}
        };
        map.dataProvider = dataProvider;
        map.write("mapdiv");
    });

    // TODO: ADD THE FLAGS OF MAJOR POINTS

</script>
<div class="row">
    <div class = "panel">
        <div class="panel-heading">
            Map
        </div>
        <div class = "panel-body">
            <div id="mapdiv" style="width: 100%; height: 500px;" onload="loadChart()"></div>
        </div>
    </div>

</div>
<div class="row">
    <div class="col-sm-6">
        <div class = "panel">
            <div class="panel-heading">
                Roads
            </div>
            <div class = "panel-body">
                <c:forEach var="road" items="${requestScope.roads}">
                    <div class="row">
                        <a href="/RoadTrip/road/${road.getId()}">${road.getName()}</a>
                    </div>
                </c:forEach>        </div>
        </div>

    </div>
    <div class="col-sm-6">
        <div class = "panel">
            <div class="panel-heading">
                Checkpoints
            </div>
            <div class = "panel-body">
                <c:forEach var="checkpoint" items="${requestScope.checkpoints}">
                    <div class="row">
                        <a href="/RoadTrip/checkpoint?id=${checkpoint.getId()}">${checkpoint.getName()}</a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>