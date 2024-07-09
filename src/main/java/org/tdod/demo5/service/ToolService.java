package org.tdod.demo5.service;

import org.tdod.demo5.entity.Tool;

import java.util.List;

public interface ToolService {

    List<Tool> getAvailableTools(int start, int number);

}
