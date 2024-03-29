package airportis.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import airportis.app.entity.FlightTicket;

	@Repository
	public class FlightTicketDAOImpl implements FlightTicketDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void save(FlightTicket flightTicket) {
		Session session= sessionFactory.getCurrentSession();
		session.saveOrUpdate(flightTicket);
	}

	@Override
	public void remove(FlightTicket flightTicket) {
		Session session= sessionFactory.getCurrentSession();
		session.remove(flightTicket);
		
	}

	@Override
	public FlightTicket getFlightTicket(int id) {
		Session session= sessionFactory.getCurrentSession();
		FlightTicket flightTicket= session.get(FlightTicket.class, id);
		return flightTicket;
	}

	@Override
	public int getEconomicTickets(int id) {
		Session session= sessionFactory.getCurrentSession();
		Query<FlightTicket> flightTicket= session.createQuery("from FlightTicket where seatClass = 'Economic' and flight= :id", FlightTicket.class);
		System.out.println("ID "+ id);
		flightTicket.setParameter("id", id);
		return flightTicket.getResultList().size();
	}

	@Override
	public int getBusinessTickets(int id) {
		Session session= sessionFactory.getCurrentSession();
		Query<FlightTicket> flightTicket= session.createQuery("from FlightTicket where seatClass = 'Business' and flight= :id", FlightTicket.class);
		flightTicket.setParameter("id", id);
		return flightTicket.getResultList().size();
	}

	@Override
	public int getFirstTickets(int id) {
		Session session= sessionFactory.getCurrentSession();
		Query<FlightTicket> flightTicket= session.createQuery("from FlightTicket where seatClass = 'First' and flight= :id", FlightTicket.class);
		flightTicket.setParameter("id", id);
		return flightTicket.getResultList().size();
	}

	@Override
	public List<FlightTicket> getAllTickets(String identificationNumber) {
		Session session= sessionFactory.getCurrentSession();
		Query<FlightTicket> flightTicket= session.createQuery("from FlightTicket where userIdentificationNumber = :userIdentificationNumber", FlightTicket.class);
		flightTicket.setParameter("userIdentificationNumber", identificationNumber);
		return flightTicket.getResultList();
	}

}
