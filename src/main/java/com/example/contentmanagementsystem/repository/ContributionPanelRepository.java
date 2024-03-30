package com.example.contentmanagementsystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.contentmanagementsystem.entity.ContributionalPanel;
@Repository
public interface ContributionPanelRepository extends JpaRepository<ContributionalPanel,Integer>{

}
