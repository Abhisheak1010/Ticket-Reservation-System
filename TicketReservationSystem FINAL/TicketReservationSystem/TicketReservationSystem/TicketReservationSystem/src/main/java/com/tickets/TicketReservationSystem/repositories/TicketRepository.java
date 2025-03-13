package com.tickets.TicketReservationSystem.repositories;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickets.TicketReservationSystem.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByEventDate(LocalDate date);

    List<Ticket> findByEventName(String eventName);
}

