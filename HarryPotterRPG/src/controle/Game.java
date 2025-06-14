package controle;

import dao.InicioBD;
import view.MainMenu;


public class Game {

	public static void main(String[] args) {
		new InicioBD();

        MainMenu menu = new MainMenu();
        menu.setVisible(true);
       }
}
