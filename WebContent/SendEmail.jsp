<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/favicon.ico">
    <title>SSC Servlet</title>
    
        <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/resources/dist/css/bootstrap.min.css" rel="stylesheet" >

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/cover.css" rel="stylesheet" >

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>
    <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js' type='text/javascript'></script>
    <script type="text/javascript">
    $(window).load(function(){
        $('#sendEmailModal').modal('show');
    });
</script>
</head>
<body>

    <div class="site-wrapper">
      <div class="site-wrapper-inner">
        <div class="cover-container">
          <div class="masthead clearfix">
            <div class="inner">
              <h2 class="masthead-brand">SSC Exercise 5 - Java Servlet</h2>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- Modal -->
	<div class="modal fade" id="sendEmailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title">Send Email</h4>
	      </div>
	      <div class="modal-body">
		      <form role="form" action="SendEmailServlet" method="get">
		        <div class="form-group">
		          <label for="toInput">To</label>
		          <input type="email" class="form-control" id="toInput" name="toInput" placeholder="Enter email">
		        </div>
		        <div class="form-group">
		          <label for="ccInput">CC</label>
		          <input type="email" class="form-control" id="ccInput" name="ccInput" placeholder="Enter email">
		        </div>
		        <div class="form-group">
		          <label for="bccInput">BCC</label>
		          <input type="email" class="form-control" id="bccInput" name="bccInput" placeholder="Enter email">
		        </div>
		        <div class="form-group">
		          <label for="subjectInput">Subject</label>
		          <input type="text" class="form-control" id="subjectInput" name="subjectInput" placeholder="Enter subject">
		        </div>
		        <div class="form-group">
		          <label for="bodyInput">Body</label>
		          <input type="text" class="form-control" id="bodyInput" name="bodyInput" placeholder="Enter body">
		        </div>
		          <div class="modal-footer">
		            <button type="submit" class="btn btn-primary">Send</button>
		          </div>
		      </form>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="resources/dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>