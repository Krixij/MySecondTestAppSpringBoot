package ru.kutorov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.kutorov.MySecondTestAppSpringBoot.model.Positions;

@Service
public interface QuarterBonusService {
    double calculate (Positions positions, double salary, double bonus, int workDays);
}
