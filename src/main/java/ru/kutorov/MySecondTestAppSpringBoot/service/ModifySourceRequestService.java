package ru.kutorov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.kutorov.MySecondTestAppSpringBoot.model.Request;

@Service
public class ModifySourceRequestService implements  ModifyRequestService{
    @Override
    public void modify(Request request){
        request.setSource("Source 1");
    }
}
