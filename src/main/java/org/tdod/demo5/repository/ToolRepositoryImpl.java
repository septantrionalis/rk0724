package org.tdod.demo5.repository;

import org.springframework.stereotype.Repository;
import org.tdod.demo5.entity.Tool;
import org.tdod.demo5.entity.ToolTypeEnum;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ToolRepositoryImpl implements ToolRepository {

    private final static List<Tool> list = new ArrayList<>();
    {
        list.add(new Tool("CHNS", ToolTypeEnum.CHAINSAW,"Stihl"));
        list.add(new Tool("LADW", ToolTypeEnum.LADDER,"Werner"));
        list.add(new Tool("JAKD", ToolTypeEnum.JACKHAMMER,"DeWalt"));
        list.add(new Tool("JAKR", ToolTypeEnum.JACKHAMMER,"Ridgid"));
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

    @Override
    public List<Tool> getAvailableTools(int offset, int size) {
        Tool[] paginated = list.stream().skip(offset).limit(size).toArray(Tool[]::new);
        return Arrays.asList(paginated);
    }

}
