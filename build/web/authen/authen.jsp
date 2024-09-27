<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Login | E-Shopper</title>
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
                        <div class="col-sm-6">
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
                                    <li><a href="${pageContext.request.contextPath}/login.html" class="active"><i class="fa fa-lock"></i> Login</a></li>
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
                                    <li class="dropdown"><a href="${pageContext.request.contextPath}/#">Shop<i class="fa fa-angle-down"></i></a>
                                        <ul role="menu" class="sub-menu">
                                            <li><a href="${pageContext.request.contextPath}/shop.html">Products</a></li>
                                            <li><a href="${pageContext.request.contextPath}/product-details.html">Product Details</a></li> 
                                            <li><a href="${pageContext.request.contextPath}/checkout.html">Checkout</a></li> 
                                            <li><a href="${pageContext.request.contextPath}/cart.html">Cart</a></li> 
                                            <li><a href="${pageContext.request.contextPath}/login.html" class="active">Login</a></li> 
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
            </div><!--/header-bottom-->
        </header><!--/header-->

        <section id="form"><!--form-->
            <div class="container">
                <div class="row">
                    <div class="col-sm-4 col-sm-offset-1">
                        <div class="login-form"><!--login form-->
                            <h2>Login to your account</h2>
                            <form action="${pageContext.request.contextPath}/authen" method="post">
                                <input type="hidden" name="action" value="login">
                                <input type="text" name="usernameMail" placeholder="Email Address" required/>
                                <input type="password" name="password" placeholder="Password" required/>
                                <span>
                                    <input type="checkbox" class="checkbox"> 
                                    Keep me signed in
                                </span>
                                <button type="submit" class="btn btn-default">Login</button>
                            </form>
                            <c:if test="${not empty errorMessageLogin}">
                                <p style="color: red;">${errorMessageLogin}</p>
                            </c:if>
                        </div><!--/login form-->
                    </div>
                    <div class="col-sm-1">
                        <h2 class="or">OR</h2>
                    </div>
                    <div class="col-sm-4">
                        <div class="signup-form"><!--sign up form-->
                            <h2>New User Signup!</h2>
                            <form action="${pageContext.request.contextPath}/authen" method="post">
                                <input type="hidden" name="action" value="register">
                                <input type="text" name="username" placeholder="Username" required/>
                                <input type="email" name="email" placeholder="Email Address" required/>
                                <input type="password" name="password" placeholder="Password" required/>
                                <button type="submit" class="btn btn-default">Signup</button>
                            </form>
                            <c:if test="${not empty errorMessageRegister}">
                                <p style="color: red;">${errorMessageRegister}</p>
                            </c:if>
                        </div><!--/sign up form-->
                    </div>
                </div>
            </div>
        </section><!--/form-->


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
                        <p class="pull-left">Copyright © 2013 E-SHOPPER Inc. All rights reserved.</p>
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