package com.grokonez.springrestapi.onetoone.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grokonez.springrestapi.onetoone.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	List<Contact> findByStudentId(Long studentId);	
}
