<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Shop | E-Shopper</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/prettyPhoto.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/price-range.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/js/html5shiv.js"></script>
    <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>
	<header id="header"><!--header-->
		<div class="header_top"><!--header_top-->
			<div class="container">
				<div class="row">
					<div class="col-sm-6 ">
						<div class="contactinfo">
							<ul class="nav nav-pills">
								<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-phone"></i> +2 95 01 88 821</a></li>
								<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-envelope"></i> info@domain.com</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="social-icons pull-right">
							<ul class="nav navbar-nav">
								<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-facebook"></i></a></li>
								<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-twitter"></i></a></li>
								<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-linkedin"></i></a></li>
								<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-dribbble"></i></a></li>
								<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-google-plus"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header_top-->
		
		<div class="header-middle"><!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-sm-4">
						<div class="logo pull-left">
							<a href="${pageContext.request.contextPath}/index.html"><img src="${pageContext.request.contextPath}/images/home/logo.png" alt="" /></a>
						</div>
						<div class="btn-group pull-right">
							<div class="btn-group">
								<button type="button" class="btn btn-default dropdown-toggle usa" data-toggle="dropdown">
									USA
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li><a href="${pageContext.request.contextPath}/">Canada</a></li>
									<li><a href="${pageContext.request.contextPath}/">UK</a></li>
								</ul>
							</div>
							
							<div class="btn-group">
								<button type="button" class="btn btn-default dropdown-toggle usa" data-toggle="dropdown">
									DOLLAR
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li><a href="${pageContext.request.contextPath}/">Canadian Dollar</a></li>
									<li><a href="${pageContext.request.contextPath}/">Pound</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="shop-menu pull-right">
							<ul class="nav navbar-nav">
								<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-user"></i> Account</a></li>
								<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-star"></i> Wishlist</a></li>
								<li><a href="${pageContext.request.contextPath}/checkout.html"><i class="fa fa-crosshairs"></i> Checkout</a></li>
								<li><a href="${pageContext.request.contextPath}/cart.html"><i class="fa fa-shopping-cart"></i> Cart</a></li>
								<li><a href="${pageContext.request.contextPath}/authen"><i class="fa fa-lock"></i> Login</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-middle-->
	
		<div class="header-bottom"><!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="col-sm-9">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
						</div>
						<div class="mainmenu pull-left">
							<ul class="nav navbar-nav collapse navbar-collapse">
								<li><a href="${pageContext.request.contextPath}/index.html">Home</a></li>
								<li class="dropdown"><a href="${pageContext.request.contextPath}/#" class="active">Shop<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a href="${pageContext.request.contextPath}/shop.html" class="active">Products</a></li>
										<li><a href="${pageContext.request.contextPath}/product-details.html">Product Details</a></li> 
										<li><a href="${pageContext.request.contextPath}/checkout.html">Checkout</a></li> 
										<li><a href="${pageContext.request.contextPath}/cart.html">Cart</a></li> 
										<li><a href="${pageContext.request.contextPath}/login.html">Login</a></li> 
                                    </ul>
                                </li> 
								<li class="dropdown"><a href="${pageContext.request.contextPath}/#">Blog<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a href="${pageContext.request.contextPath}/blog.html">Blog List</a></li>
										<li><a href="${pageContext.request.contextPath}/blog-single.html">Blog Single</a></li>
                                    </ul>
                                </li> 
								<li><a href="${pageContext.request.contextPath}/404.html">404</a></li>
								<li><a href="${pageContext.request.contextPath}/contact-us.html">Contact</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="search_box pull-right">
							<input type="text" placeholder="Search"/>
						</div>
					</div>
				</div>
				</div>
			</div>
	</header>
	
	<section id="advertisement">
		<div class="container">
			<img src="${pageContext.request.contextPath}/images/shop/advertisement.jpg" alt="" />
		</div>
	</section>
	
	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="left-sidebar">
						<h2>Category</h2>
						<div class="panel-group category-products" id="accordian"><!--category-productsr-->
							<c:forEach items="${categories}" var="category">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#accordian" href="#category-${category.categoryID}">
												<span class="badge pull-right"><i class="fa fa-plus"></i></span>
												${category.categoryName}
											</a>
										</h4>
									</div>
									<div id="category-${category.categoryID}" class="panel-collapse collapse">
										<div class="panel-body">
											<ul>
												<li><a href="${pageContext.request.contextPath}/product?category=${category.categoryID}">${category.categoryName}</a></li>
												<!-- Nếu bạn có subcategories, bạn có thể thêm chúng ở đây -->
											</ul>
										</div>
									</div>
								</div>
							</c:forEach>
						</div><!--/category-productsr-->
					
						<div class="brands_products"><!--brands_products-->
							<h2>Brands</h2>
							<div class="brands-name">
								<ul class="nav nav-pills nav-stacked">
									<li><a href="${pageContext.request.contextPath}/"> <span class="pull-right">(50)</span>Acne</a></li>
									<li><a href="${pageContext.request.contextPath}/"> <span class="pull-right">(56)</span>Grüne Erde</a></li>
									<li><a href="${pageContext.request.contextPath}/"> <span class="pull-right">(27)</span>Albiro</a></li>
									<li><a href="${pageContext.request.contextPath}/"> <span class="pull-right">(32)</span>Ronhill</a></li>
									<li><a href="${pageContext.request.contextPath}/"> <span class="pull-right">(5)</span>Oddmolly</a></li>
									<li><a href="${pageContext.request.contextPath}/"> <span class="pull-right">(9)</span>Boudestijn</a></li>
									<li><a href="${pageContext.request.contextPath}/"> <span class="pull-right">(4)</span>Rösch creative culture</a></li>
								</ul>
							</div>
						</div><!--/brands_products-->
						
						<div class="price-range"><!--price-range-->
							<h2>Price Range</h2>
							<div class="well">
								 <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="600" data-slider-step="5" data-slider-value="[250,450]" id="sl2" ><br />
								 <b>$ 0</b> <b class="pull-right">$ 600</b>
							</div>
						</div><!--/price-range-->
						
						<div class="shipping text-center"><!--shipping-->
							<img src="${pageContext.request.contextPath}/images/home/shipping.jpg" alt="" />
						</div><!--/shipping-->
						
					</div>
				</div>
				
				<div class="col-sm-9 padding-right">
					<div class="features_items"><!--features_items-->
                        <h2 class="title text-center">Features Items</h2>
                        
                        <c:forEach items="${featuredProducts}" var="product">
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <img src="${product.productImage}" alt="" />
                                            <h2>$${product.price}</h2>
                                            <p>${product.productName}</p>
                                            <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                        </div>
                                        <div class="product-overlay">
                                            <div class="overlay-content">
                                                <h2>$${product.price}</h2>
                                                <p>${product.productName}</p>
                                                <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="choose">
                                        <ul class="nav nav-pills nav-justified">
                                            <li><a href="#"><i class="fa fa-plus-square"></i>Add to wishlist</a></li>
                                            <li><a href="#"><i class="fa fa-plus-square"></i>Add to compare</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        
                    </div><!--features_items-->
                    <div class="category-tab"><!--category-tab-->
                        <div class="col-sm-12">
                            <h2 class="title text-center">All Products</h2>
                        </div>
                        <div class="tab-content">
                            <div class="tab-pane fade active in" id="all" >
                                <c:forEach items="${productList}" var="product">
                                    <div class="col-sm-4">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <img src="${product.productImage}" alt="" />
                                                    <h2>$${product.price}</h2>
                                                    <p>${product.productName}</p>
                                                    <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div><!--/category-tab-->

                    <div class="text-center"></div>
                        <ul class="pagination">
                            <c:if test="${currentPage != 1}">
                                <li><a href="${pageContext.request.contextPath}/home?page=${currentPage - 1}">&laquo;</a></li>
                            </c:if>

                            <c:forEach begin="1" end="${noOfPages}" var="i">
                                <c:choose>
                                    <c:when test="${currentPage eq i}">
                                        <li class="active"><a href="#">${i}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="${pageContext.request.contextPath}/home?page=${i}">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <c:if test="${currentPage lt noOfPages}">
                                <li><a href="${pageContext.request.contextPath}/home?page=${currentPage + 1}">&raquo;</a></li>
                            </c:if>
                        </ul>
                    </div>
				</div>
			</div>
		</div>
	</section>
	
	<footer id="footer"><!--Footer-->
		<div class="footer-top">
			<div class="container">
				<div class="row">
					<div class="col-sm-2">
						<div class="companyinfo">
							<h2><span>e</span>-shopper</h2>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,sed do eiusmod tempor</p>
						</div>
					</div>
					<div class="col-sm-7">
						<div class="col-sm-3">
							<div class="video-gallery text-center">
								<a href="${pageContext.request.contextPath}/#">
									<div class="iframe-img">
										<img src="${pageContext.request.contextPath}/images/home/iframe1.png" alt="" />
									</div>
									<div class="overlay-icon">
										<i class="fa fa-play-circle-o"></i>
									</div>
								</a>
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="video-gallery text-center">
								<a href="${pageContext.request.contextPath}/#">
									<div class="iframe-img">
										<img src="${pageContext.request.contextPath}/images/home/iframe2.png" alt="" />
									</div>
									<div class="overlay-icon">
										<i class="fa fa-play-circle-o"></i>
									</div>
								</a>
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="video-gallery text-center">
								<a href="${pageContext.request.contextPath}/#">
									<div class="iframe-img">
										<img src="${pageContext.request.contextPath}/images/home/iframe3.png" alt="" />
									</div>
									<div class="overlay-icon">
										<i class="fa fa-play-circle-o"></i>
									</div>
								</a>
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="video-gallery text-center">
								<a href="${pageContext.request.contextPath}/#">
									<div class="iframe-img">
										<img src="${pageContext.request.contextPath}/images/home/iframe4.png" alt="" />
									</div>
									<div class="overlay-icon">
										<i class="fa fa-play-circle-o"></i>
									</div>
								</a>
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="address">
							<img src="${pageContext.request.contextPath}/images/home/map.png" alt="" />
							<p>505 S Atlantic Ave Virginia Beach, VA(Virginia)</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="footer-widget">
			<div class="container">
				<div class="row">
					<div class="col-sm-2">
						<div class="single-widget">
							<h2>Service</h2>
							<ul class="nav nav-pills nav-stacked">
								<li><a href="${pageContext.request.contextPath}/">Online Help</a></li>
								<li><a href="${pageContext.request.contextPath}/">Contact Us</a></li>
								<li><a href="${pageContext.request.contextPath}/">Order Status</a></li>
								<li><a href="${pageContext.request.contextPath}/">Change Location</a></li>
								<li><a href="${pageContext.request.contextPath}/">FAQ’s</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="single-widget">
							<h2>Quock Shop</h2>
							<ul class="nav nav-pills nav-stacked">
								<li><a href="${pageContext.request.contextPath}/">T-Shirt</a></li>
								<li><a href="${pageContext.request.contextPath}/">Mens</a></li>
								<li><a href="${pageContext.request.contextPath}/">Womens</a></li>
								<li><a href="${pageContext.request.contextPath}/">Gift Cards</a></li>
								<li><a href="${pageContext.request.contextPath}/">Shoes</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="single-widget">
							<h2>Policies</h2>
							<ul class="nav nav-pills nav-stacked">
								<li><a href="${pageContext.request.contextPath}/">Terms of Use</a></li>
								<li><a href="${pageContext.request.contextPath}/">Privecy Policy</a></li>
								<li><a href="${pageContext.request.contextPath}/">Refund Policy</a></li>
								<li><a href="${pageContext.request.contextPath}/">Billing System</a></li>
								<li><a href="${pageContext.request.contextPath}/">Ticket System</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="single-widget">
							<h2>About Shopper</h2>
							<ul class="nav nav-pills nav-stacked">
								<li><a href="${pageContext.request.contextPath}/">Company Information</a></li>
								<li><a href="${pageContext.request.contextPath}/">Careers</a></li>
								<li><a href="${pageContext.request.contextPath}/">Store Location</a></li>
								<li><a href="${pageContext.request.contextPath}/">Affillate Program</a></li>
								<li><a href="${pageContext.request.contextPath}/">Copyright</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-3 col-sm-offset-1">
						<div class="single-widget">
							<h2>About Shopper</h2>
							<form action="#" class="searchform">
								<input type="text" placeholder="Your email address" />
								<button type="submit" class="btn btn-default"><i class="fa fa-arrow-circle-o-right"></i></button>
								<p>Get the most recent updates from <br />our site and be updated your self...</p>
							</form>
						</div>
					</div>
					
				</div>
			</div>
		</div>
		
		<div class="footer-bottom">
			<div class="container">
				<div class="row">
					<p class="pull-left">Copyright © 2013 E-Shopper. All rights reserved.</p>
					<p class="pull-right">Designed by <span><a target="_blank" href="${pageContext.request.contextPath}/http://www.themeum.com">Themeum</a></span></p>
				</div>
			</div>
		</div>
		
	</footer><!--/Footer-->
	

  
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/js/price-range.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.scrollUp.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.prettyPhoto.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>