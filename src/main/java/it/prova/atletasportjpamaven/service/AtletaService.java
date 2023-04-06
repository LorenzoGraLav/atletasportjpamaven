package it.prova.atletasportjpamaven.service;

import java.util.List;

import it.prova.atletasportjpamaven.dao.AtletaDAO;
import it.prova.atletasportjpamaven.dao.SportDAO;
import it.prova.atletasportjpamaven.model.Atleta;
import it.prova.atletasportjpamaven.model.Sport;

public interface AtletaService {
	public List<Atleta> listAll() throws Exception;

	public Atleta caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Atleta atletaInstance) throws Exception;

	public void inserisciNuovo(Atleta atletaInstance) throws Exception;

	public void rimuovi(Long idAtleta) throws Exception;

	public void aggiungiSport(Atleta atletaEsistente, Sport sportInstance) throws Exception;

	public void rimuoviSportDaAtleta(Long idAtleta, Long idSport) throws Exception;

	public Atleta caricaAtletaSingoloConSport(Long id) throws Exception;

	// per injection
	public void setAtletaDAO(AtletaDAO atletaDAO);

	public void setSportDAO(SportDAO sportDAO);
}
