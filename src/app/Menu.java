package app;


import java.util.Scanner;

import repository.ConexaoBanco;
import repository.PostegresSQL;

public class Menu {
	public static void main(String[] args) {
		ConexaoBanco banco=new PostegresSQL();
		Scanner sc=new Scanner(System.in);
		new MenuExecucao(sc, new MetodoClasse(), new ServiceFactory(banco),new ParametroResolver(sc)).iniciar();

	}
}