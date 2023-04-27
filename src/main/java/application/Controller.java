package application;

import javafx.fxml.FXML;

public class Controller {
    private Model theModel;
    private View theView;



    @FXML
    protected void LoginButtonPressed()
    {
        theModel.login();
    }
    public void setModelAndView(Model model, View view)
    {
        this.theModel = model;
        this.theView = view;
    }
}
