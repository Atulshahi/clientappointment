package com.example.demo.repository;

//public interface DoctorRepository {
//
//}

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Doctor;

//import com.example.in.model.Doctor;

//import com.example.demo.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	

}
