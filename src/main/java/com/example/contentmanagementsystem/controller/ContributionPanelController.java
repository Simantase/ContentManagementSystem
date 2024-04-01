package com.example.contentmanagementsystem.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.contentmanagementsystem.entity.ContributionalPanel;
import com.example.contentmanagementsystem.service.ContributionPanelService;
import com.example.contentmanagementsystem.utility.ErrorStructure;
import com.example.contentmanagementsystem.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
@RestController
@AllArgsConstructor
public class ContributionPanelController {
	private ContributionPanelService contributionPanelService;
	@Operation(description = "The EndPoint Is Give Permission To Blog Owner Can Add Other Users As Contributors To The Contribution Panel,"
			+ "Letting Them Write Blog Posts",responses = {
					@ApiResponse(responseCode = "200",description = "Blog Owner Is Added Contributor Successfully"),
					@ApiResponse(responseCode = "400",description = "Invalid Operation!!!",
					content =@Content(schema = @Schema(implementation = ErrorStructure.class)))
	})
	@PutMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<ResponseStructure<ContributionalPanel>> addContributor(@PathVariable int userId,
			@PathVariable int panelId){
		return contributionPanelService.addContributor(userId,panelId);
	}
	@Operation(description = "The EndPoint Is Give Permission To Blog Owner Can Delete Other Users From The Contribution Panel",
			responses = {
			@ApiResponse(responseCode = "200",description = "Blog Owner Is Deleted Contributor Successfully"),
			@ApiResponse(responseCode = "400",description = "Invalid Operation!!!",
			content =@Content(schema = @Schema(implementation = ErrorStructure.class)))
	})
	@DeleteMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<ResponseStructure<ContributionalPanel>> removeUserFromContributionPanel(
			@PathVariable int userId,@PathVariable int panelId){
		return contributionPanelService.removeUserFromContributionPanel(userId,panelId);
	}
}
