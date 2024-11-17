package com.example.GDSC_hackathon.repository;

import com.example.GDSC_hackathon.domain.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassroomRepo extends JpaRepository <Classroom, Long> {
    @Override
    Optional<Classroom> findById(Long aLong);
}
