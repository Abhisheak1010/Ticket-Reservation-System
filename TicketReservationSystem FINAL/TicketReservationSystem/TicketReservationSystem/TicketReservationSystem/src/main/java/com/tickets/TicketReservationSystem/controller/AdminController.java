package com.tickets.TicketReservationSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tickets.TicketReservationSystem.model.Ticket;
import com.tickets.TicketReservationSystem.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/tickets")
    public Ticket addTicket(@RequestBody Ticket ticket) {
        return adminService.addTicket(ticket);
    }

    @PutMapping("/tickets/{ticketId}/price")
    public ResponseEntity<String> updateTicketPrice(@PathVariable Long ticketId, @RequestParam double price) {
        adminService.updateTicketPrice(ticketId, price);
        return ResponseEntity.ok("Ticket price updated successfully for Ticket ID: " + ticketId);
    }

    @PutMapping("/inventory/{inventoryId}")
    public ResponseEntity<String> updateInventory(@PathVariable Long inventoryId, @RequestParam int availableTickets) {
        adminService.manageInventory(inventoryId, availableTickets);
        return ResponseEntity.ok("Inventory updated successfully for Inventory ID: " + inventoryId);
    }
}
