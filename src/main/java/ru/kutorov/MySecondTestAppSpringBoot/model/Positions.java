package ru.kutorov.MySecondTestAppSpringBoot.model;

import lombok.Getter;

@Getter
public enum Positions {
    DEV (2.2, false),
    HR (1.2, false),
    TL (2.6, true),
    PO (5.0, false),
    TPM (3.7, true),
    CTO (4.0, false);

    private final double positionCoefficient;
    private final boolean isManager;

    Positions(double positionCoefficient, boolean isManager){
        this.positionCoefficient = positionCoefficient;
        this.isManager = isManager;
    }
}
