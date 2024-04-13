package com.example.contentmanagementsystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.contentmanagementsystem.entity.Schedule;
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Integer>{

}
