package ru.kutorov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.kutorov.MySecondTestAppSpringBoot.model.Request;

@Service
public interface ModifyRequestService {
    void modify(Request request);
}
