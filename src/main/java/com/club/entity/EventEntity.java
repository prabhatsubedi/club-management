package com.club.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="event")
public class EventEntity {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "eventName")
	private String eventName;
	
	@Column(name = "place")
	private String place;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<PeopleEntity> peopleEntities;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<PeopleEntity> getPeopleEntities() {
		return peopleEntities;
	}

	public void setPeopleEntities(List<PeopleEntity> peopleEntities) {
		this.peopleEntities = peopleEntities;
	}
	
	
}
