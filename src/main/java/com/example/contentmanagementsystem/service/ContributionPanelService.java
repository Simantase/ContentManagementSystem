package com.example.contentmanagementsystem.service;
import org.springframework.http.ResponseEntity;
import com.example.contentmanagementsystem.entity.ContributionalPanel;
import com.example.contentmanagementsystem.utility.ResponseStructure;
public interface ContributionPanelService {

	ResponseEntity<ResponseStructure<ContributionalPanel>> addContributor(int userId, int panelId);
	ResponseEntity<ResponseStructure<ContributionalPanel>> removeUserFromContributionPanel(int userId, int panelId);

}
