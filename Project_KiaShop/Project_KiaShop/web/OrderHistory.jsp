

<%@page import="entity.Cart"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>JSP Page</title>-->
        <meta charset="UTF-8">
        <meta name="description" content="">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <!-- Title  -->
        <title>Order History</title>

        <!-- Favicon  -->
        <link rel="icon" href="img/core-img/favicon2.ico">

        <!-- Core Style CSS -->
        <link href="http://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/core-style.css">
        <!-- <link rel="stylesheet" href="style.css"> -->
        <style>
            thead{
                background-color: #f5f7fa;
            }
            table {
                width: 100%;
            }
        </style>
    </head>
    <body>
        <!-- Search Wrapper Area Start -->
        <jsp:include page="common/search.jsp"></jsp:include>
            <!-- Search Wrapper Area End -->

            <!-- ##### Main Content Wrapper Start ##### -->
        <jsp:include page="common/header.jsp"></jsp:include>
            <!-- Header Area End -->

            <div class="section-padding-100">
                <div class="cart-title mt-50">
                    <h2>Order History</h2>
                </div>

                <div >
                    <table style="width:1100px">
                        <thead style="background-color: #f5f7fa">
                            <tr>
                                <th >Order ID</th>
                                <th >Recipient's Name</th>
                                <th>Order Date</th>
                                <th >Address Receive</th>
                                <th >Phone Number</th>
                                <th >Total Price</th>
                                <th >Status</th>
                                <th style="text-align: center">Detail</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${lstOrder}" var="o">
                            <tr>

                                <td class="id" >
                                    <span>${o.id}</span>
                                </td>
                                <td class="name" >
                                    <span>${o.name}</span>
                                </td>
                                <td class="date" >
                                    <span>${o.date}</span>
                                </td>
                                <td class="address" >
                                    <span>${o.address}</span>
                                </td>
                                <td class="sdt" >
                                    <span>${o.sdt}</span>
                                </td>
                                <td class="totalPrice" >
                                    <span>${o.totalPrice}</span>
                                </td>  
                                <td class="status" >
                                    <span>${o.status}</span>
                                </td>  
                                <td style="text-align: center">
                                    <a href="OrderDetailControl?orderId=${o.id}"><i class="material-icons" data-toggle="tooltip" title="Detail">&#xe24c;</i>
                                </td>
                                <td> 
                                    <c:if test="${o.status eq 'Waiting'}">
                                        <form id="cancelForm${o.id}" action="OrderHistoryControl" method="get">
                                            <input type="hidden" name="cancelID" value="${o.id}">
                                            <input type="hidden" name="status" value="Cancelled">
                                            <a href="javascript:void(0);" class="delete" onclick="confirmCancel(${o.id});">Cancel Order</a>
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- ##### Main Content Wrapper End ##### -->

    <!-- ##### Newsletter Area Start ##### -->

    <!-- ##### Newsletter Area End ##### -->

    <!-- ##### Footer Area Start ##### -->
    <jsp:include page="common/footer.jsp"></jsp:include>
    <!-- ##### Footer Area End ##### -->

    <!-- ##### jQuery (Necessary for All JavaScript Plugins) ##### -->
    <script src="js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="js/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Plugins js -->
    <script src="js/plugins.js"></script>
    <!-- Active js -->
    <script src="js/active.js"></script>
    <script>
                                              function confirmCancel(orderId) {
                                                  // Xác nhận trước khi hủy đơn hàng
                                                  var result = confirm("Are you sure?");
                                                  if (result) {
                                                      // Nhập lý do hủy đơn hàng
                                                      var reason = prompt("Please enter a reason for canceling your order:", "");
                                                      if (reason !== null && reason.trim() !== "") {
                                                          // Tạo một đối tượng XMLHttpRequest để gửi yêu cầu
                                                          var xhr = new XMLHttpRequest();
                                                          // Tạo URL với orderId và reason
                                                          var url = "Cancel?cancelID=" + orderId + "&reason=" + encodeURIComponent(reason);
                                                          // Mở một kết nối GET đến URL đã tạo
                                                          xhr.open("GET", url, true);
                                                          // Đặt sự kiện cho thay đổi trạng thái của yêu cầu
                                                          xhr.onreadystatechange = function () {
                                                              // Kiểm tra xem yêu cầu đã hoàn tất và có mã trạng thái là 200 (OK) không
                                                              if (xhr.readyState == 4 && xhr.status == 200) {
                                                                  // Thông báo rằng đơn hàng đã được hủy
                                                                  alert("The order has been cancelled.");
                                                                  // Tải lại trang để cập nhật dữ liệu
                                                                  location.reload();
                                                                  // Gửi biểu mẫu
                                                                  document.getElementById('cancelForm' + orderId).submit();
                                                              }
                                                          };
                                                          // Gửi yêu cầu
                                                          xhr.send();
                                                      } else {
                                                          // Thông báo nếu không nhập lý do hủy đơn hàng
                                                          alert("Please enter a reason for canceling your order.");
                                                      }
                                                  }
                                              }

    </script>
</body>
</html>
