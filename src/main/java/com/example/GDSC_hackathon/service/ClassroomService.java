package com.example.GDSC_hackathon.service;

import com.example.GDSC_hackathon.domain.Classroom;
import com.example.GDSC_hackathon.repository.ClassroomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ClassroomService {
    @Autowired
    private ClassroomRepo classroomRepo;
    public Classroom updateClassroom(Classroom updatedClassroom, Integer n) {
        //class room 받음 이거를 업데이트를 해줘야됨
        updatedClassroom.changeReservecdSeat(n);
        return classroomRepo.save(updatedClassroom);
    }
}
