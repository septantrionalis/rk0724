package org.tdod.demo5.repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.tdod.demo5.entity.Tool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Repository
public class ToolRepositoryImpl implements ToolRepository {

    private static List<Tool> list = new ArrayList<>();
    {
        list.add(new Tool("CHNS","Chainsaw","Stihl"));
        list.add(new Tool("LADW","Ladder","Werner"));
        list.add(new Tool("JAKD","Jackhammer","DeWalt"));
        list.add(new Tool("JAKR","Jackhammer","Ridgid"));
    }

    public List<Tool> getAvailableTools(int offset, int size) {
        Tool[] paginated = list.stream().skip(offset).limit(size).toArray(Tool[]::new);
        return Arrays.asList(paginated);
    }

}
