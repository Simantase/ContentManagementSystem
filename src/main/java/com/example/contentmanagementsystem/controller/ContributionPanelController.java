package com.example.contentmanagementsystem.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.contentmanagementsystem.entity.ContributionalPanel;
import com.example.contentmanagementsystem.service.ContributionPanelService;
import com.example.contentmanagementsystem.utility.ResponseStructure;

import lombok.AllArgsConstructor;
@RestController
@AllArgsConstructor
public class ContributionPanelController {
	private ContributionPanelService contributionPanelService;
	@PutMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<ResponseStructure<ContributionalPanel>> addContributor(@PathVariable int userId,
			@PathVariable int panelId){
		return contributionPanelService.addContributor(userId,panelId);
	}
	@DeleteMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<ResponseStructure<ContributionalPanel>> removeUserFromContributionPanel(
			@PathVariable int userId,@PathVariable int panelId){
		return contributionPanelService.removeUserFromContributionPanel(userId,panelId);
	}
}
