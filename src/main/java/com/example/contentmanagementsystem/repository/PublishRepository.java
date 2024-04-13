package com.example.contentmanagementsystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.contentmanagementsystem.entity.Publish;
@Repository
public interface PublishRepository extends JpaRepository<Publish,Integer> {

}
