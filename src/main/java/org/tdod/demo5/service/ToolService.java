package org.tdod.demo5.service;

import org.tdod.demo5.entity.RentalAgreement;
import org.tdod.demo5.entity.Tool;

import java.time.LocalDate;
import java.util.List;

/**
 * A basic tool api service.
 */
public interface ToolService {

    /**
     * Get a rental agreement based on the requested parameters. If the tool isn't found, null is returned.
     * @param toolCode The tool code.
     * @param rentalDayCount The number of days for which the customer wants to rent the tool. (e.g. 4 days)
     * @param discountPercent As a whole number, 0-100 (e.g. 20 = 20%)
     * @param checkoutDate Check out date
     * @return a rental agreement, or null if the tool cannot be found.
     */
    RentalAgreement getRentalAgreement(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate);

    /**
     * Get a list of available tools. Not needed for this exercise, but used for testing.
     * @param offset the offset tool in the dataset.
     * @param size the size of the dataset.
     * @return a paginated list of tools.
     */
    List<Tool> getAvailableTools(int offset, int size);

}
