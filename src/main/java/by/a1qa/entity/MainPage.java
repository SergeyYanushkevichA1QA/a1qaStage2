package by.a1qa.entity;

public class MainPage  {
    private final LoginForm loginForm = new LoginForm();
    private final NavBarForm navBarForm = new NavBarForm();


    public LoginForm getLoginForm() {
        return loginForm;
    }

    public NavBarForm getNavBarForm() {
        return navBarForm;
    }

}
