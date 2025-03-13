package com.tickets.TicketReservationSystem.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tickets.TicketReservationSystem.model.Inventory;
import com.tickets.TicketReservationSystem.model.Reservation;
import com.tickets.TicketReservationSystem.model.Ticket;
import com.tickets.TicketReservationSystem.repositories.InventoryRepository;
import com.tickets.TicketReservationSystem.repositories.ReservationRepository;
import com.tickets.TicketReservationSystem.repositories.TicketRepository;

import jakarta.transaction.Transactional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ReservationRepository reservationRepository;
    private final InventoryRepository inventoryRepository;

    public TicketService(TicketRepository ticketRepository, ReservationRepository reservationRepository,
                         InventoryRepository inventoryRepository) {
        this.ticketRepository = ticketRepository;
        this.reservationRepository = reservationRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public List<Ticket> searchTicketsByDate(LocalDate date) {
        return ticketRepository.findByEventDate(date);
    }

    public List<Ticket> searchTicketsByEventName(String eventName) {
        return ticketRepository.findByEventName(eventName);
    }

    @Transactional
    public Reservation bookTicket(Long ticketId, String username) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        Inventory inventory = ticket.getInventory();
        if (inventory.getAvailableTickets() <= 0) {
            throw new RuntimeException("No tickets available");
        }

        inventory.setAvailableTickets(inventory.getAvailableTickets() - 1);
        inventoryRepository.save(inventory);

        Reservation reservation = new Reservation(null, username, LocalDate.now(), ticket);
        return reservationRepository.save(reservation);
    }

    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Inventory inventory = reservation.getTicket().getInventory();
        inventory.setAvailableTickets(inventory.getAvailableTickets() + 1);
        inventoryRepository.save(inventory);

        reservationRepository.deleteById(reservationId);
    }
}
