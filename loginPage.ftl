<html>
<head><title>Login</title>

<body>

<h1>Login</h1>


<form name="user" action="/" method="post">
    <div>
        <h3>${error}</h3><br/>
        Enter your email:
        <input type="text" name="username"/><br/>
        <input type="submit" value="login" />
    </div>
</form>
<a href="/signup"><button>Sign up now!</button></a>

</body>
</html>