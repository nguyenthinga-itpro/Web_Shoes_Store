<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Customer Statistics</title>
        <link rel="icon" href="img/core-img/favicon2.ico">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
        <script type="text/javascript" src="http://dev.jquery.com/view/trunk/plugins/validate/jquery.validate.js"></script>
        <link href="css/manager.css" rel="stylesheet" media="all">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <!-- Thêm thư viện Chart.js -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"></script>
    </head>
    <body>
        <div class="page-wrapper">
            <!-- MAIN CONTENT-->
            <div class="main-content">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="overview-wrap">
                                    <h2 class="title-1"><a href="ShopControl?showAllProducts=true">Shop</a> / Customer Statistics</h2>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-3">
                            <h2>Customer Statistics</h2>
                        </div>
                        <div class="col-sm-9 text-right">
                            <form action="CustomerStatisticsServlet" method="get">
                                <a for="datePicker" style="color: black; font-size: 16px">Choose date:</a>
                                <input value="<%= request.getAttribute("selectedDate")%>" type="date" style="color: black; font-size: 16px " id="datePicker" name="selectedDate">
                                <button type="submit" class="btn btn-primary" style="color: black; font-size: 16px ">Show Statistics</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="col-ms-8">
                    <!-- Thẻ canvas để vẽ biểu đồ -->
                    <canvas id="customerChart"></canvas>
                </div>
                <div class="col-ms-4">
                    <!-- Thẻ canvas để vẽ biểu đồ -->
                    <canvas id="customerChart1"></canvas> 
                </div>
            </div>
        </div>

        <script>
            // Hàm này sẽ được gọi sau khi trang được tải hoàn thành
            document.addEventListener("DOMContentLoaded", function () {
                // Lấy thẻ canvas để vẽ biểu đồ
                var ctx = document.getElementById('customerChart').getContext('2d');

                // Dữ liệu biểu đồ sẽ được cung cấp từ servlet
                // Trong ví dụ này, chúng ta sẽ giả định dữ liệu đã được lấy từ servlet và lưu trữ trong một biến data
                var data = {
                    labels: ['Day', 'Month', 'Year'],
                    datasets: [{
                            label: 'Total Customers',
                            data: [<%= request.getAttribute("totalCustomersToday")%>,
            <%= request.getAttribute("totalCustomersThisMonth") %>,
            <%= request.getAttribute("totalCustomersThisYear") %>],
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255, 99, 132, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)'
                            ],
                            borderWidth: 1
                        }, {
                            label: 'New Customers',
                            data: [<%= request.getAttribute("newCustomersToday") %>,
            <%= request.getAttribute("newCustomersThisMonth") %>,
            <%= request.getAttribute("newCustomersThisYear") %>],
                            backgroundColor: [
                                'rgba(153, 102, 255, 0.2)',
                                'rgba(255, 159, 64, 0.2)',
                                'rgba(75, 192, 192, 0.2)'
                            ],
                            borderColor: [
                                'rgba(153, 102, 255, 1)',
                                'rgba(255, 159, 64, 1)',
                                'rgba(75, 192, 192, 1)'
                            ],
                            borderWidth: 1
                        }]
                };

                // Khởi tạo biểu đồ
                var customerChart = new Chart(ctx, {
                    type: 'bar',
                    data: data,
                    options: {
                        scales: {
                            yAxes: [{
                                    ticks: {
                                        beginAtZero: true
                                    }
                                }]
                        }
                    }
                });
            });
            // Hàm này sẽ được gọi sau khi trang được tải hoàn thành
            document.addEventListener("DOMContentLoaded", function () {
                // Lấy thẻ canvas để vẽ biểu đồ
                var ctx = document.getElementById('customerChart').getContext('2d');

                // Dữ liệu biểu đồ sẽ được cung cấp từ servlet
                // Trong ví dụ này, chúng ta sẽ giả định dữ liệu đã được lấy từ servlet và lưu trữ trong một biến data
                var data = {
                    labels: ['Day', 'Month', 'Year'],
                    datasets: [{
                            label: 'Total Customers',
                            data: [<%= request.getAttribute("totalCustomersToday")%>,
            <%= request.getAttribute("totalCustomersThisMonth") %>,
            <%= request.getAttribute("totalCustomersThisYear") %>],
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255, 99, 132, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)'
                            ],
                            borderWidth: 1
                        }, {
                            label: 'New Customers',
                            data: [<%= request.getAttribute("newCustomersToday") %>,
            <%= request.getAttribute("newCustomersThisMonth") %>,
            <%= request.getAttribute("newCustomersThisYear") %>],
                            backgroundColor: [
                                'rgba(153, 102, 255, 0.2)',
                                'rgba(255, 159, 64, 0.2)',
                                'rgba(75, 192, 192, 0.2)'
                            ],
                            borderColor: [
                                'rgba(153, 102, 255, 1)',
                                'rgba(255, 159, 64, 1)',
                                'rgba(75, 192, 192, 1)'
                            ],
                            borderWidth: 1
                        }]
                };

                // Khởi tạo biểu đồ
                var customerChart1 = new Chart(ctx, {
                    type: 'bar',
                    data: data,
                    options: {
                        scales: {
                            yAxes: [{
                                    ticks: {
                                        beginAtZero: true
                                    }
                                }]
                        }
                    }
                });
            });
        </script>
    </body>
</html>