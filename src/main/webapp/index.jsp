<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <script src=https://code.jquery.com/jquery-3.1.1.min.js ></script>
    <script src="http://momentjs.com/downloads/moment.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/index.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>

<ul class="nav">
    <li>
        <a href="<%=request.getContextPath()%>/addcar.jsp">Add a Car</a>
    </li>
    <li>
        <a href="<%=request.getContextPath()%>/usercars.jsp">My ads</a>
    </li>
    <li>
        <div class="card-header">
                <% if (request.getSession().getAttribute("user") == null) { %>
        <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Войти</a>
    <% } else { %>
        <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"> <%=request.getSession().getAttribute("user")%> | Выйти</a>
    <% } %>
    </div>
    </li>

</ul>


<table id="cars-tbl" name = "cars-tbl">
    <thead>
    <tr>
        <td> Description </td>
        <td> Photo </td>
        <td> Added Date </td>
        <td> Status </td>
    </tr>
    </thead>
    <tbody>
    <tr>
    </tr>
    </tbody>
</table>
</body>

<script>
       $(document).ready(function () {
        $.ajax({
            url: 'http://localhost:8080/drom/getAllCar.do',
            type: "GET",
            dataType: "json",
            success: function(data) {
                console.log("Start success function")
                let jsonobj = JSON.parse(JSON.stringify(data));
                console.log(jsonobj)
                $.each(data, function(index) {
                        let car = {
                            cuid:jsonobj[index].id,
                            date:jsonobj[index].date,
                            model:jsonobj[index].model,
                            brand:jsonobj[index].brand,
                            price:jsonobj[index].price,
                            year:jsonobj[index].year,
                            user:jsonobj[index].user,
                            imagePath:jsonobj[index].imagePath,
                            status:jsonobj[index].status
                        };

                        let row =
                            "<tr>" +
                            "<td>" +
                            "<a href='<%=request.getContextPath()%>/car.jsp?id="+ car.cuid + "' + >" +
                            car.brand + " " + car.price+ " " + car.year + " " + car.model + " " + car.user.name + " " + car.user.phone +
                            "</td>" +
                            "<td>" + "<img src="+ car.imagePath+">" +"</td>"+
                            "<td>" + "<span>" + car.date  + "</span>" +"</td>"+
                            "<td>" + "<span id='st-" + car.cuid + "'>" + car.status + "</span>" + "</td>"+
                            "</tr>";
                        $("#cars-tbl tbody").append(row);

                    car.status === false ? $("#st-" + car.cuid).prop("innerHTML", "Sold") : 'Active';
                    console.log(car.status)
                    }
                );
            }
        });
    })

</script>


</html>
