package cs.ems.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "Event")
public class Event {
	@Id
	@Column(name="id")
	private String id;
	@Column(name="duration")
	private int duration;
	@Column(name="event_type", nullable = true)
	private String type;
	@Column(name="host", nullable = true)
	private String host;
	@Column(name="alert")
	private boolean alert;
	
	public Event() {
		super();
	}
	
	public Event(String id, int duration, String type, String host, boolean alert) {
		super();
		this.id = id;
		this.duration = duration;
		this.type = type;
		this.host = host;
		this.alert = alert;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public boolean isAlert() {
		return alert;
	}
	public void setAlert(boolean alert) {
		this.alert = alert;
	}
	

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	
}
