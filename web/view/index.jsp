<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="utf-8">
    <title>Currency converter</title>
    <link rel="stylesheet" type="text/css" href="static/css/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" href="static/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="static/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" type="text/css" href="static/css/style.css"/>
  </head>
  <body>

    <div class="row" id="content">

        <div class="well col-xs-3 col-xs-offset-5">
            <H2>Currency converter</H2>
            <form role="form" method="post" name="form" id="form">

                <div class="form-group" id="date-input">
                    Date:
                    <input type="text" class="form-control" id="date" name="date" placeholder="Enter date">
                    <label class="control-label" id="date-info"></label>
                </div>

                <div class="form-group" id="from-input">
                    From:
                    <select id="from" name="from">
                        <option value=""> ... </option>
                    </select>
                    <label class="control-label" id="from-info"></label>
                </div>
                <span id="swap" class="glyphicon glyphicon-sort" title="Swap currencies" onclick="swap();"></span>
                <div class="form-group" id="to-input">
                    To:
                    <select id="to" name="to">
                        <option value=""> ... </option>
                    </select>
                    <label class="control-label" id="to-info"></label>
                </div>

                <div class="form-group" id="amount-input">
                    Amount:
                    <input type="text" class="form-control" maxlength="10" id="amount" name="amount" placeholder="Enter amount">
                    <label class="control-label" id="amount-info"></label>
                </div>

            </form>
            <button type="submit" class="btn btn-primary btn-block" onclick="handleForm();">Calculate</button>
        </div>

        <div id="result-div" class="well col-xs-3 col-xs-offset-5">
            <label id="result"></label>
        </div>

    </div>

  </body>
  <script type="text/javascript" src="static/js/jquery-1.7.1.min.js"></script>
  <script type="text/javascript" src="static/js/jquery-ui.js"></script>
  <script type="text/javascript" src="static/js/main.js"></script>
</html>
