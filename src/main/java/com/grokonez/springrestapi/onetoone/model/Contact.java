package com.grokonez.springrestapi.onetoone.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "contacts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Contact implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;
    
    @Column(name = "phone")
    private String phone;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    public Contact() {
    }
    
    public Contact(String city, String phone) {
    	this.city = city;
    	this.phone = phone;
    	//this.id=id;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }
    
    public Long getId() {
    	return this.id;
    }
    
    public void setCity(String city) {
    	this.city = city;
    }
    
    public String getCity() {
    	return this.city;
    }
    
    public void setPhone(String phone) {
    	this.phone = phone;
    }
    
    public String getPhone() {
    	return this.phone;
    }
    
    public void setStudent(Student student) {
    	this.student = student;
    }
    
    public Student getStudent() {
    	return this.student;
    }
}
