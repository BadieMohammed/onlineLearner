<html>
<head>
<title>Course Details</title>
</head>
<body>
<h1>Information</h1>
<#list course as details>
    <h2>${details.name}</h2>
    <br/>
    <h3>Producer: ${details.erstellersName}</h3>
    <br/>
    Number of Free Seats: ${details.freiePlaetze}
    <br/>
    <a href="/new_enroll?kid=${details.kID}"><button>Register</button></a>

</#list>

<#list owncourse as owndetails>
    <h2>${owndetails.name}</h2>
    <br/>
    <h3>Producer: ${owndetails.erstellersName}</h3>
    <br/>
    Number of Free Seats: ${owndetails.freiePlaetze}
    <br/>
    <a href="/delete?kid=${owndetails.kID}"><button>Delete</button></a>
    <br/>
    ==================================================

</#list>
<h1>${aufgaben}</h1>


    <table class="datatable">
        <tr>
            <th><h2>${title}</h2></th> <th><h2>${titletwo}</h2></th> <th><h2>${titlethree}</h2></th>
        </tr>
        <#list owntask as ownauf>
        <tr>
            <td><a href="/new_assignment?kid=${ownauf.kID}&anummer=${ownauf.aNummer}">${ownauf.name}</a></td> <td>${ownauf.text}</td> <td>${ownauf.grade}</td>
        </tr>
        </#list>
    </table>
<a href="/"><button>Back to login</button></a>
</body>
</html>