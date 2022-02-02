<html>
<head><title>Main Page</title>

<body>
<h3>${error}</h3><br/>
<a href="/"><button>Go to login</button></a>
<form name="user" action="view_main" method="post">

    <h1>My Courses:</h1>
    <br/>
    <div>
        <#list myowncourse as owncourse>
            <li>Title:&nbsp;<a href="/view_course?kid=${owncourse.kID}">${owncourse.name}</a></li>
            <li>Producer: ${owncourse.erstellersName}</li>
            <li>Free Seats: ${owncourse.freiePlaetze}</li>
            <h3>===================================</h3>
        </#list>
    </div>
    <br/>
    <h1>Available Courses:</h1>
    <br/>
    <div>
    <#list mycourse as course>
        <li>Title:&nbsp;<a href="/view_course?kid=${course.kID}">${course.name}</a></li>
        <li>Producer: ${course.erstellersName}</li>
        <li>Free Seats: ${course.freiePlaetze}</li>
        <h3>===================================</h3>
    </#list>
    </div>

</form>
<a href="/new_course"><button>Create a course</button></a>
</body>
</html>