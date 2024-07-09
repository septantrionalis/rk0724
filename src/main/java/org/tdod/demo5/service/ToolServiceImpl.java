package org.tdod.demo5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdod.demo5.entity.Tool;
import org.tdod.demo5.repository.ToolRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToolServiceImpl implements ToolService {

    @Autowired
    private ToolRepository toolRepository;

    public List<Tool> getAvailableTools(int start, int number) {
        return toolRepository.getAvailableTools(0,4);
    }


}
