package controle;

import dao.InicioBD;
import dao.InserirPerguntas;
import view.MainMenu;


public class Game {

	public static void main(String[] args) {
		new InicioBD();
		new InserirPerguntas();

        MainMenu menu = new MainMenu();
        menu.setVisible(true);
       }
}
