<%@ page import="com.bcsg.common.FormatUtils" %>
<%@ page import="com.bcsg.model.Card" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bcsg.servlet.CommonServlet" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">

    <title> BCSG - Java Technical Test </title>
</head>
<body>

    <div class="container theme-showcase" role="main">

        <% List<String> errorList = (List<String>) session.getAttribute(CommonServlet.ERROR_LIST_PARAM); %>
        <% if (errorList != null && errorList.size() > 0) { %>
            <div class="panel panel-danger">
                <div class="panel panel-heading">
                    Something went wrong
                </div>
                <div class="panel panel-body">
                    <ul>
                        <% for (String error : errorList) { %>
                            <li>
                                <%= error %>
                            </li>
                        <% } %>
                    </ul>
                </div>
            </div>
        <% } %>

        <% List<Card> cardList = (List<Card>) session.getAttribute(CommonServlet.CARD_LIST_PARAM); %>
        <% if (cardList != null && cardList.size() > 0) { %>
            <h2>Card list</h2>
            <table class="table">
                <tr>
                    <th>Bank</th>
                    <th>Card number</th>
                    <th>Expiry date</th>
                </tr>
                <% for (Card card : cardList) { %>
                    <tr>
                        <td><%= card.getBank() %></td>
                        <td><%= FormatUtils.getCardNumberFormat(card.getCardNumber()) %></td>
                        <td><%= FormatUtils.getFormatDate(card.getExpiryDate()) %></td>
                    </tr>
                <% } %>
            </table>
        <% } %>

        <h2>Insert new card</h2>
        <div class="row">
            <div class="col-md-6">
                <form action="CardServlet">
                    <div class="form-group">
                        <label for="bank">Bank</label>
                        <input type="text" class="form-control" name="bank" id="bank" placeholder="HSBC Canada">
                    </div>
                    <div class="form-group">
                        <label for="cardNumber">Card number</label>
                        <input type="text" class="form-control" name="cardNumber" id="cardNumber" placeholder="xxxx-xxxx-xxxx-xxxx">
                    </div>
                    <div class="form-group">
                        <label for="expiryDate">Expiry date</label>
                        <input type="text" class="form-control" name="expiryDate" id="expiryDate" placeholder="Feb-2015">
                    </div>

                    <button type="submit" class="btn btn-default btn-sm">Insert new card</button>
                </form>
            </div>
        </div>

        <h2>Import cards from CSV</h2>
        <div class="row">
            <div class="col-md-6">
                <form method="POST" action="CardUploadServlet" enctype="multipart/form-data" >
                    <div class="form-group">
                        <label for="file">Select a file to upload</label>
                        <input type="file" name="file" id="file" class="form-control" placeholder="sample.csv">
                    </div>
                    <button type="submit" class="btn btn-default btn-sm">Import Cards</button>
                </form>
            </div>
        </div>
    </div>

</body>
</html>
