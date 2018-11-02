package airportis.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import airportis.app.dao.DestinationDAO;

@Service
public class DestinationServiceImpl implements DestinationService{

	@Autowired
	DestinationDAO destinationDAO;
	
	@Override
	@Transactional
	public Map<Integer, String> getAllDestinations() {
		if(destinationDAO==null) System.out.println("DestinationDAO je NULL");
		return destinationDAO.getAllDestinations();
	}

}