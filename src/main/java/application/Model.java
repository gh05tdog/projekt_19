package application;

public class Model {
    private View view;


    public Model(View view) {
        this.view = view;
    }

    public void login(String name) {
        view.showMainPage(name);
    }
}
