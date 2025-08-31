<%-- 
    Document   : index.jsp
    Created on : Mar 19, 2024, 8:42:03 PM
    Author     : Nguyen Thi Nga - CS171351
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>HomePage</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            /* CSS cho Navbar */
            .navbar {
                overflow: hidden;
                padding: 10px;
                color: white;
            }

            .carousel-item img {
                width: 100%;
                height: 400px; /* Chiều cao ảnh sau khi cắt */
                object-fit: cover; /* Đảm bảo ảnh không bị biến dạng */
                padding-left: 30px;
                padding-right: 30px;
            }
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }
            .container {
                max-width: 800px;
                margin: 0 auto;
                padding: 20px;
            }
            h1 {
                text-align: center;
            }
            ul {
                list-style-type: none;
                padding: 0;
            }
            li {
                margin-bottom: 10px;
            }
            .size-chart {
                display: flex;
                justify-content: space-between;
                margin-top: 20px;
            }
            .size-chart div {
                flex-basis: 30%;
                padding: 10px;
                border: 1px solid #ccc;
                text-align: center;
            }
            /* CSS cho nội dung */
            .content {
                padding: 20px;
                margin: 20px;
            }

            /* CSS cho footer */
            .footer {
                background-color: #333;
                color: white;
                text-align: center;
                padding: 20px;
            }

            /* CSS cho ô vuông */
            .square-box {
                background-color: #f1f1f1;
                padding: 20px;
                margin-bottom: 20px;
            }
            .hidden {
                display: none;
            }
        </style>
    </head>
    <body>
        <!-- Navbar -->
        <div class="navbar" style="display: flex; align-items: center; justify-content: space-between; background-color: #333; padding: 10px; margin-bottom: 20px">
            <div class="name">
                <h1 style="font-size: 36px; margin-right: 30px;"><span style="color: #ffc107; text-align: center"><b>KIA </b></span>SHOP</h1>
            </div>
            <div class="logo">
                <img src="img/core-img/logo3.png" style="width: 130px; height: 70px; margin-left: 30px;">
            </div>
            <div class="menu">
                <a href="Login.jsp" style="color: white; text-decoration: none; font-weight: bold; margin-right: 20px;">Login</a>
                <a href="Signup.jsp" style="color: white; text-decoration: none; font-weight: bold; margin-right: 20px;">Register</a>
                <a href="ShopControl?showAllProducts=true" style="color: white; text-decoration: none; font-weight: bold; margin-right: 20px;">Shop</a>
            </div>
        </div>

        <!-- Carousel -->
        <div id="demo" class="carousel slide" data-bs-ride="carousel" data-bs-interval="3000">
            <!-- Các nút điều khiển -->
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
                <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
                <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
            </div>

            <!-- Ảnh trong slide -->
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="https://images.vans.com/is/image/VansBrand/SP24%20OLD%20MEETS%20KNU%20%2D%20DESKTOP%20HERO%201440x600px%2D1?$fullres$" alt="Image 1" class="d-block" style="width:100%; filter: grayscale(100%);">
                </div>
                <div class="carousel-item">
                    <img src="https://dosi-in.com/images/news_content/18411/2020/11/26/lieu-se-co-su-ket-hop-giua-nike-va-adidas-trong-tuong-lai-khong-xa_2020_11_26_0.jpg" alt="Image 2" class="d-block" style="width:100%; filter: grayscale(100%);">
                </div>
                <div class="carousel-item">
                    <img src="https://cdn.theathletic.com/app/uploads/2023/05/29121940/0525_ConverseTimeline-scaled.jpg" alt="Image 3" class="d-block" style="width:100%; filter: grayscale(100%);">
                </div>
            </div>

            <!-- Nút điều khiển -->
            <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
                <span class="carousel-control-next-icon"></span>
            </button>
        </div>
        <!-- Grid system -->
        <div class="container-fluid mt-3">
            <div class="row">
                <div class="col-md-6">
                    <div class="square-box">
                        <div id="Demo1" class="carousel slide" data-bs-ride="carousel" data-bs-interval="3000">
                            <!-- Các nút điều khiển -->
                            <div class="carousel-indicators">
                                <button type="button" data-bs-target="#Demo1" data-bs-slide-to="0" class="active"></button>
                                <button type="button" data-bs-target="#Demo1" data-bs-slide-to="1"></button>
                                <button type="button" data-bs-target="#Demo1" data-bs-slide-to="2"></button>
                                <button type="button" data-bs-target="#Demo1" data-bs-slide-to="3"></button>
                            </div>

                            <!-- Ảnh trong slide -->
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img src="https://www.inspireuplift.com/resizer/?image=https://cdn.inspireuplift.com/uploads/images/seller_products/1678963134_a11-01.png&width=600&height=600&quality=90&format=auto&fit=pad" alt="Image 1" class="d-block" style="width:100%">
                                </div>
                                <div class="carousel-item">
                                    <img src="https://sneakerdaily.vn/wp-content/uploads/2022/08/Logo-adidas-1.jpg.webp" alt="Image 2" class="d-block" style="width:100%">
                                </div>
                                <div class="carousel-item">
                                    <img src="http://alovesinhgiay.com/upload/images/nike-quacacnam(1).jpg" alt="Image 3" class="d-block" style="width:100%">
                                </div>
                                <div class="carousel-item">
                                    <img src="https://i.pinimg.com/1200x/97/45/56/9745562c95c55432c1e2efde6ab61555.jpg" alt="Image 3" class="d-block" style="width:100%">
                                </div>
                            </div>

                            <!-- Nút điều khiển -->
                            <button class="carousel-control-prev" type="button" data-bs-target="#Demo1" data-bs-slide="prev">
                                <span class="carousel-control-prev-icon"></span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#Demo1" data-bs-slide="next">
                                <span class="carousel-control-next-icon"></span>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="square-box">
                        <div style="background-color: #ffc107; color: white; text-align: center">
                            <h2>Shoe Size Guide</h2>
                        </div>
                        <p>Choosing the right shoe size is important for comfort and fit. Use the guide below to find your perfect size.</p>
                        <ul>
                            <li><strong>Step 1:</strong> Measure your foot length in centimeters or inches.</li>
                            <li><strong>Step 2:</strong> Refer to the size chart below to find your corresponding shoe size.</li>
                        </ul>

                        <div class="size-chart">
                            <div>
                                <h2>US Size</h2>
                                <p>6</p>
                                <p>7</p>
                                <p>8</p>
                                <p>9</p>
                                <p>10</p>
                            </div>
                            <div>
                                <h2>EU Size</h2>
                                <p>39</p>
                                <p>40</p>
                                <p>41</p>
                                <p>42</p>
                                <p>43</p>
                            </div>
                            <div>
                                <h2>UK Size</h2>
                                <p>5.5</p>
                                <p>6.5</p>
                                <p>7.5</p>
                                <p>8.5</p>
                                <p>9.5</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6" id="content">
                    <div class="square-box">
                        <div style="background-color: #ffc107; color: white; text-align: center">
                            <h2>Return policy - Instructions and requirements</h2>
                        </div>
                        <p><strong>1. Return Policy:</strong></p>
                        <p>We want you to be completely satisfied when shopping at KIA Shop. If you are not satisfied for any reason, we offer an easy exchange and refund policy within 5 - 7 days of receiving your order.</p>
                        <p><strong>2. Refund conditions:</strong></p>
                        <ul>
                            <li>Goods must be intact, unused and with tags intact.</li>
                            <li>Change requests must be made within 3 days of receipt of order.</li>
                            <li>Goods marked as final cannot be accepted for payment or fulfilled.</li>
                            <li>Returns or refunds are not accepted on items that are personalized or made to special requests unless there is an error or omission on our part.</li>
                            <li>Proof of purchase is required for all returns or refunds.</li>
                        </ul>
                        <div class="row additional-content hidden">
                            <div class="col-md-6">
                                <div class="square-box">
                                    <p><strong>3. Return Process:</strong></p>
                                    <ul>
                                        <li>Contact Customer Service to initiate the return or refund process. Provide the order number and reason for return.</li>
                                        <li>Securely package the product in its original packaging, along with the original invoice or proof of purchase.</li>
                                        <li>Ship the product back to us using a trackable shipping method. Customers are responsible for return shipping costs unless there is an error or defect from our end.</li>
                                        <li>Upon receiving the returned product and confirming its condition, we will process the refund or exchange within [number] working days.</li>
                                    </ul>
                                    <p><strong>4. Refund/Exchange Options:</strong></p>
                                    <ul>
                                        <li>Refund: The refunded amount will be transferred to the original payment method used for the purchase.</li>
                                        <li>Exchange: If you wish to exchange the product for a different size, color, or style, please specify your preference when contacting Customer Service. Exchange is subject to product availability.</li>
                                    </ul>
                                    <p><strong>5. Contact:</strong></p>
                                    <p>If you have any questions or concerns regarding our return policy, feel free to contact our Customer Service team. We are here to assist you and ensure that your shopping experience with us is as smooth and comfortable as possible.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style="display: flex; justify-content: center;">
                        <button id="load-more-btn" class="btn btn-warning" style="margin-bottom: 10px; color: white;">Load More</button>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="square-box">
                        It's great that you experience our online shopping site! We hope you find suitable products and have a pleasant shopping experience. Enjoy discovering new products and don't hesitate to contact us if you need any assistance. We are always here to serve you and hope you have a wonderful day!
                        <img src="https://www.essentialcrafts.co.uk/image/cache/data/Category%20images/Peel%20Offs/thank-you-peel-offs-600x315.png" alt="Image 3" class="d-block" style="width:100%">
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <div class="footer">
            <p>&copy; 2024 - KIA SHOP</p>
        </div>
        <!-- Thêm thư viện JavaScript của jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

        <script>
            $(document).ready(function () {
                var clickCount = 0;
                // Sự kiện click vào nút "Load More"
                $('#load-more-btn').click(function () {
                    clickCount++;
                    if (clickCount % 2 === 1) {
                        // Hiển thị nội dung ẩn
                        $('.additional-content').removeClass('hidden');
                        // Ẩn nút "Load More" sau khi được nhấp
//                $(this).text('Hide Content');
                    } else {
                        // Ẩn nội dung khi nút được nhấp lần thứ hai
                        $('.additional-content').addClass('hidden');
                        // Hiển thị lại nút "Load More"
                        $(this).text('Load More');
                    }
                });
            });
        </script>

    </body>
</html>
