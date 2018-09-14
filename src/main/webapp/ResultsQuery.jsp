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

        <title>Profile Page - Material Kit by Creative Tim</title>

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
                    <a class="navbar-brand" href="http://www.creative-tim.com">Creative Tim</a>
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
                                            ArrayList<Result> resultMatching = (ArrayList<Result>) request.getAttribute("resultMatching");
                                            ArrayList<Result> resultIg = (ArrayList<Result>) request.getAttribute("resultIg");
                                            ArrayList<Result> resultG2a = (ArrayList<Result>) request.getAttribute("resultG2a");
                                            ArrayList<Result> resultYoutube = (ArrayList<Result>) request.getAttribute("resultYoutube");
                                            ArrayList<Result> resultEveryEye = (ArrayList<Result>) request.getAttribute("resultEveryEye");

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
                                    <h3 class="title">Games</h3>
               
                                </div>
                            </div>
                        </div>
                        <div class="description text-center">
                            <p>An artist of considerable range, Chet Faker — the name taken by Melbourne-raised, Brooklyn-based Nick Murphy — writes, performs and records all of his own music, giving it a warm, intimate feel with a solid groove structure. </p>
                        </div>

                        <div class="row">
                            <div class="col-md-6 col-md-offset-3">
                                <div class="profile-tabs">
                                    <div class="nav-align-center">
                                        <ul class="nav nav-pills" role="tablist">
                                            <li class="active">
                                                <a href="#studio" role="tab" data-toggle="tab">
                                                    <i class="material-icons">star</i>
                                                    Minor prezzo
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

                                                <% 
                                                    int half = (resultMatching.size() / 2);
                                                    int rating;
                                                    boolean flag = false;
                                                    out.println("<div class='row'>");
                                                    out.println("<div class='col-md-6'>");

                                                    for (int i = 0; i < resultMatching.size(); i++) {
                                                        if (i == half || i == half+ 1 && flag == false) {
                                                            out.println("</div>");
                                                            out.println("<div class='col-md-6'>");
                                                            flag = true;
                                                        }
                                                        rating = resultMatching.get(i).getFeedback()/100;
                                                        
                                                   
                                                        out.println("<b>"+resultMatching.get(i).getTitle()+"</b>");
                                                        out.println("<br>");
                                                        out.println("<br>");
                                                 
                                                        
                                                        out.println("<form action='InformationResult' method='GET' role='form' class='form-horizontal'>");
                                                        out.println("<input type='hidden' name='id' value='"+resultMatching.get(i).getId()+"'>");
                                                        out.println("<input type='image' src=" + resultMatching.get(i).getImgUrl() + " class='img-rounded' width='250' height='350' />");                                                      
                                                        
                                                        out.println("Prezzo: "+resultMatching.get(i).getPrice());
                                                        out.println("<br>");
                                                        for(int j=0;j<rating;j++)
                                                        {
                                                            out.println("<span class='fa fa-star checked'></span>");
                                                        }
                                                        for(int j=rating;j<5;j++)
                                                        {
                                                            out.println("<span class='fa fa-star'></span>");
                                                        }
                                                        out.println("</br>");
                                                        out.println("<a href=" + resultMatching.get(i).getLinkRef() + " class='btn btn-default'>Buy Now</a>");
                                                        out.println("</form>");
                                                        out.println("<hr>");
                                                    }
                                                    out.println("</div>");
                                                    out.println("</div>");

                                                %>

                                            </div>
                                            <div class="tab-pane text-center" id="work">
                                                <%  
                                                    System.out.println("" + resultIg.size());
                                                    System.out.println("" + resultG2a.size());
                                                    flag = false;
                                                    out.println("<div class='row'>");
                                                    out.println("<div class='col-md-6'>");
                                                    half = (resultIg.size() / 2);
                                                    int resto = (resultIg.size()% 2);
                                                 
                                                    for (int i = 0; i < resultIg.size(); i++) {
                                                        if (i == half+1 ) {
                                                            out.println("</div>");
                                                            out.println("<div class='col-md-6'>");
                                                       
                                                        }
                                                        rating = resultIg.get(i).getFeedback()/100;
                                                        
                                                   
                                                        out.println("<b>"+resultIg.get(i).getTitle()+"</b>");
                                                        out.println("<br>");
                                                        out.println("<br>");
                                                        
                                                        out.println("<form action='InformationResult' method='GET' role='form' class='form-horizontal'>");
                                                        out.println("<input type='hidden' name='id' value='"+resultIg.get(i).getId()+"'>");
                                                        out.println("<input type='image' src=" + resultIg.get(i).getImgUrl() + " class='img-rounded' width='250' height='350' />");
                                                        out.println("Prezzo: "+resultIg.get(i).getPrice());
                                                        out.println("</br>");
                                                        for(int j=0;j<rating;j++)
                                                        {
                                                            out.println("<span class='fa fa-star checked'></span>");
                                                        }
                                                        for(int j=rating;j<5;j++)
                                                        {
                                                            out.println("<span class='fa fa-star'></span>");
                                                        }
                                                        out.println("</br>");
                                                        out.println("<a href=" + resultIg.get(i).getLinkRef() + " class='btn btn-default'>Buy Now</a>");
                                                        out.println("</form>");
                                                        out.println("<hr>");
                                     
                                                    }
                                                    int k=0;
                                                    if(resto==1)
                                                    {   
                                                        rating = resultG2a.get(0).getFeedback()/100;
                                                        
                                                   
                                                        out.println("<b>"+resultG2a.get(0).getTitle()+"</b>");
                                                        out.println("<br>");
                                                        out.println("<br>");
                                                        
                                                        out.println("<form action='InformationResult' method='GET' role='form' class='form-horizontal'>");
                                                        out.println("<input type='hidden' name='id' value='"+resultG2a.get(0).getId()+"'>");
                                                        out.println("<input type='image' src=" + resultG2a.get(0).getImgUrl() + " class='img-rounded' width='250' height='350' />");
                                                        out.println("Prezzo: "+resultG2a.get(0).getPrice());
                                                        out.println("<br>");
                                                        for(int j=0;j<rating;j++)
                                                        {
                                                            out.println("<span class='fa fa-star checked'></span>");
                                                        }
                                                        for(int j=rating;j<5;j++)
                                                        {
                                                            out.println("<span class='fa fa-star'></span>");
                                                        }
                                                        out.println("</br>");
                                                        out.println("<a href=" + resultG2a.get(0).getLinkRef() + " class='btn btn-default'>Buy Now</a>");
                                                        out.println("</form>");
                                                        out.println("<hr>");                                                        
                                                        k=1;
                                                    }
                                                    out.println("</div>");
                                                    out.println("</div>");

                                                    out.println("<div class='row'>");
                                                    out.println("<div class='col-md-6'>");
                                                    half = (resultG2a.size() / 2);
                                                    resto = (resultG2a.size()% 2);
                                                 
                                                    
                                                    for (int i = k; i < resultG2a.size(); i++) {
                                                        if ( i == half+ 1) {
                                                            out.println("</div>");
                                                            out.println("<div class='col-md-6'>");
                                                            
                                                        }
                                                        rating = resultG2a.get(i).getFeedback()/100;
                                                        
                                                   
                                                        out.println("<b>"+resultG2a.get(i).getTitle()+"</b>");
                                                        out.println("<br>");
                                                        out.println("<br>");
                                                 
                                                        
                                                        out.println("<form action='InformationResult' method='GET' role='form' class='form-horizontal'>");
                                                        out.println("<input type='hidden' name='id' value='"+resultG2a.get(i).getId()+"'>");
                                                        out.println("<input type='image' src=" + resultG2a.get(i).getImgUrl() + " class='img-rounded' width='250' height='350' />");                                                      
                                                        
                                                        out.println("Prezzo: "+resultG2a.get(i).getPrice());
                                                        out.println("<br>");
                                                        for(int j=0;j<rating;j++)
                                                        {
                                                            out.println("<span class='fa fa-star checked'></span>");
                                                        }
                                                        for(int j=rating;j<5;j++)
                                                        {
                                                            out.println("<span class='fa fa-star'></span>");
                                                        }
                                                        out.println("</br>");
                                                        out.println("<a href=" + resultG2a.get(i).getLinkRef() + " class='btn btn-default'>Buy Now</a>");
                                                        out.println("</form>");
                                                        out.println("<hr>");

                                                    }

                                                    out.println("</div>");
                                                    out.println("</div>");
                                                   
                                                %>
                                            </div>
                                            <div class="tab-pane text-center" id="shows">
                                                <%
                                                    
                                                  
                                                    out.println("<div class='row'>");
                                                    out.println("<div class='col-md-12'>");

                                                    
                                                        out.println("<h6>Trailer</h6>");  
                                                        String x = resultYoutube.get(0).getLinkRef().substring(0, 23) + "embed/" + resultYoutube.get(0).getLinkRef().substring(31, resultYoutube.get(0).getLinkRef().length());
                                                        out.println("<iframe width='550' height='350' src="+x+" frameborder='0' allow='autoplay'; encrypted-media allowfullscreen></iframe>");
                                                        out.println("<hr>");
                                                        out.println("<h6>GamePlay</h6>");  
                                                        x = resultYoutube.get(1).getLinkRef().substring(0, 23) + "embed/" + resultYoutube.get(1).getLinkRef().substring(31, resultYoutube.get(1).getLinkRef().length());
                                                        out.println("<iframe width='550' height='350' src="+x+" frameborder='0' allow='autoplay'; encrypted-media allowfullscreen></iframe>");
                                                        out.println("<hr>");
                                                        out.println("</div>");
                                                        out.println("</div>");

                                                %>
                                            </div>
                                            <div class="tab-pane text-center" id="reviews">
                                                <%
                                                                                                      
                                                    out.println("<div class='row'>");
                                                    out.println("<div class='col-md-12'>");

                                                    for (int i = 0; i < resultEveryEye.size(); i++) {
                                                        
                                                        
                                                        out.println("<p>");
                                                        out.println(resultEveryEye.get(i).getRewiew().substring(0, 1000));                                                        
                                                        out.println("</p>"); 
                                                       
                                                        out.println("<p> Pubblicato da:");
                                                        out.println(resultEveryEye.get(i).getPublisher());                                                        
                                                        out.println("</p>"); 
                                                        out.println("<hr>");
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
