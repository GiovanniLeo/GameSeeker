<%-- 
    Document   : ResultsQuery
    Created on : 8-set-2018, 10.54.56
    Author     : raffa
--%>

<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.gameseeker.mongoDB.Result"%>
<%@page import="com.mycompany.gameseeker.mongoDB.Result"%>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <!-- CSS Files -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
        <link href="assets/css/material-kit.css" rel="stylesheet"/>
        <style>
            .checked {
                color: orange;
            }
        </style>
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
                    <a class="navbar-brand" href="index.html">
                     <i class="material-icons">home</i>
                     GameSeeker
                    </a>
                </div>

                <div class="collapse navbar-collapse" id="navigation-example">
                    <ul class="nav navbar-nav navbar-right">

                        <li>
                            <form action="MinPriceTest" method="GET" role="form" class="form-horizontal">
                            <div class="form-group">
                    
                          <input type="text" name="titolo" value="" placeholder="Ricerca" class="form-control " style="text-align:center;color:white" type="submit"/>                            
                                                   
                            </div>
                            </form>
                        </li>
                        <!--          <li>
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
                        -->      </ul>
                </div>
            </div>
        </nav>
        <%
            ArrayList<Result> resultMatching = (ArrayList<Result>) request.getAttribute("resultMatching");
            ArrayList<Result> resultIg = (ArrayList<Result>) request.getAttribute("resultIg");
            ArrayList<Result> resultG2a = (ArrayList<Result>) request.getAttribute("resultG2a");
            ArrayList<Result> resultYoutube = (ArrayList<Result>) request.getAttribute("resultYoutube");
            ArrayList<Result> resultEveryEye = (ArrayList<Result>) request.getAttribute("resultEveryEye");
            System.out.println("Init");

        %>
        <div class="wrapper">
            <div class="header header-filter" style="background-image: url('Immagini/gaming.jpg');"></div>

            <div class="main main-raised">
                <div class="profile-content">
                    <div class="container">
                        <div class="row">
                            <div class="profile">
                                <div class="avatar">
                                    <img src="Immagini/Logo.jpg" alt="Circle Image" class="img-circle img-responsive img-raised" style="height:30%;width:30%">
                                </div>
                                <div class="name">
                                    <h3 class="title">Games</h3>

                                </div>
                            </div>
                        </div>
                        <div class="description text-center">
                            <p>GameSeeker è una piattaforma web che ha lo scopo di raccogliere informazioni sui videogame ricercati, attraverso una semplice interfaccia grafica adatta a qualsiasi dispositivo </p>
                        </div>

                        <div class="row">
                            <div class="col-md-6 col-md-offset-3">
                                <div class="profile-tabs">
                                    <div class="nav-align-center">
                                        <ul class="nav nav-pills" role="tablist">
                                            <li class="active">
                                                <a href="#studio" role="tab" data-toggle="tab">
                                                    <i class="material-icons">star</i>
                                                    Best
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#work" role="tab" data-toggle="tab">
                                                    <i class="material-icons">public</i>
                                                    Tutte le fonti
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#shows" role="tab" data-toggle="tab">
                                                    <i class="material-icons">live_tv</i>
                                                    Watch now
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#reviews" role="tab" data-toggle="tab">
                                                    <i class="material-icons">sms</i>
                                                    Reviews
                                                </a>
                                            </li>                                           
                                        </ul>

                                        <div class="tab-content gallery">
                                            <div class="tab-pane active" id="studio">

                                                <%                                                    double half = (resultMatching.size() / 2);
                                                    int rating;
                                                    boolean flag = false;
                                                    boolean one = false;
                                                    if (resultMatching.size() == 1) {
                                                        one = true;
                                                    }

                                                    out.println("<div class='row'>");
                                                    if (!one) {
                                                        out.println("<div class='col-md-6'>");
                                                    } else {
                                                        out.println("<div class='col-md-12'>");
                                                    }
                                                    int i = 0;
                                                    if(resultMatching.get(0)==null)
                                                    {
                                                       out.println("<p style='text-align:center'>Nessun elemento trovato</p>");
                                                    }
                                                   
                                                    else
                                                    {
                                                
                                                    while (i < resultMatching.size()) {
                                                        if (i == half || i == half + 1 && flag == false) {
                                                            out.println("</div>");
                                                            if (!one) {
                                                                out.println("<div class='col-md-6'>");
                                                            } else {
                                                                out.println("<div class='col-md-12'>");
                                                            }
                                                            flag = true;
                                                        }
                                                        rating = resultMatching.get(i).getFeedback();
                                                        System.out.println(resultMatching.get(i).getFeedback());

                                                        out.println("<div class='row'>");
                                                        out.println("<i>" + resultMatching.get(i).getTitle() + "</i>");
                                                        out.println("</div>");
                                                        out.println("<br>");
                                                        out.println("<br>");

                                                        out.println("<form action='InformationResult' method='GET' role='form' class='form-horizontal'>");
                                                        out.println("<input type='hidden' name='id' value='" + resultMatching.get(i).getId() + "'>");
                                                        out.println("<input type='image' src=" + resultMatching.get(i).getImgUrl() + " class='img-rounded' width='250' height='350' />");
                                                        out.println("<br>");
                                                        out.println("<br>");
                                                        out.println("Prezzo: " + resultMatching.get(i).getPrice() + " €");
                                                        out.println("<br>");

                                                        for (int j = 0; j < rating; j++) {
                                                            out.println("<span class='fa fa-star checked'></span>");
                                                        }
                                                        for (int j = rating; j < 5; j++) {
                                                            out.println("<span class='fa fa-star'></span>");
                                                        }
                                                        out.println("</br>");

                                                        out.println("<a href=" + resultMatching.get(i).getLinkRef() + " class='btn btn-default' target='blank'>Buy Now</a>");
                                                        out.println("</form>");
                                                        out.println("<hr>");
                                                        i++;
                                                    }
                                                    }
                                                    out.println("</div>");
                                                    out.println("</div>");

                                                %>

                                            </div>
                                            <div class="tab-pane text-center" id="work">
                                                <%  
                                                    System.out.println("Size Ig " + resultIg.size());
                                                    System.out.println("Size G2a " + resultG2a.size());
                                                    flag = true;
                                                    out.println("<div class='row'>");
                                                    out.println("<div class='col-md-6'>");
                                                    half = (double) resultIg.size() / 2;
                                                    int resto = (resultIg.size() % 2);
                                                    i = 0;
                                                    int k = 0;
                                                    if(resultIg.get(0)==null && resultG2a.get(0)==null)
                                                    {
                                                     //   out.println("<p style='text-align:center'>Nessun elemento trovato</p>");
                                                    }
                                                    if(resultIg.get(0)==null)
                                                    {
                                                        
                                                    }
                                                    else{
                                                    while (i < resultIg.size()) {
                                                        if ((i > half || i == half) && flag) {
                                                            out.println("</div>");
                                                            out.println("<div class='col-md-6'>");
                                                            flag = false;
                                                        }
                                                        rating = resultIg.get(i).getFeedback();

                                                        out.println("<div style='height:35px'>");
                                                        out.println("<i>" + resultIg.get(i).getTitle() + "</i>");
                                                        out.println("</div>");
                                                        out.println("<br>");
                                                        out.println("<br>");

                                                        out.println("<form action='InformationResult' method='GET' role='form' class='form-horizontal'>");
                                                        out.println("<input type='hidden' name='id' value='" + resultIg.get(i).getId() + "'>");
                                                        out.println("<input type='image' src=" + resultIg.get(i).getImgUrl() + " class='img-rounded' width='250' height='350' />");
                                                        out.println("<br>");
                                                        out.println("<br>");
                                                        out.println("Prezzo: " + resultIg.get(i).getPrice() + " €");
                                                        out.println("</br>");
                                                        for (int j = 0; j < rating; j++) {
                                                            out.println("<span class='fa fa-star checked'></span>");
                                                        }
                                                        for (int j = rating; j < 5; j++) {
                                                            out.println("<span class='fa fa-star'></span>");
                                                        }
                                                        out.println("</br>");
                                                        out.println("<a href=" + resultIg.get(i).getLinkRef() + " class='btn btn-default' target='blank'>Buy Now</a>");
                                                        out.println("</form>");
                                                        out.println("<hr>");
                                                        i = i + 1;
                                                    }
                                                   
                                                    
                                                    
                                                    if (resto == 1) {
                                                        if (resultIg.size() == 1 && resultIg.get(0) !=null) {
                                                            out.println("</div>");
                                                            out.println("<div class='col-md-6'>");
                                                        }
                                                        rating = resultG2a.get(0).getFeedback();
                                                       

                                                        out.println("<div style='height:35px'>");
                                                        out.println("<i>" + resultG2a.get(0).getTitle() + "</i>");
                                                        out.println("</div>");
                                                        out.println("<br>");
                                                        out.println("<br>");

                                                        out.println("<form action='InformationResult' method='GET' role='form' class='form-horizontal'>");
                                                        out.println("<input type='hidden' name='id' value='" + resultG2a.get(0).getId() + "'>");
                                                        out.println("<input type='image' src=" + resultG2a.get(0).getImgUrl() + " class='img-rounded' width='250' height='350' />");
                                                        out.println("<br>");
                                                        out.println("<br>");
                                                        out.println("Prezzo: " + resultG2a.get(0).getPrice()+ " €");
                                                        out.println("<br>");
                                                        for (int j = 0; j < rating; j++) {
                                                            out.println("<span class='fa fa-star checked'></span>");
                                                        }
                                                        for (int j = rating; j < 5; j++) {
                                                            out.println("<span class='fa fa-star'></span>");
                                                        }
                                                        out.println("</br>");
                                                        out.println("<a href=" + resultG2a.get(0).getLinkRef() + " class='btn btn-default' target='blank'>Buy Now</a>");
                                                        out.println("</form>");
                                                        out.println("<hr>");
                                                        k = 1;
                                                    }
                                                    out.println("</div>");
                                                    out.println("</div>");
                                                    }
                                                    out.println("<div class='row'>");
                                                    out.println("<div class='col-md-6'>");
                                                    half = (double) resultG2a.size() / 2;
                                                    resto = (resultG2a.size() % 2);
                                                    flag = true;
                                                    
                                                    i = k;
                                                    
                                                    if(resultG2a.get(0)==null)
                                                    {
                                                        System.out.println("Nessun elemento trovato");
                                                    }
                                                    else{                                                                                            
                                                    while (i < resultG2a.size()) {
                                                        if (i == half || i > half && flag) {
                                                            out.println("</div>");
                                                            out.println("<div class='col-md-6'>");
                                                            flag = false;
                                                        }
                                                        rating = resultG2a.get(i).getFeedback();

                                                        out.println("<div style='height:35px'>");
                                                        out.println("<i>" + resultG2a.get(i).getTitle() + "</i>");
                                                        out.println("</div>");
                                                        out.println("<br>");
                                                        out.println("<br>");

                                                        out.println("<form action='InformationResult' method='GET' role='form' class='form-horizontal'>");
                                                        out.println("<input type='hidden' name='id' value='" + resultG2a.get(i).getId() + "'>");
                                                        out.println("<input type='image' src=" + resultG2a.get(i).getImgUrl() + " class='img-rounded' width='250' height='350' />");
                                                        out.println("<br>");
                                                        out.println("<br>");
                                                        out.println("Prezzo: " + resultG2a.get(i).getPrice()+ " €");
                                                        out.println("<br>");
                                                        for (int j = 0; j < rating; j++) {
                                                            out.println("<span class='fa fa-star checked'></span>");
                                                        }
                                                        for (int j = rating; j < 5; j++) {
                                                            out.println("<span class='fa fa-star'></span>");
                                                        }
                                                        out.println("</br>");
                                                        out.println("<a href=" + resultG2a.get(i).getLinkRef() + " class='btn btn-default' target='blank'>Buy Now</a>");
                                                        out.println("</form>");
                                                        out.println("<hr>");
                                                        i++;
                                                    }
                                                    }
                                                    out.println("</div>");
                                                    out.println("</div>");

                                                %>
                                            </div>
                                            <div class="tab-pane text-center" id="shows">
                                                <% 
                                                    System.out.println("Tras");
                                                    String x;
                                                    out.println("<div class='row'>");
                                                    out.println("<div class='col-md-12'>");
                                                    
                                                    
                                                    if(resultYoutube.size()>1)
                                                    {
                                                    if(resultYoutube.get(0)==null)
                                                    {
                                                    System.out.println("Youtube è vagant");
                                                    }
                                                    else
                                                    {
                                                    out.println("<h3>Video più visualizzato</h3>");
                                                    x = resultYoutube.get(0).getLinkRef().substring(0, 23) + "embed/" + resultYoutube.get(0).getLinkRef().substring(31, resultYoutube.get(0).getLinkRef().length());
                                                    out.println("<iframe width='550' height='350' src=" + x + " frameborder='0' allow='autoplay'; encrypted-media allowfullscreen></iframe>");
                                                    out.println("<hr>");
                                                    out.println("<h3>GamePlay</h3>");
                                                    }
                                                    if(resultYoutube.get(1)==null)
                                                    {
                                                    System.out.println("Youtube è vagant");
                                                    }
                                                    else
                                                    {                                                    
                                                    x = resultYoutube.get(1).getLinkRef().substring(0, 23) + "embed/" + resultYoutube.get(1).getLinkRef().substring(31, resultYoutube.get(1).getLinkRef().length());
                                                    out.println("<iframe width='550' height='350' src=" + x + " frameborder='0' allow='autoplay'; encrypted-media allowfullscreen></iframe>");
                                                    out.println("<hr>");
                                                    }
                                                    }
                                                    else
                                                    {
                                                        //out.println("<p style='text-align:center'>Nessun elemento trovato</p>");
                                                    }
                                                    out.println("</div>");
                                                    out.println("</div>");
                                                    System.out.println("Esc");
                                                %>
                                            </div>
                                            <div class="tab-pane text-center" id="reviews">
                                                <%  
                                                    out.println("<div class='row'>");
                                                    out.println("<div class='col-md-12' style='text-align:left'>");
                                                    if(resultEveryEye.isEmpty())
                                                    {
                                                       out.println("<p style='text-align:center'>Nessun elemento trovato</p>"); 
                                                    }
                                                    else
                                                    {
                                                    i = 0;
                                                    while (i < resultEveryEye.size()) {
                                                        
                                                        out.println("<i>"+resultEveryEye.get(i).getTitle()+"</i>");
                                                        out.println("<textarea style='overflow-y:scroll;resize:none;border:none' rows='10' cols='100' >");                                                       
                                                        out.println(resultEveryEye.get(i).getRewiew());

                                                        out.println("</textarea>");

                                                        out.println("<p> Pubblicato da:");
                                                        out.println(resultEveryEye.get(i).getPublisher());
                                                        out.println("</p>");
                                                        out.println("<hr>");
                                                        i++;
                                                    }
                                                    }
                                                    out.println("</div>");
                                                    out.println("</div>");

                                                %>
                                            </div>

                                        </div>
                                    </div>
                                </div>
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

                </nav>
                        <div class="copyright pull-right">
                            &copy; 2018, made with  by <i class="fa fa-heart heart"></i><a href="https://www.linkedin.com/in/raffaele-ceruso-585a0612b/"><i> Ceruso </i></a>&<a href="https://www.linkedin.com/in/giovanni-leo-b223a712b/"><i> Leo</i></a>
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
