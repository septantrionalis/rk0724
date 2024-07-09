package org.tdod.demo5.repository;

import org.tdod.demo5.entity.Tool;

import java.util.List;

public interface ToolRepository {

    List<Tool> getAvailableTools(int offset, int size);

}
