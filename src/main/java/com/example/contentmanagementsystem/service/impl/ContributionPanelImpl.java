package com.example.contentmanagementsystem.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.contentmanagementsystem.entity.ContributionalPanel;
import com.example.contentmanagementsystem.exception.IllegalAccessRequestException;
import com.example.contentmanagementsystem.exception.PanelNotFoundByIdException;
import com.example.contentmanagementsystem.exception.UserNotFoundByIdException;
import com.example.contentmanagementsystem.repository.BlogRepository;
import com.example.contentmanagementsystem.repository.ContributionPanelRepository;
import com.example.contentmanagementsystem.repository.UserRepository;
import com.example.contentmanagementsystem.service.ContributionPanelService;
import com.example.contentmanagementsystem.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContributionPanelImpl implements ContributionPanelService {
	private UserRepository userRepository;
	private ContributionPanelRepository contributionPanelRepository;
	private BlogRepository blogRepository;
	private ResponseStructure<ContributionalPanel> responseStructure;

	@Override
	public ResponseEntity<ResponseStructure<ContributionalPanel>> addContributor(int userId, int panelId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepository.findByEmail(email).map(owner -> {
			return contributionPanelRepository.findById(panelId).map(panel -> {

				if (!blogRepository.existsByUsersAndContributionalPanel(owner, panel))
					throw new IllegalAccessRequestException("Failed To Add Contributor!!!");

				return userRepository.findById(userId).map(contributor -> {
					if(!panel.getContributors().contains(contributor) && panel.getContributors().contains(owner))
					panel.getContributors().add(contributor);
					ContributionalPanel panel2 = contributionPanelRepository.save(panel);

					return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
							.setStatusMessage("Contributor Is Added In Contributional Panel").setStatusData(panel2));
				}).orElseThrow(() -> new UserNotFoundByIdException("User Is Not Found!!!"));
			}).orElseThrow(() -> new PanelNotFoundByIdException("Panel Is Not Found By Id!!!"));
		}).get();
	}
	@Override
	public ResponseEntity<ResponseStructure<ContributionalPanel>> removeUserFromContributionPanel(int userId,
			int panelId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepository.findByEmail(email).map(owner -> {
			return contributionPanelRepository.findById(panelId).map(panel -> {

				if (!blogRepository.existsByUsersAndContributionalPanel(owner, panel))
					throw new IllegalAccessRequestException("Failed To Add Contributor!!!");

				return userRepository.findById(userId).map(contributor -> {
					panel.getContributors().remove(contributor);
					ContributionalPanel panel2 = contributionPanelRepository.save(panel);

					return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
							.setStatusMessage("Contributor Is Deleted From Contributional Panel")
							.setStatusData(panel2));
				}).orElseThrow(() -> new UserNotFoundByIdException("User Is Not Found!!!"));
			}).orElseThrow(() -> new PanelNotFoundByIdException("Panel Is Not Found By Id!!!"));
		}).get();
	}


}
