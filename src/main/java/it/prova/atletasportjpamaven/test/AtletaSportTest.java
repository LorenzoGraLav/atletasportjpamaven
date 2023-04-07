package it.prova.atletasportjpamaven.test;

import java.time.LocalDate;
import java.util.List;

import it.prova.atletasportjpamaven.dao.EntityManagerUtil;
import it.prova.atletasportjpamaven.model.Atleta;
import it.prova.atletasportjpamaven.model.Sport;
import it.prova.atletasportjpamaven.model.StatoAtleta;
import it.prova.atletasportjpamaven.service.AtletaService;
import it.prova.atletasportjpamaven.service.MyServiceFactory;
import it.prova.atletasportjpamaven.service.SportService;

public class AtletaSportTest {

	public static void main(String[] args) {
		AtletaService atletaServiceInstance = MyServiceFactory.getAtletaServiceInstance();
		SportService sportServiceInstance = MyServiceFactory.getSportServiceInstance();

		// ora passo alle operazioni CRUD
		try {

//					// inizializzo i ruoli sul db
//					initRuoli(ruoloServiceInstance);

			System.out.println("In tabella  ci sono " + atletaServiceInstance.listAll().size() + " elementi.");

			testInserisciNuovoAtleta(atletaServiceInstance);
			System.out.println("In tabella Atleta ci sono " + atletaServiceInstance.listAll().size() + " elementi.");

			testCollegaAtletaASportEsistente(sportServiceInstance, atletaServiceInstance);
			System.out.println("In tabella Atleta ci sono " + atletaServiceInstance.listAll().size() + " elementi.");

			testModificaStatoAtleta(atletaServiceInstance);
			System.out.println("In tabella Atleta ci sono " + atletaServiceInstance.listAll().size() + " elementi.");

			testRimuoviSportDaAtleta(sportServiceInstance, atletaServiceInstance);
			System.out.println("In tabella Atleta ci sono " + atletaServiceInstance.listAll().size() + " elementi.");

			testInserisciNuovoSport(sportServiceInstance);
			System.out.println("In tabella Sport ci sono " + sportServiceInstance.listAll().size() + " elementi.");

			testAggiornaAtleta(atletaServiceInstance);
			System.out.println("In tabella Atleta ci sono " + atletaServiceInstance.listAll().size() + " elementi.");
			
			testAggiornaSport(sportServiceInstance);
			System.out.println("In tabella Sport ci sono " + sportServiceInstance.listAll().size() + " elementi.");
			
			testRimuoviAtleta(atletaServiceInstance);
			System.out.println("In tabella Atleta ci sono " + atletaServiceInstance.listAll().size() + " elementi.");
			
			testRimuoviSport(sportServiceInstance);
			System.out.println("In tabella Sport ci sono " + sportServiceInstance.listAll().size() + " elementi.");



		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}

	}

	private static void testInserisciNuovoAtleta(AtletaService atletaServiceInstance) throws Exception {
		System.out.println(".......testInserisciNuovoAtleta inizio.............");

		Atleta atletaNuovo = new Atleta("Filippo", "Magnini", "FP096", LocalDate.now(), 4);
		atletaServiceInstance.inserisciNuovo(atletaNuovo);
		if (atletaNuovo.getId() == null)
			throw new RuntimeException("testInserisciNuovoAtleta fallito ");

		System.out.println(".......testInserisciNuovoAtleta fine: PASSED.............");
	}

	private static void testInserisciNuovoSport(SportService sportServiceInstance) throws Exception {
		System.out.println(".......testInserisciNuovoSport inizio.............");

		Sport sportNuovo = new Sport("Basket", LocalDate.parse("2023-05-01"), LocalDate.now());
		sportServiceInstance.inserisciNuovo(sportNuovo);
		if (sportNuovo.getId() == null)
			throw new RuntimeException("testInserisciNuovoSport fallito ");

		System.out.println(".......testInserisciNuovoSport fine: PASSED.............");
	}

	private static void testAggiornaAtleta(AtletaService atletaServiceInstance) throws Exception {
		System.out.println(".......testAggiornaAtleta inizio.............");

		List<Atleta> listaAtleti = atletaServiceInstance.listAll();
		Atleta daAggiornare = listaAtleti.get(0);

		daAggiornare.setNome("Maurizio");

		atletaServiceInstance.aggiorna(daAggiornare);

		List<Atleta> listaAtletiFinale = atletaServiceInstance.listAll();

		System.out.println(listaAtletiFinale.get(0));

		System.out.println(".......testAggiornaAtleta fine: PASSED.............");

	}

	private static void testAggiornaSport(SportService sportServiceInstance) throws Exception {
		System.out.println("............testAggiornaSport inizio.................");

		List<Sport> elencoSport = sportServiceInstance.listAll();

		Sport daAggiornare = elencoSport.get(0);
		
		daAggiornare.setDescrizione("Pallamano");

		sportServiceInstance.aggiorna(daAggiornare);

		List<Sport> elencoSportFinale = sportServiceInstance.listAll();

		System.out.println(elencoSportFinale.get(0));

		System.out.println(".............testAggiornaSport fine: PASSED..............");
	}

	private static void testRimuoviAtleta(AtletaService atletaServiceInstance) throws Exception {
		System.out.println("...........testRimuoviAtleta inizio.............");

		List<Atleta> elencoAtleti = atletaServiceInstance.listAll();

		Atleta daCancellare = elencoAtleti.get(elencoAtleti.size() - 1);

		atletaServiceInstance.rimuovi(daCancellare.getId());

		System.out.println(atletaServiceInstance.listAll());

		System.out.println("...........testRimuoviAtleta fine: PASSED...........");
	}

	private static void testRimuoviSport(SportService sportServiceInstance) throws Exception {
		System.out.println("...........testRimuoviSport inizio.............");

		List<Sport> elencoSports = sportServiceInstance.listAll();

		Sport daCancellare = elencoSports.get(elencoSports.size() - 1);

		sportServiceInstance.rimuovi(daCancellare.getId());

		System.out.println(sportServiceInstance.listAll());

		System.out.println("...........testRimuoviSport fine: PASSED...........");
	}

	private static void testCollegaAtletaASportEsistente(SportService sportServiceInstance,
			AtletaService atletaServiceInstance) throws Exception {
		System.out.println(".......testCollegaAtletaASportEsistente inizio.............");

		Sport sportEsistenteSuDb = sportServiceInstance.cercaPerDescrizione("Calcio");
		if (sportEsistenteSuDb == null)
			throw new RuntimeException("testCollegaAtletaASportEsistente fallito: sport inesistente ");

		// mi creo un atleta inserendolo direttamente su db
		Atleta atletaNuovo = new Atleta("Luca", "Fornio", "LF095", LocalDate.now(), 5);
		atletaServiceInstance.inserisciNuovo(atletaNuovo);
		if (atletaNuovo.getId() == null)
			throw new RuntimeException("testInserisciNuovoAtleta fallito: atleta non inserito ");

		atletaServiceInstance.aggiungiSport(atletaNuovo, sportEsistenteSuDb);
		// per fare il test ricarico interamente l'oggetto e la relazione
		Atleta atletaReloaded = atletaServiceInstance.caricaAtletaSingoloConSport(atletaNuovo.getId());
		if (atletaReloaded.getSports().size() != 1)
			throw new RuntimeException("testInserisciNuovoAtleta fallito: sport non aggiunti ");

		System.out.println(".......testCollegaAtletaASportEsistente fine: PASSED.............");
	}

	private static void testModificaStatoAtleta(AtletaService atletaServiceInstance) throws Exception {
		System.out.println(".......testModificaStatoAtleta inizio.............");

		// mi creo un atleta inserendolo direttamente su db
		Atleta atletaNuovo = new Atleta("Valentino", "Rossi", "VR46", LocalDate.now(), 200);
		atletaServiceInstance.inserisciNuovo(atletaNuovo);
		if (atletaNuovo.getId() == null)
			throw new RuntimeException("testModificaStatoAtleta fallito: atleta non inserito ");

		// proviamo a passarlo nello stato ATTIVO ma salviamoci il vecchio stato
		StatoAtleta vecchioStato = atletaNuovo.getStato();
		atletaNuovo.setStato(StatoAtleta.ATTIVO);
		atletaServiceInstance.aggiorna(atletaNuovo);

		if (atletaNuovo.getStato().equals(vecchioStato))
			throw new RuntimeException("testModificaStatoAtleta fallito: modifica non avvenuta correttamente ");

		System.out.println(".......testModificaStatoAtleta fine: PASSED.............");
	}

	private static void testRimuoviSportDaAtleta(SportService sportServiceInstance, AtletaService atletaServiceInstance)
			throws Exception {
		System.out.println(".......testRimuoviSportDaAtleta inizio.............");

		// carico uno sport e lo associo ad un nuovo atleta
		Sport sportEsistenteSuDb = sportServiceInstance.cercaPerDescrizione("Pallavolo");
		if (sportEsistenteSuDb == null)
			throw new RuntimeException("testRimuoviSportDaAtleta fallito: sport inesistente ");

		// mi creo un atleta inserendolo direttamente su db
		Atleta atletaNuovo = new Atleta("Aldo", "Pieretti", "AP98", LocalDate.now(), 4);
		atletaServiceInstance.inserisciNuovo(atletaNuovo);
		if (atletaNuovo.getId() == null)
			throw new RuntimeException("testRimuoviSportDaAtleta fallito: atleta non inserito ");
		atletaServiceInstance.aggiungiSport(atletaNuovo, sportEsistenteSuDb);

		// ora ricarico il record e provo a disassociare lo sport
		Atleta atletaReloaded = atletaServiceInstance.caricaAtletaSingoloConSport(atletaNuovo.getId());
		boolean confermoSportPresente = false;
		for (Sport sportItem : atletaReloaded.getSports()) {
			if (sportItem.getDescrizione().equals(sportEsistenteSuDb.getDescrizione())) {
				confermoSportPresente = true;
				break;
			}
		}

		if (!confermoSportPresente)
			throw new RuntimeException("testRimuoviSportDaAtleta fallito: atleta e sport non associati ");

		// ora provo la rimozione vera e propria ma poi forzo il caricamento per fare un
		// confronto 'pulito'
		atletaServiceInstance.rimuoviSportDaAtleta(atletaReloaded.getId(), sportEsistenteSuDb.getId());
		atletaReloaded = atletaServiceInstance.caricaAtletaSingoloConSport(atletaNuovo.getId());
		if (!atletaReloaded.getSports().isEmpty())
			throw new RuntimeException("testRimuoviSportDaAtleta fallito: sport ancora associato ");

		System.out.println(".......testRimuoviSportDaAtleta fine: PASSED.............");
	}

}
