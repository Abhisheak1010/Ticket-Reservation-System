package com.tickets.TicketReservationSystem.service;

import org.springframework.stereotype.Service;

import com.tickets.TicketReservationSystem.model.Inventory;
import com.tickets.TicketReservationSystem.model.Ticket;
import com.tickets.TicketReservationSystem.repositories.InventoryRepository;
import com.tickets.TicketReservationSystem.repositories.TicketRepository;

@Service
public class AdminService {
    private final TicketRepository ticketRepository;
    private final InventoryRepository inventoryRepository;

    public AdminService(TicketRepository ticketRepository, InventoryRepository inventoryRepository) {
        this.ticketRepository = ticketRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public Ticket addTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    } 

    public void updateTicketPrice(Long ticketId, double price) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setPrice(price);
        ticketRepository.save(ticket);
    }

    public void manageInventory(Long inventoryId, int availableTickets) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        inventory.setAvailableTickets(availableTickets);
        inventoryRepository.save(inventory);
    }
}

