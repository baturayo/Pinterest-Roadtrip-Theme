<%-- 
    Document   : stats
    Created on : Mar 16, 2018, 2:38:35 PM
    Author     : cekef
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<div id="chart_div"></div>
<script>
    google.charts.load('current', {packages: ['corechart', 'bar']});
    google.charts.setOnLoadCallback(drawBasic);

    function processData(){
        var data = ${requestScope.logins};
        var results = {};
        data.forEach(date => {
            var toAdd = date["day"] + "-" + date["month"] + "-" + date["year"];
            if (toAdd in results){
                results[toAdd][1] += 1;
            } else{
                results[toAdd] = [new Date(date["year"],date["month"],date["day"]),1];
            }
            
        });
        
        var resultsArray = [];
        for (key in results){
            resultsArray.push([results[key][0], results[key][1]]);
        }
        return resultsArray;
    }

    function drawBasic() {

        var data = new google.visualization.DataTable();
        var calculated = processData();
        console.log(calculated);
        data.addColumn('date', 'Date of log in');
        data.addColumn('number', 'Amount Logged in');
        data.addRows(calculated);
//        data.addRows([
//            [{v: [8, 0, 0], f: '8 am'}, 1],
//            [{v: [9, 0, 0], f: '9 am'}, 2],
//            [{v: [10, 0, 0], f: '10 am'}, 3],
//            [{v: [11, 0, 0], f: '11 am'}, 4],
//            [{v: [12, 0, 0], f: '12 pm'}, 5],
//            [{v: [13, 0, 0], f: '1 pm'}, 6],
//            [{v: [14, 0, 0], f: '2 pm'}, 7],
//            [{v: [15, 0, 0], f: '3 pm'}, 8],
//            [{v: [16, 0, 0], f: '4 pm'}, 9],
//            [{v: [17, 0, 0], f: '5 pm'}, 10],
//        ]);

        var options = {
            title: 'Past logins each day',
            hAxis: {
                title: 'Date',
                format: 'dd-MM-yyyy'
            },
            vAxis: {
                title: 'Amount of times logged in'
            }
        };

        var chart = new google.visualization.ColumnChart(
                document.getElementById('chart_div'));

        chart.draw(data, options);
    }
</script>