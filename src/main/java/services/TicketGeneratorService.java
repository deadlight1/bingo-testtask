package services;

import model.Ticket;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class TicketGeneratorService {

	//List -> Map (n to const)
	private final List<Queue<Integer>> uniqueNumbers = new ArrayList<>();

	// Not recommend change constants algorithm works for usual bingo (6 tickets, 9 strips, 3 column)
	private final int TICKETS_QUANTITY = 6;
	private final int ROW_QUANTITY = 9;
	private final int COLUMN_QUANTITY = 3;

	private List<Ticket> generate() {
		List<Ticket> tickets = new ArrayList<>();
		for (int i = 0; i < TICKETS_QUANTITY; i++) {
			// create ticket and table for it
			Ticket ticket = new Ticket();
			List<List<Integer>> table = new ArrayList<>();

			// fill the ticket table
			for (int k = 0; k < ROW_QUANTITY; k++) {
				List<Integer> column = new ArrayList<>();
				for (int j = 0; j < COLUMN_QUANTITY; j++) {
					if (j == 0) {
						column.add(null);
					// add second blank (hardcoded for 6 tickets)
					} else if (((i <= 1 && k < 3)
							|| (i >= 2 && i <= 3 && k >= 3 && k <= 5)
							|| (i >= 4 && k >= 6))
							&& j == 1) {
						column.add(null);
					} else {
						column.add(getRandomNumber(k));
					}
				}
				table.add(column);
			}

			ticket.setTable(table);
			tickets.add(ticket);
		}
		return tickets;
	}

	private Integer getRandomNumber(int row) {
		return uniqueNumbers.get(row).poll();
	}

	// generate 90 unique numbers
	private void fillNumbers() {
		List<Integer> row = new ArrayList<>();
		for (int i = 1; i < 91; i++) {
			row.add(i);
			if (row.size() % 10 == 0) {
				Queue<Integer> queue = new LinkedList<>(row);
				uniqueNumbers.add(queue);
				row = new ArrayList<>();
			}
		}
	}

	public List<Ticket> generateTickets() {
		fillNumbers();
		return generate();
	}
}
