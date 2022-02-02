<html>
<head><title>New Course</title>

<body>
<h1>Create a new course</h1>
<br/>
<div><h3>${error}</h3><br/>
<a href="/"><button>Go to login</button></a>
</div>
<br/>
<form name="user" action="new_course" method="post">
    Name: <input type="text" name="titel"/> <br/>
    Registration Password: <input type="text" name="pass"/><br/>
    <label for="seats">Number of free seats:</label>
    <input type="number" id="seats" name="seats" step="1" min="0" max="100"><br/>
    Description: <textarea name="descrip" rows="10" cols="40"></textarea><br/>

    <input type="submit" value="create"/>
</form>
<a href="/view_main"><button>Return to Main Page</button></a>
</body>
</html>