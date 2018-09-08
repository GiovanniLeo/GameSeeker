<%-- 
    Document   : ResultQuery
    Created on : 7-set-2018, 12.28.13
    Author     : raffa
--%>

<%@page import="java.io.PrintWriter"%>
<%@page import="com.mycompany.gameseeker.mongoDB.Result"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
        <link rel="icon" type="image/png" href="assets/img/favicon.png">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

        <title>Game Seeker</title>

        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />

        <!--     Fonts and icons     -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />

        <!-- CSS Files -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
        <link href="assets/css/material-kit.css" rel="stylesheet" />

        <script src="assets/js/jquery.min.js" type="text/javascript"></script>
        <script src="assets/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="assets/js/material.min.js"></script>

        <!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
        <script src="assets/js/nouislider.min.js" type="text/javascript"></script>

        <!--  Plugin for the Datepicker, full documentation here: http://www.eyecon.ro/bootstrap-datepicker/ -->
        <script src="assets/js/bootstrap-datepicker.js" type="text/javascript"></script>

        <!-- Control Center for Material Kit: activating the ripples, parallax effects, scripts from the example pages etc -->
        <script src="assets/js/material-kit.js" type="text/javascript"></script>
        <script>
            function scrollToAnchor(aid) {
                console.log(aid);
                $('html,body').animate({scrollTop: $(document).height() - $(window).height()}, 900);
            }
            $(windows).load(function () {
                $("#ReserchIconLink").click(function () {
                    scrollToAnchor('#GameSearch');
                });
            })


        </script>

    </head>

    <body class="landing-page">
        <nav class="navbar navbar-transparent navbar-absolute">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-example">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="http://www.creative-tim.com">GSProject</a>
                </div>

                <div class="collapse navbar-collapse" id="navigation-example">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="components-documentation.html" target="_blank">
                                Components
                            </a>
                        </li>
                        <li>
                            <a id="ReserchIconLink">
                                <i class="material-icons">search</i> Ricerca il tuo Gioco
                            </a>
                        </li>
                        <li>
                            <a href="https://twitter.com/CreativeTim" target="_blank" class="btn btn-simple btn-white btn-just-icon">
                                <i class="fa fa-twitter"></i>
                            </a>
                        </li>
                        <li>
                            <a href="https://www.facebook.com/CreativeTim" target="_blank" class="btn btn-simple btn-white btn-just-icon">
                                <i class="fa fa-facebook-square"></i>
                            </a>
                        </li>
                        <li>
                            <a href="https://www.instagram.com/CreativeTimOfficial" target="_blank" class="btn btn-simple btn-white btn-just-icon">
                                <i class="fa fa-instagram"></i>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="wrapper">
            <div class="header header-filter" style="background-image: url('Immagini/gaming.jpg');">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">

                        </div>
                    </div>
                </div>
            </div>
            <%
                ArrayList<Result> resultMatching = (ArrayList<Result>) request.getAttribute("resultMatching");
               
            %>
            <div class="main main-raised">
                <div class="container">
                    <div class="section text-center section-landing">
                        <div class="row">
                            <ul>                               
                                    <% PrintWriter outing = response.getWriter();
                                      for (int i = 0; i < resultMatching.size(); i++) { 
                                           out.println("<h1>Giochi " + resultMatching.get(i).getTitle() + "</h1>");
                           
                                }%>
                            </ul>
                        </div>
                    </div>
                </div>

                <footer class="footer">
                    <div class="container">
                        <nav class="pull-left">
                            <ul>
                                <li>
                                    <a href="http://www.creative-tim.com">
                                        GameSeeker
                                    </a>
                                </li>
                                <li>
                                    <a href="http://presentation.creative-tim.com">
                                        About Us
                                    </a>
                                </li>
                                <li>
                                    <a href="http://blog.creative-tim.com">
                                        Blog
                                    </a>
                                </li>
                                <li>
                                    <a href="http://www.creative-tim.com/license">
                                        Licenses
                                    </a>
                                </li>
                            </ul>
                        </nav>
                        <div class="copyright pull-right">
                            &copy; 2018, made with <i class="fa fa-heart heart"></i> by Ceruso & Leo
                        </div>
                    </div>
                </footer>

            </div>
        </div>
    </body>

    <!--   Core JS Files   -->


</html>
