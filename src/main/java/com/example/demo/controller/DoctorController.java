package com.example.demo.controller;

//public class DoctorController {
//
//}

import java.util.HashMap;
//public class DoctorController {
//
//}
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepository;

//import com.example.in.exception.ResourceNotFoundException;
//import com.example.in.model.Doctor;
//import com.example.in.repository.DoctorRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class DoctorController {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	//get all doctors
	@GetMapping("/doctors")
	public List<Doctor> getAllDoctors(){
		return doctorRepository.findAll();
	}
	
	//create appointment
	@PostMapping("/doctors")
	public Doctor createAppointment(@RequestBody Doctor doctor) {
		return doctorRepository.save(doctor);
		
	}
	
	// get doctor or patient by id rest api
		@GetMapping("/doctors/{phonenumber}")
		public ResponseEntity<Doctor> getEmployeeById(@PathVariable Long phonenumber) {
			Doctor doctor = doctorRepository.findById(phonenumber)
					.orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id :" + phonenumber));
			return ResponseEntity.ok(doctor);
		}
		
	// update employee rest api
		
		@PutMapping("/doctors/{phonenumber}")
		public ResponseEntity<Doctor> updateEmployee(@PathVariable Long phonenumber, @RequestBody Doctor doctorDetails){
			Doctor doctor = doctorRepository.findById(phonenumber)
					.orElseThrow(() -> new ResourceNotFoundException("Appointment not exist with phonenumber :" + phonenumber));
			
			doctor.setFirstname(doctorDetails.getFirstname());
			doctor.setLastname(doctorDetails.getLastname());
			doctor.setEmailid(doctorDetails.getEmailid());
			
			Doctor updatedDoctor = doctorRepository.save(doctor);
			return ResponseEntity.ok(updatedDoctor);
		}
		
		// delete doctor or appointment rest api
		@DeleteMapping("/doctors/{phonenumber}")
		public ResponseEntity<Map<String, Boolean>> deleteDoctor(@PathVariable Long phonenumber){
			Doctor doctor = doctorRepository.findById(phonenumber)
					.orElseThrow(() -> new ResourceNotFoundException("Appointment not exist with phonenumber :" + phonenumber));
			
			doctorRepository.delete(doctor);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
       
		
}


