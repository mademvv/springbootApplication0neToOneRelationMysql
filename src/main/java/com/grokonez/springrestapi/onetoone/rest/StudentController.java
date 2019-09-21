package com.grokonez.springrestapi.onetoone.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grokonez.springrestapi.onetoone.exception.NotFoundException;
import com.grokonez.springrestapi.onetoone.jpa.StudentRepository;
import com.grokonez.springrestapi.onetoone.model.Student;

@RestController
@RequestMapping("api")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	@RequestMapping("/")
	public String getFrontPage(Model model) {
		model.addAttribute("successMsg", "Welcome to Output Systems");
		
		return "FrontPage";
	}
	
	@RequestMapping("/Homepage")
	public String getHomePage(Model model) {
		model.addAttribute("successMsg", "Welcome to Output Systems");
		
		return "Homepage";
	}
    @GetMapping("/students")
    public List<Student> getAllStudents() {
    	return studentRepository.findAll();
    }
    
    @GetMapping("/students/{id}")
    public Student getStudentByID(@PathVariable Long id) {
    	Optional<Student> optStudent = studentRepository.findById(id);
    	if(optStudent.isPresent()) {
    		return optStudent.get();
    	}else {
    		throw new NotFoundException("Student not found with id " + id);
    	}
    }
    
    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student) {
    	
        return studentRepository.save(student);
    }
    
    @PutMapping("/students/{id}")
    public Student updatec(@PathVariable Long id,
                                   @Valid @RequestBody Student studentUpdated) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(studentUpdated.getName());
                    student.setAge(studentUpdated.getAge());
                    return studentRepository.save(student);
                }).orElseThrow(() -> new NotFoundException("Student not found with id " + id));
    }
    
    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        return studentRepository.findById(id)
                .map(student -> {
                    studentRepository.delete(student);
                    return "Delete Successfully!";
                }).orElseThrow(() -> new NotFoundException("Student not found with id " + id));
    }
}