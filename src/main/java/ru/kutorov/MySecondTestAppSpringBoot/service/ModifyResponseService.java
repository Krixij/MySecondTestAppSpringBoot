package ru.kutorov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.kutorov.MySecondTestAppSpringBoot.model.Response;

@Service
public interface ModifyResponseService {
    Response modify(Response response);
}
