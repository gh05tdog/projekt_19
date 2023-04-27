package application;

public class Model {
    private View view;
    private Controller theController;

    public Model(View view) {
        this.view = view;
    }

    public void login() {
        System.out.println("Login");
        view.showMainPage();
    }
}
