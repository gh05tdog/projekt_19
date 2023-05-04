package application;

import domain.User;

public class Model {
    private final View view;


    public Model(View view) {
        this.view = view;
        // Add a user to the database
        User.createUser("Test", "test",true);
    }

    public void login(String name) {
        view.showMainPage(name);
    }
}
