package com.example.GDSC_hackathon.controller;

import com.example.GDSC_hackathon.domain.Classroom;
import com.example.GDSC_hackathon.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassroom(@PathVariable Long id) {
        return ResponseEntity.notFound().build();
    }
}