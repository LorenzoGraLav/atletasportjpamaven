package it.prova.atletasportjpamaven.service;

import java.util.List;

import it.prova.atletasportjpamaven.dao.AtletaDAO;
import it.prova.atletasportjpamaven.dao.SportDAO;
import it.prova.atletasportjpamaven.model.Sport;

public interface SportService {
	public List<Sport> listAll() throws Exception;

	public Sport caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Sport sportInstance) throws Exception;

	public void inserisciNuovo(Sport sportInstance) throws Exception;

	public void rimuovi(Long idSportToRemove) throws Exception;
	
	public Sport cercaPerDescrizione(String descrizione) throws Exception;

	// per injection
	public void setAtletaDAO(AtletaDAO atletaDAO);

	public void setSportDAO(SportDAO sportDAO);
}
