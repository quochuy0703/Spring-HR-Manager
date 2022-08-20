package com.example.springdemo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="is_work")
    private boolean isWork;

    @Column(name="annual_leave")
    private float annualLeave;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="current_work")
    private WorkHour currentWork;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private List<WorkHour> workHours;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private List<AnnualLeave> annualLeaves;


    public void addWorkHour(WorkHour workHour){

        if(workHours == null){
            workHours = new ArrayList<>();
        }

        workHours.add(workHour);
    }

    public void addWorkHour(AnnualLeave annualLeave){

        if(annualLeaves == null){
            annualLeaves = new ArrayList<>();
        }

        annualLeaves.add(annualLeave);
    }


    public List<WorkHour> getWorkHours() {
        return workHours;
    }



    public void setWorkHours(List<WorkHour> workHours) {
        this.workHours = workHours;
    }



    public User() {
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", firstName=" + firstName + ", id=" + id + ", isWork=" + isWork + ", lastName="
                + lastName + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isWork() {
        return isWork;
    }

    public void setWork(boolean isWork) {
        this.isWork = isWork;
    }










    public WorkHour getCurrentWork() {
        return currentWork;
    }










    public void setCurrentWork(WorkHour currentWork) {
        this.currentWork = currentWork;
    }




    public float getAnnualLeave() {
        return annualLeave;
    }



    public void setAnnualLeave(float annualLeave) {
        this.annualLeave = annualLeave;
    }


    


    

}
