package it.prova.atletasportjpamaven.dao;

import java.util.List;

import it.prova.atletasportjpamaven.model.Atleta;
import it.prova.atletasportjpamaven.model.Sport;

public interface SportDAO extends IBaseDAO<Sport> {

	public Sport findByDescrizione(String descrizione) throws Exception;
	
	List<Atleta> findAllBySport(Sport sportInput) throws Exception;
	
	public Sport findByIdFetchingAtleti(Long id);

}
