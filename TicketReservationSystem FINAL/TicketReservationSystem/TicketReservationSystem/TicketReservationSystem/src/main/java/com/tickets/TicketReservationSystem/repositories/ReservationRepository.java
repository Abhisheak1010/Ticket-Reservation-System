package com.tickets.TicketReservationSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickets.TicketReservationSystem.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}

