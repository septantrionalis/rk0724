package org.tdod.demo5.service;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    public String getTest(String str) {
        return String.format("Hello3 %s!", str);

    }


}
