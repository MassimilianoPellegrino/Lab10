package it.polito.tdp.rivers.db;

public class TestRiversDAO {

	public static void main(String[] args) {
		RiversDAO dao = new RiversDAO();
		System.out.println(dao.getAllRivers());
		System.out.println(dao.getUltimaMisurazione(dao.getAllRivers().get(0)));
		System.out.println(dao.getNumeroMisurazioni(dao.getAllRivers().get(0)));
		System.out.println(dao.getFlussoMedio(dao.getAllRivers().get(0)));
		System.out.println(dao.getFlussi(dao.getAllRivers().get(0)).get(0));

	}

}
