package services;


import model.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


class TicketGeneratorServiceTest {

	private final TicketGeneratorService ticketGeneratorService = new TicketGeneratorService();

	@Test
	void generateTickets() {
		List<Ticket> tickets = ticketGeneratorService.generateTickets();
		Set<Integer> uniqueNumbers = new HashSet<>();
		for (Ticket ticket : tickets) {
			int empty = 0;
			for (List<Integer> row : ticket.getTable()) {
				for (Integer number : row) {
					if (number == null) {
						empty++;
					} else {
						uniqueNumbers.add(number);
					}
				}
			}
			Assertions.assertEquals(12, empty);
		}
		Assertions.assertEquals(90, uniqueNumbers.size());
		System.out.println(uniqueNumbers);
		tickets.forEach(System.out::println);
	}

	// Simple performance test
	@Test
	void generateTicketsPerformanceTest() {
		LocalDateTime start = LocalDateTime.now();
		for (int i = 0; i < 10_000; i++) {
			ticketGeneratorService.generateTickets();
		}
		LocalDateTime finish = LocalDateTime.now();
		Duration duration = Duration.between(start, finish);
		System.out.printf("Nano:%s", duration.getNano());
		System.out.printf(" Seconds:%s", duration.getSeconds());
	}
}