package org.tdod.demo5.repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.tdod.demo5.entity.Tool;
import org.tdod.demo5.entity.ToolTypeEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class ToolRepositoryImpl implements ToolRepository {

    private final static List<Tool> list = new ArrayList<>();
    {
        list.add(new Tool("CHNS", ToolTypeEnum.CHAINSAW,"Stihl"));
        list.add(new Tool("LADW", ToolTypeEnum.CHAINSAW,"Werner"));
        list.add(new Tool("JAKD", ToolTypeEnum.JACKHAMMER,"DeWalt"));
        list.add(new Tool("JAKR", ToolTypeEnum.JACKHAMMER,"Ridgid"));
    }

    @Override
    public List<Tool> getAvailableTools(int offset, int size) {
        Tool[] paginated = list.stream().skip(offset).limit(size).toArray(Tool[]::new);
        return Arrays.asList(paginated);
    }

    @Override
    public Tool getToolByCode(String code) {
        Tool tool;
        try {
            tool = list.stream().filter(c -> c.getToolCode().equals(code)).collect(Collectors.toList()).get(0);
        } catch (Exception e) {
            return null;
        }

        return tool;
    }

}
