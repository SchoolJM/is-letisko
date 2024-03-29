package airportis.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import airportis.app.dao.DestinationDAO;
import airportis.app.entity.Destination;
import airportis.app.model.DestinationModel;

@Service
public class DestinationServiceImpl implements DestinationService{

	@Autowired
	DestinationDAO destinationDAO;
	
	@Override
	@Transactional
	public Map<Integer, String> getAllDestinations() {
		return destinationDAO.getAllDestinations();
	}

	@Override
	@Transactional
	public String getDestinationName(int id) {
		String name = destinationDAO.getDestination(id).getAirport() + 
				", " + destinationDAO.getDestination(id).getCity() +
				", "+ destinationDAO.getDestination(id).getCountry();
		return name;
	}

	@Override
	@Transactional
	public void save(DestinationModel destinationModel) {
			Destination destination= new Destination(
					destinationModel.getAirport(),
					destinationModel.getCity(),
					destinationModel.getCountry(),
					destinationModel.getLatitude(),
					destinationModel.getLongitude());
			destination.setId(destinationModel.getId());
			destinationDAO.save(destination);
	}

	@Override
	@Transactional
	public List<Destination> getListOfDestinations() {
		return destinationDAO.getListOfDestinations();
	}

	@Override
	@Transactional
	public DestinationModel getDestinationModel(int id) {
		Destination destination= destinationDAO.getDestination(id);
		if(destination!=null) {
			DestinationModel destinationModel= new DestinationModel(destination.getId(),
					destination.getCountry(),
					destination.getCity(),
					destination.getAirport(),
					destination.getLatitude(),
					destination.getLongitude(),
					destination.getDistance());
			return destinationModel;
		}else {
			return null;
		}
		
		
	}


}
