package com.example.moattravel.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.moattravel.entity.House;
import com.example.moattravel.entity.Reservation;
import com.example.moattravel.entity.User;
import com.example.moattravel.form.ReservationInputForm;
import com.example.moattravel.repository.HouseRepository;
import com.example.moattravel.repository.ReservationRepository;
import com.example.moattravel.security.UserDetailsImpl;
import com.example.moattravel.service.ReservationService;

@Controller
public class ReservationController {
	private final ReservationRepository reservationRepository;
	private final HouseRepository houseRepository;
	private final ReservationService reservationService;

	public ReservationController(ReservationRepository reservationRepository, HouseRepository houseRepository,
			ReservationService reservationService) {
		this.reservationRepository = reservationRepository;
		this.houseRepository = houseRepository;
		this.reservationService = reservationService;
	}

	@GetMapping("/reservations")
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			Model model) {
		User user = userDetailsImpl.getUser();
		Page<Reservation> reservationPage = reservationRepository.findByUserOrderByCreatedAtDesc(user, pageable);

		model.addAttribute("reservationPage", reservationPage);

		return "reservations/index";
	}

	@GetMapping("/houses/{id}/reservations/input")
	public String input(
			@PathVariable(name = "id") Integer id,
			@ModelAttribute @Validated ReservationInputForm reservationInputForm,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			Model model) {

		House house = houseRepository.getReferenceById(id);
		Integer numberOfPeople = reservationInputForm.getNumberOfPeople();
		Integer capacity = house.getCapacity();

		model.addAttribute("house", house);
		return "reservations/input";

	}
}
