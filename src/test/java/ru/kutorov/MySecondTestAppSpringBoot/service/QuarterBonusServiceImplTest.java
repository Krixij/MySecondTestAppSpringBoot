package ru.kutorov.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.kutorov.MySecondTestAppSpringBoot.model.Positions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class QuarterBonusServiceImplTest {

    @Test
    void calculate() {
        double bonus = 3.0;
        double salary = 200000.00;
        int workDays = 43;
        Positions positions = Positions.TL;

        double result = new QuarterBonusServiceImpl().calculate(positions, salary, bonus, workDays);

        double expected = 745333.3333333334;
        assertThat(result).isEqualTo(expected);
    }
}