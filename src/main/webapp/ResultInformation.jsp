<%-- 
    Document   : ResultInformation
    Created on : 10-set-2018, 17.02.26
    Author     : raffa
--%>

<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.gameseeker.mongoDB.Result"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
        <link rel="icon" type="image/png" href="../assets/img/favicon.png">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

        <title>Profile Page - Material Kit by Creative Tim</title>

        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />

        <!--     Fonts and icons     -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />

        <!-- CSS Files -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
        <link href="assets/css/material-kit.css" rel="stylesheet"/>

    </head>

    <body class="profile-page">
        <nav class="navbar navbar-transparent navbar-fixed-top navbar-color-on-scroll">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-example">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.html">GS Project</a>
                </div>

                <div class="collapse navbar-collapse" id="navigation-example">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="components-documentation.html" target="_blank">
                                Components
                            </a>
                        </li>
                        <li>
                            <a href="http://demos.creative-tim.com/material-kit-pro/presentation.html?ref=utp-freebie" target="_blank">
                                <i class="material-icons">unarchive</i> Upgrade to PRO
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
                        <%
                            Result resultInformation = (Result) request.getAttribute("id");

                        %>
        <div class="wrapper">
            <div class="header header-filter" style="background-image: url('Immagini/gaming.jpg');"></div>

            <div class="main main-raised">
                <div class="profile-content">
                    <div class="container">
                        <div class="row">
                            <div class="profile">
                                <div class="avatar">
                                    <img src="assets/img/christian.jpg" alt="Circle Image" class="img-circle img-responsive img-raised">
                                </div>
                                <div class="name">
                                    <h3 class="title"><%= resultInformation.getTitle() %></h3>
                                </div>
                            </div>
                        </div>



                        <div class="row">
                            <div class="description text-center">
                                <% PrintWriter outing = response.getWriter();
                                 
                                    out.println("<div class='col-md-6'>");
                                    out.println("<img src=" + resultInformation.getImgUrl() + " class='img-rounded' width='250' height='350' style= 'margin-bottom: 50px;' />");
                                    out.println("</div>");

                                    out.println("<div class='col-md-6'>");
                                    
                                    if(resultInformation.getPlattformTitle()!=null)
                                    out.println("<b>Platform: </b>" + resultInformation.getPlattformTitle());
                                    out.println("<br>");                                
                                    out.println("<b>Price: </b> " + resultInformation.getPrice());
                                    out.println("<br>");
                                    if(resultInformation.getFeedback()!=0)
                                    out.println("<b>Feedback: </b>" + resultInformation.getFeedback());
                                    out.println("<br>");
                                    if(resultInformation.getReleaseDate()!=null)
                                    out.println("<b>Release date: </b>" + resultInformation.getReleaseDate());
                                    out.println("<br>");
                                    if(resultInformation.getRequisitiMinimi()!=null)
                                    out.println("<b>Requisiti minimi: </b>" + resultInformation.getRequisitiMinimi());
                                    out.println("<br>");
                                    if(resultInformation.getRequisitiConsigliati()!=null)
                                    out.println("<b>Requisiti consigliati: </b>" + resultInformation.getRequisitiConsigliati());
                                    out.println("<br>");
                                    out.println("<br>");
                                    out.println("<br>");
                                    out.println("<br>");
                                    out.println("<br>");
                                    out.println("</div>");
                                    

                                %>
                                <!-- End Profile Tabs -->
                            </div>
                        </div>

                    </div>
                </div>
            </div>

        </div>

        <footer class="footer">
            <div class="container">
                <nav class="pull-left">
                    <ul>
                        <li>
                            <a href="http://www.creative-tim.com">
                                Creative Tim
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
                    &copy; 2016, made with <i class="fa fa-heart heart"></i> by Creative Tim
                </div>
            </div>
        </footer>


    </body>
    <!--   Core JS Files   -->
    <script src="assets/js/jquery.min.js" type="text/javascript"></script>
    <script src="assets/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="assets/js/material.min.js"></script>

    <!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
    <script src="assets/js/nouislider.min.js" type="text/javascript"></script>

    <!--  Plugin for the Datepicker, full documentation here: http://www.eyecon.ro/bootstrap-datepicker/ -->
    <script src="assets/js/bootstrap-datepicker.js" type="text/javascript"></script>

    <!-- Control Center for Material Kit: activating the ripples, parallax effects, scripts from the example pages etc -->
    <script src="assets/js/material-kit.js" type="text/javascript"></script>

</html>
