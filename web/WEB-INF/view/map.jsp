<!-- amCharts javascript sources -->
<link href="../ammap/ammap.css" rel="stylesheet" type="text/css">

<script src="../ammap/ammap.js" type="text/javascript"></script>
<script src="../ammap/maps/js/${requestScope.country}.js" type="text/javascript"></script>


    

<!-- amCharts javascript code -->
<script type="text/javascript">
    var map;
    
    AmCharts.ready(function() {
        map = new AmCharts.AmMap();
        console.log("HERE");
        var dataProvider = {
            mapVar: AmCharts.maps.${requestScope.country}
        };
        map.dataProvider = dataProvider;
        map.write("mapdiv");
    });
    
    // TODO: ADD THE FLAGS OF MAJOR POINTS

</script>
<div id="mapdiv" style="width: 100%; height: 500px;" onload="loadChart()"></div>