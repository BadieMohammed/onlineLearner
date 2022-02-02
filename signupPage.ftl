<html>
<head><title>Sign Up</title>

<body>

<h1>Welcome</h1>


<form name="user" action="/signup" method="post">
    <div>
        <h3>${error}</h3><br/>
        Enter your email:
        <input type="text" name="username"/><br/>
        Enter your name:
        <input type="text" name="name"/><br/>
        <input type="submit" value="sign up" />
    </div>
</form>
<a href="/"><button>Login</button></a>

</body>
</html>