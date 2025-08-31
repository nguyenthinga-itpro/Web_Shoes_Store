<%@page import="entity.Cart"%>
<%@page import="java.util.List"%>
<%@page import="entity.Product"%>
<%@page import="dao.DAO"%>
<%@page import="entity.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="main-content-wrapper d-flex clearfix">
    <!-- Mobile Nav (max width 767px)-->
    <div class="mobile-nav">
        <!-- Navbar Brand -->
        <div class="amado-navbar-brand">
            <a href="Home.jsp"><img src="img/core-img/logo3.png" alt=""></a>
        </div>
        <!-- Navbar Toggler -->
        <div class="amado-navbar-toggler">
            <span></span><span></span><span></span>
        </div>
    </div>

    <!-- Header Area Start -->
    <header class="header-area clearfix">
        <!-- Close Icon -->
        <div class="nav-close">
            <i class="fa fa-close" aria-hidden="true"></i>
        </div>
        <!-- Logo -->
        <div class="logo">
            <a href="index.html"><img src="img/core-img/logo3.png" alt=""></a>
        </div>
        <!-- Amado Nav -->
        <nav class="amado-nav">
            <ul>
                <!--<li><a href="HomeControl">Home</a></li>-->
                <li><a href="ShopControl?showAllProducts=true">Shop</a></li>
                    <c:if test="${sessionScope.acc.role == 1}">
                    <li><a href="ManagerControl"> Manage Product</a></li>
                    <li><a href="ManagerCustomerControl"> Manage Customer</a></li>
                    <li><a href="ManageOrderControl"> Manage Order</a></li>
                    <li><a href="DashBord.jsp">Statistical Chart</a></li>
                    </c:if>
                <!--<li><a href="ProductDetail.jsp">Product</a></li>-->
                 <c:if test="${sessionScope.acc.role == 0 }">
                    <li><a href="OrderHistoryControl">Order History</a></li>
                    </c:if>
                <!--<li><a href="CheckOut.jsp">Checkout</a></li>-->
             


                <c:if test="${sessionScope.acc == null}">
                    <li><a href="Login.jsp">Login</a></li>
                    <li><a href="Signup.jsp">Register</a></li>
                    <li><a href="index.jsp">Home</a></li>
                    </c:if>
                    <c:if test="${sessionScope.acc != null}">
                    <li><a href="profile">Profile</a></li>
                    <li><a href="logout">Logout</a></li>
                    </c:if>
            </ul>
        </nav>
        <!-- Button Group -->
        <%
            DAO dao = new DAO();
            Product pNew = dao.getNewestProduct();
            int idNew = pNew.getId();
            int idBest = dao.getBestSeller();
            Cart c = (Cart) session.getAttribute("cart");
            int count = 0;
            if(c!=null){
                count = c.getItems().size();
            }
        %>
        <div class="amado-btn-group mt-30 mb-100">
            <a href="productDetail?productID=<%= idBest%>" class="btn amado-btn mb-15">Bestseller</a>
            <a href="productDetail?productID=<%= idNew%>" class="btn amado-btn active">Newest Product</a>
        </div>
        <!-- Cart Menu -->
        <div class="cart-fav-search mb-100">
              <c:if test="${sessionScope.acc.role == 0 }">
            <a href="cart" class="cart-nav"><img src="img/core-img/cart.png" alt=""> Cart <span>(<%= count%>)</span></a>
             </c:if>
            <a href="#" class="fav-nav"><img src="img/core-img/favorites.png" alt=""> Favourite</a>
        </div>
        <!-- Social Button -->
        <div class="social-info d-flex justify-content-between">
            <a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
            <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
            <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
            <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
        </div>
    </header>