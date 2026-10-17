package ru.kutorov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.kutorov.MySecondTestAppSpringBoot.model.Positions;

import java.time.Year;

@Service
    public class AnnualBonusServiceImpl implements AnnualBonusService{
    @Override
    public  double calculate(Positions positions, double salary, double bonus, int workDays){
        int year = Year.now().getValue();
        int countDays = 0;
        if (year % 4 == 0){
            countDays = 366;
        } else {
            countDays = 365;
        }
        return salary * bonus * countDays * positions.getPositionCoefficient() / workDays;
    }
}
