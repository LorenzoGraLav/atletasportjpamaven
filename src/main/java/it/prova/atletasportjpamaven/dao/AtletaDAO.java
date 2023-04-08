package it.prova.atletasportjpamaven.dao;

import java.util.List;

import it.prova.atletasportjpamaven.model.Atleta;
import it.prova.atletasportjpamaven.model.Sport;

public interface AtletaDAO extends IBaseDAO<Atleta> {
	public List<Atleta> findAllBySport(Sport sportInput);
	public Atleta findByIdFetchingSports(Long id);

	public void disconnectAtletaToSport(Atleta atletaInput)throws Exception;
	
	public void deleteAndDisconnectAtleta(Atleta atletaInput)throws Exception;
	public int sumNumeroMedaglieVinteInSportChiusi() throws Exception;


}
