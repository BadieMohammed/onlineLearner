<html>
<head><title>Delete a course</title>

<body>
<br/>
<div><h3>${error}</h3><br/>
    <a href="/"><button>Go to login</button></a>
</div>
<br/>

<#list registered as kurs>
    <form name="user" action="delete" method="post">

        Are you sure about deleting ${kurs.name} ?<br/>

        <input type="submit" value="delete"/>
    </form>
</#list>
<a href="/view_main"><button>Return to Main Page</button></a>
</body>
</html>