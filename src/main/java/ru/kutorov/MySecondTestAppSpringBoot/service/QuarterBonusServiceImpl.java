package ru.kutorov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.kutorov.MySecondTestAppSpringBoot.model.Positions;

@Service
public class QuarterBonusServiceImpl implements QuarterBonusService{
    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays){
        if (positions.isManager()){
            if (workDays >= 90){
                return salary * bonus * positions.getPositionCoefficient();
            } else {
                return salary * bonus * workDays * positions.getPositionCoefficient()/ 90;
            }
        }else {
            return 0;
        }
    }
}
