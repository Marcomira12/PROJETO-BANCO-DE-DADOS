package app;


import java.util.Scanner;

import repository.ConexaoBanco;
import repository.PostegresSQL;

public class Menu {
	public static void main(String[] args) {
		ConexaoBanco banco=new PostegresSQL();
		new MenuExecucao(new Scanner(System.in), new MetodoClasse(), new ServiceFactory(banco)).iniciar();

	}
}