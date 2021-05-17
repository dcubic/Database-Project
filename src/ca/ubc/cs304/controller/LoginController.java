package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.LoginWindowDelegate;
import ca.ubc.cs304.ui.LoginWindow;
import ca.ubc.cs304.ui.MainMenu;

public class LoginController implements LoginWindowDelegate {

	private DatabaseConnectionHandler dbHandler = null;
	private LoginWindow loginWindow = null;

	public LoginController() {
		dbHandler = new DatabaseConnectionHandler();
	}

	private void start() {
		loginWindow = new LoginWindow();
		loginWindow.showFrame(this);
	}

	/**
	 * LoginWindowDelegate Implementation
	 * <p>
	 * connects to Oracle database with supplied username and password
	 */
	public void login(String username, String password) {
		boolean didConnect = dbHandler.login(username, password);

		if (didConnect) {
            loginWindow.dispose();
            MainMenu mainMenu = new MainMenu(dbHandler);
            mainMenu.showFrame();
        } else {
            loginWindow.handleLoginFailed();

            if (loginWindow.hasReachedMaxLoginAttempts()) {
                loginWindow.dispose();
                System.out.println("You have exceeded your number of allowed attempts");
                System.exit(-1);
            }
        }
	}

    public static void main(String args[]) {
        LoginController loginController = new LoginController();
        loginController.start();
    }

}
