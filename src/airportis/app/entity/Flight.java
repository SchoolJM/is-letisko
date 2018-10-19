package airportis.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="flights")
public class Flight {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="takeoff_date")
	private Date takeoff_date;
	
	@Column(name="destination")
	private int destination;
	
	@Column(name="gate")
	private int gate;
	
	@Column(name="plane")
	private String plane;
	
	public Flight() {}

	public Flight(Date takeoff_date, int destination, int gate, String plane) {
		super();
		this.takeoff_date = takeoff_date;
		this.destination = destination;
		this.gate = gate;
		this.plane = plane;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTakeoff_date() {
		return takeoff_date;
	}

	public void setTakeoff_date(Date takeoff_date) {
		this.takeoff_date = takeoff_date;
	}

	public int getDestination() {
		return destination;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}

	public int getGate() {
		return gate;
	}

	public void setGate(int gate) {
		this.gate = gate;
	}

	public String getPlane() {
		return plane;
	}

	public void setPlane(String plane) {
		this.plane = plane;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", takeoff_date=" + takeoff_date + ", destination=" + destination + ", gate=" + gate
				+ ", plane=" + plane + "]";
	}
	
	
	
}
