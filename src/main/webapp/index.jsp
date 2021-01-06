<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <script src=https://code.jquery.com/jquery-3.1.1.min.js ></script>
    <script src="http://momentjs.com/downloads/moment.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/nav.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>

<div class="menu">
    <ul>
        <li><a href="<%=request.getContextPath()%>/addcar.jsp">Add a Car</a></li>
        <li><a href="<%=request.getContextPath()%>/usercars.jsp">My ads</a></li>
        <% if (request.getSession().getAttribute("user") == null) { %> <li>
            <a href="<%=request.getContextPath()%>/login.jsp">Login</a> </li>
            <% } else { %>
        <li>  <a href="<%=request.getContextPath()%>/login.jsp"> <%=request.getSession().getAttribute("user")%> | Logout</a>
            <% } %></li>
    </ul>

    <div class="filter-menu">
        <ul>
            <li><button type="button">Show with photo</button></li>
            <li><button type="button">Show of the last day</button></li>
            <li><button type="button" id = "doFilterByBrand">Filter by brand</button>
            <select name = "brand-select" id="brand-select">
                <option>Brand 1</option>
                <option>Brand 2</option>
            </select>
            </li>
        </ul>
    </div>


</div>

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

    let arrBrands = [];

    $(document).ready(function () {
        $.ajax({
            url: 'http://localhost:8080/drom/getAllCar.do',
            type: "GET",
            dataType: "json",
            success: function(data) {
                let jsonobj = JSON.parse(JSON.stringify(data));
                $.each(data, function(index) {
                    arrBrands.push(jsonobj[index].brand);
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
                            "<td>" + "<img src=/download?name="+ car.imagePath+">" +"</td>"+
                            "<td>" + "<span>" + car.date  + "</span>" +"</td>"+
                            "<td>" + "<span id='st-" + car.cuid + "'>" + car.status + "</span>" + "</td>"+
                            "</tr>";
                        $("#cars-tbl tbody").append(row);
                        car.status === false ? $("#st-" + car.cuid).prop("innerHTML", 'Active' ) : 'Sold';
                        $("#brand-select").append($("<option />").val(car.brand).text(car.brand));
                    }
                );
            }
        });
    })



</script>

<script>
    $('#doFilterByBrand').click(function() {
        let brand = $("#brand-select>option:selected").text()
        $.ajax({
            url: 'http://localhost:8080/drom/getCarByBrand.do',
            type: "POST",
            dataType: "text",
            data: brand,
            success: function(data){
                console.log(data)
            }
        });
    })

</script>



</html>