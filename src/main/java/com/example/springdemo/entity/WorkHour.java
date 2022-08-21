package com.example.springdemo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="work_hour")
public class WorkHour {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="start_hour")
    private Date startHour;

    @Column(name="end_hour")
    private Date endHour;

    @Column(name="work_place")
    private String workPlace;
	

    

	public WorkHour() {
	}

    

	@Override
	public String toString() {
		return "WorkHour [endHour=" + endHour + ", id=" + id + ", startHour=" + startHour + ", workPlace=" + workPlace
				+ "]";
	}



	public WorkHour(Date startHour, Date endHour, String workPlace) {
		this.startHour = startHour;
		this.endHour = endHour;
		this.workPlace = workPlace;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartHour() {
		return startHour;
	}

	public void setStartHour(Date startHour) {
		this.startHour = startHour;
	}

	public Date getEndHour() {
		return endHour;
	}

	public void setEndHour(Date endHour) {
		this.endHour = endHour;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

    


}
