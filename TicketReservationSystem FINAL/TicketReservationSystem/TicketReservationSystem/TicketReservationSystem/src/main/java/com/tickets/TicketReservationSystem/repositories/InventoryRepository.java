package com.tickets.TicketReservationSystem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tickets.TicketReservationSystem.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}

