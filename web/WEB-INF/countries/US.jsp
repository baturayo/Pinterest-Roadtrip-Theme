<!-- amCharts javascript sources -->
<script type="text/javascript" src="https://www.amcharts.com/lib/3/ammap.js"></script>
<script type="text/javascript" src="https://www.amcharts.com/lib/3/maps/js/usa2Low.js"></script>

<!-- amCharts javascript code -->
<script type="text/javascript">
    AmCharts.makeChart("map", {
        "type":"map",
        "dataProvider":{
            "map":"usa2Low"
        }
    }
    );

</script>
<div id="map" style="width: 100%; height: 966px;" onload="loadChart()"></div>