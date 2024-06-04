<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Registro - Atlas</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/aos/aos.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

</head>

<body>

  <!-- ======= Header ======= -->
  <header id="header" class="fixed-top d-flex align-items-center">
    <div class="container d-flex align-items-center justify-content-between">

      <div class="logo">
        <h1 class="text-light"><a href="#">Atlas</a></h1>
      </div>

      <nav id="navbar" class="navbar">
        <ul>
          <li><a class="active" href="#">Home</a></li>
          <li class="dropdown"><a href="#"><span>Account</span> <i class="bi bi-chevron-down"></i></a>
            <ul>
              <li><a href="profile.jsp">Profile</a></li>
              <li><a href="#"></a></li>
              <li class="dropdown"><a href="#"><span>Funds Management</span> <i class="bi bi-chevron-right"></i></a>
                <ul>
                  <li><a href="deposit.jsp">Deposit</a></li>
                  <li><a href="transfer.jsp">Transfer</a></li>
                  <li><a href="withdrawal.jsp">Withdraw</a></li>
                </ul>
              </li>
            </ul>
          </li>
          <li><a href="#">Services</a></li>
          <li><a href="plans.jsp">Plans</a></li>
          <li><a href="#">Portfolio</a></li>
          <li><a href="#">Blog</a></li>
          <li><a href="contact.jsp">Contact</a></li>

          <li><a class="getstarted" href="login.jsp">Log In</a></li>
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav><!-- .navbar -->

    </div>
  </header><!-- End Header -->

  <main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="breadcrumb-hero">
        <div class="container">
          <div class="breadcrumb-hero">
            <h2>Register</h2>
            <p>Sign up now to access a wide range of personalized banking services and enjoy exclusive benefits. With our secure and easy-to-use platform, you can manage your finances efficiently and securely, from anywhere, at any time. </p>
          </div>
        </div>
      </div>
      <div class="container">
        <ol>
          <li><a href="index.jsp">Home</a></li>
          <li>Register</li>
        </ol>
      </div>
    </section><!-- End Breadcrumbs -->

    <!-- ======= Register Section ======= -->
    <section id="contact" class="contact">
      <div class="container text-center">
          
        <div class="row mt-5">

          <div class="col-lg-3" data-aos="fade-right">
          </div>

          <div class="col-lg-6 mt-5 mt-lg-0" data-aos="fade-left">

            <form action="register" method="post" class="php-email-form">
              
              <div class="row">
                <div class="col-md-6 form-group">
                  <label>Name</label>
                  <input type="text" name="firstName" class="form-control" id="firstName" placeholder="First Name" required>
                </div>
                <div class="col-md-6 form-group mt-3 mt-md-0">
                  <label> </label>
                  <input type="text" class="form-control" name="middleName" id="middleName" placeholder="Middle Name" required>
                </div>
                <div class="col-md-6 form-group">
                  <label>Last Name</label>
                  <input type="text" name="lastName1" class="form-control" id="lastName1" placeholder="First Last Name" required>
                </div>
                <div class="col-md-6 form-group mt-3 mt-md-0">
                  <label> </label>
                  <input type="text" class="form-control" name="lastName2" id="lastName2" placeholder="Second Last Name" required>
                </div>
                <div class="col-md-6 form-group">
                  <label>Identification</label>
                  <input type="text" name="identification" class="form-control" id="identification" placeholder="Identification Number" pattern="[0-9]+" required>
                </div>
                <div class="col-md-6 form-group mt-3 mt-md-0">
                  <label>Birth Date</label>
                  <input type="date" class="form-control" name="birthDate" id="birthDate" placeholder="" required max="2006-12-31">
                </div>
                <div class="col-md-6 form-group">
                  <label>Address</label>
                  <input type="text" name="address" class="form-control" id="address" placeholder="Address" required>
                </div>
                <div class="col-md-6 form-group mt-3 mt-md-0">
                  <label>Phone Number</label>
                  <input type="number" class="form-control" name="phoneNumber" id="phoneNumber" placeholder="Phone number" pattern="[0-9]+" required>
                </div>
                <div class="form-group">
                  <label for="username">Email</label>  
                  <input type="text" name="email" class="form-control" id="email" placeholder="Your password" required>
                </div>
              </div>
              <div class="row">
                <div class="form-group">
                  <label for="username">Username</label>  
                  <input type="text" name="username" class="form-control" id="username" placeholder="Your password" required>
                </div>
                <div class="form-group">
                  <label for="password">Password</label>  
                  <input type="password" name="password" class="form-control" id="password" placeholder="Password" required>
                  <input type="hidden" name="type" id="type" value="">
                  <script>
                    // Obtener el valor del parámetro 'type' de la URL
                    var urlParams = new URLSearchParams(window.location.search);
                    var typeValue = urlParams.get('type');
                    
                    // Asignar el valor al campo oculto
                    document.getElementById('type').value = typeValue;
                  </script>
                </div>
              </div>
              <div class="my-3">
                <div class="loading">Loading</div>
                <div class="error-message"></div>
                <div class="sent-message">Your message has been sent. Thank you!</div>
              </div>
              <div class="text-center"><button type="submit">Crear</button></div>
            </form>

          </div>

        </div>

      </div>
    </section><!-- End Contact Section -->

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <footer id="footer">
    <div class="footer-top">
      <div class="container">
        <div class="row">

          <div class="col-lg-3 col-md-6 footer-info">
            <h3>Serenity</h3>
            <p>Cras fermentum odio eu feugiat lide par naso tierra. Justo eget nada terra videa magna derita valies darta donna mare fermentum iaculis eu non diam phasellus. Scelerisque felis imperdiet proin fermentum leo. Amet volutpat consequat mauris nunc congue.</p>
          </div>

          <div class="col-lg-3 col-md-6 footer-links">
            <h4>Useful Links</h4>
            <ul>
              <li><a href="#">Home</a></li>
              <li><a href="#">About us</a></li>
              <li><a href="#">Services</a></li>
              <li><a href="#">Terms of service</a></li>
              <li><a href="#">Privacy policy</a></li>
            </ul>
          </div>

          <div class="col-lg-3 col-md-6 footer-contact">
            <h4>Contact Us</h4>
            <p>
              A108 Adam Street <br>
              New York, NY 535022<br>
              United States <br>
              <strong>Phone:</strong> +1 5589 55488 55<br>
              <strong>Email:</strong> info@example.com<br>
            </p>

            <div class="social-links">
              <a href="#" class="twitter"><i class="bi bi-twitter"></i></a>
              <a href="#" class="facebook"><i class="bi bi-facebook"></i></a>
              <a href="#" class="instagram"><i class="bi bi-instagram"></i></a>
              <a href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
            </div>

          </div>

          <div class="col-lg-3 col-md-6 footer-newsletter">
            <h4> Our Newsletter</h4>
            <p> Tamen quem nulla quae legam multos aute sint culpa legam noster magna veniam enim veniam illum dolore legam minim quorum culpa amet magna export quem marada parida nodela caramase seza.</p>
            <form action="" method="post">
              <input type="email" name="email"><input type="submit" value="Subscribe">
            </form>
          </div>

        </div>
      </div>
    </div>

    <div class="container">
      <div class="copyright">
        &copy; Copyright <strong><span>Serenity</span></strong>. All Rights Reserved
      </div>
      <div class="credits">
        <!-- All the links in the footer should remain intact. -->
        <!-- You can delete the links only if you purchased the pro version. -->
        <!-- Licensing information: https://bootstrapmade.com/license/ -->
        <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/serenity-bootstrap-corporate-template/ -->
        Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
      </div>
    </div>
  </footer><!-- End Footer -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="assets/vendor/purecounter/purecounter_vanilla.js"></script>
  <script src="assets/vendor/aos/aos.js"></script>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
  <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
  <script src="assets/vendor/waypoints/noframework.waypoints.js"></script>

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>
  <script src="assets/js/URLParams.js"></script>

</body>

</html>