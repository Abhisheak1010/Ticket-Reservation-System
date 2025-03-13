package com.tickets.TicketReservationSystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tickets.TicketReservationSystem.model.Reservation;
import com.tickets.TicketReservationSystem.model.Ticket;
import com.tickets.TicketReservationSystem.service.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/search/date")
    public List<Ticket> searchTicketsByDate(@RequestParam LocalDate date) {
        return ticketService.searchTicketsByDate(date);
    }

    @GetMapping("/search/event")
    public List<Ticket> searchTicketsByEvent(@RequestParam String eventName) {
        return ticketService.searchTicketsByEventName(eventName);
    }

    @PostMapping("/book/{ticketId}")
    public Reservation bookTicket(@PathVariable Long ticketId, @RequestParam String username) {
        return ticketService.bookTicket(ticketId, username);
    }
    
    @DeleteMapping("/cancel/{reservationId}")
    public ResponseEntity<String> cancelReservation(@PathVariable Long reservationId){
    	ticketService.cancelReservation(reservationId);
		return ResponseEntity.ok("Reservation Cancelled Successfully!!");
    	
    }
}

