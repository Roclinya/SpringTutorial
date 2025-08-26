@import java.util.HashMap
@import  com.tutorial.SpringTutorial.vo.StockDto
@param StockDto stockDto

<%--@param Page page--%>
@param HashMap<String, Object> global

<head>
    @if(global.get("year") != null)
        <meta name="description" content="${global.get("year").toString()}">
    @endif
    <title>Title ${stockDto.name}!</title>
    <!-- Redirect page after 3 seconds -->
<%--    <meta http-equiv="refresh" content="3;url=https://www.mozilla.org" />--%>
</head>
<body>
@if(global.get("year") != null)
    <h1>${global.get("year").toString()}</h1>
@endif

    <p>Welcome to my example page!</p>

<select id="cars">
    <option value="volvo" selected="${false}">Volvo</option>
    <option value="saab" selected="${true}">Saab</option>
    <option value="opel" selected="${false}">Opel</option>
    <option value="audi" selected="${false}">Audi</option>
</select>
</body>
</select>
</body>