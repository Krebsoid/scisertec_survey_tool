'use strict';

var chartModule = angular.module('ChartModule',[]);

chartModule.directive('pieChart', function() {
    return {
        restrict:'A',
        scope : {
            data : '@pieChart'
        },
        link:function (scope, element, attrs) {

            var pieChart;
            scope.$watch(
                function() {
                    return scope.data;
                },
                function(data) {
                    var series = [];
                    var isChartEmpty = true;
                    if(data != '') {
                        angular.forEach(JSON.parse(data), function(value, key) {
                            series.push([key,value]);
                            if(value > 0) {
                                isChartEmpty = false;
                            }
                        });
                    }
                    if(!pieChart && !isChartEmpty) {
                        pieChart = angular.element(element).highcharts({
                            chart: {
                                plotBackgroundColor: null,
                                plotBorderWidth: null,
                                plotShadow: false,
                                renderTo: angular.element(element),
                                type: 'pie'
                            },
                            title: {
                                text: attrs.chartTitle
                            },
                            plotOptions: {
                                pie: {
                                    allowPointSelect: true,
                                    cursor: 'pointer',
                                    dataLabels: {
                                        enabled: true,
                                        color: '#000000',
                                        connectorColor: '#000000',
                                        formatter: function() {
                                            return '<b>'+ this.point.name +'</b>: '+ Math.round(this.percentage) +' %';
                                        }
                                    }
                                }
                            },
                            series: [{
                                id : attrs.id,
                                type: 'pie',
                                name: attrs.seriesName,
                                data: series
                            }]
                        });
                    }
                    else {
                        var chart = angular.element(element).highcharts();
                        if(chart) {
                            chart.get(attrs.id).setData(series);
                        }
                    }
                }
            );
        }
    }
});

chartModule.directive('columnChart', function() {
    return {
        restrict:'A',
        transclude: true,
        controller:function ($scope, $element, $attrs) {
            var categories = [];
            if($attrs.categories != '') {
                categories = $attrs.categories.split(',');
            }
            angular.element($element).highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: $attrs.chartTitle
                },
                xAxis: {
                    categories: categories
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: $attrs.yAxisText
                    }
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                        '<td style="padding:0"><b>{point.y}</b></td></tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                }
            });

            this.addSeries = function(series) {
                var chart = angular.element($element).highcharts();
                chart.addSeries(series);
            }
        },
        template : '<div data-ng-transclude></div>'
    }
})
    .directive('columnChartSeries', function() {
        return {
            restrict:'A',
            require: '^columnChart',
            scope : {
                seriesData : '@columnChartSeries'
            },
            link:function (scope, element, attrs, controller) {
                scope.$watch(
                    function() {
                        return scope.seriesData;
                    },
                    function(data) {
                        var series = {
                            name : attrs.seriesName,
                            data : []
                        };
                        if(data != '') {
                            angular.forEach(JSON.parse(data), function(value) {
                                series.data.push(value);
                            });
                            controller.addSeries(series)
                        }
                    }
                );
            }
        }
    });