package domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@NamedQueries({
	@NamedQuery(name="Wedstrijd.getAllWedstrijdenByStadiumId", 
			query = "SELECT w FROM Wedstrijd w WHERE w.stadium_id = :stadium_id "
					+ "ORDER BY w.wedstrijd_id")
	})
@Table(name = "wedstrijd")
public class Wedstrijd implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wedstrijd_id;
	
    private String land1, land2;
    private Timestamp datetime;
   
    @Transient
    private String datum;
    @Transient
    private String aftrap;
    @Transient
    private String voetbalCode1;
    @Transient
    private String voetbalCode2;
    
    private Long stadium_id;
    private int tickets;

    public Long getStadium_id() {
		return stadium_id;
	}
	public void setStadium_id(Long stadium_id) {
		this.stadium_id = stadium_id;
	}
    
    public Wedstrijd() {
    	
    }
    
	public Wedstrijd(Long wedstrijd_id, String land1, String land2, Timestamp datetime, Long stadium_id, int tickets) {
		super();
		this.wedstrijd_id = wedstrijd_id;
		this.land1 = land1;
		this.land2 = land2;
		this.datetime = datetime;
		this.stadium_id = stadium_id;
		this.tickets = tickets;
	}
	
	public String getDatum() {
		Date date = new Date(datetime.getTime());
		String datepattern = "dd MMMM ";
		SimpleDateFormat dateformatter = new SimpleDateFormat(datepattern, new Locale("nl", "BE"));
		String datum=dateformatter.format(date);
		return datum;
	}
	
	public String getAftrap() {
		Date date = new Date(datetime.getTime());
		String datepattern = "k:mm";
		SimpleDateFormat dateformatter = new SimpleDateFormat(datepattern);
		String aftrap=dateformatter.format(date);
		return aftrap;
	}
	
	public int getTickets() {
		return tickets;
	}

	public void setTickets(int tickets) {
		this.tickets = tickets;
	}
	public Long getWedstrijd_id() {
		return wedstrijd_id;
	}

	public void setWedstrijd_id(Long wedstrijd_id) {
		this.wedstrijd_id = wedstrijd_id;
	}

	public String getLand1() {
		return land1;
	}

	public void setLand1(String land1) {
		this.land1 = land1;
	}

	public String getLand2() {
		return land2;
	}

	public void setLand2(String land2) {
		this.land2 = land2;
	}

	public Timestamp getDatetime() {
		return datetime;
	}

	@Override
	public String toString() {
		return "Wedstrijd [wedstrijd_id=" + wedstrijd_id + ", land1=" + land1 + ", land2=" + land2 + ", datetime="
				+ datetime + ", datum=" + getDatum() + ", aftrap=" + getAftrap() + ", tickets=" + tickets + ", stadium_id="
				+ stadium_id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(wedstrijd_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wedstrijd other = (Wedstrijd) obj;
		return Objects.equals(wedstrijd_id, other.wedstrijd_id);
	}

}
