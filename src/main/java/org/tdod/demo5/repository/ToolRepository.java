package org.tdod.demo5.repository;

import org.tdod.demo5.entity.Tool;

import java.util.List;

public interface ToolRepository {

    /**
     * Gets a tool based on the code. Null if its not found.
     * @param code the code of the tool needed.
     * @return a tool based on the code. Null if its not found.
     */
    Tool getToolByCode(String code);

    /**
     * Gets the list of available tools.  Used for testing and not needed for this exercise.
     * @param offset the offset
     * @param size the size of the result needed
     * @return a paginated list of tools.
     */
    List<Tool> getAvailableTools(int offset, int size);

}
