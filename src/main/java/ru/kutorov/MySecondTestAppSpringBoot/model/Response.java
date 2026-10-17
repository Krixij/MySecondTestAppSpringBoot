package ru.kutorov.MySecondTestAppSpringBoot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    /**
     *  Уникальный идентификатор сообщения
     */
    private String uid;
    /**
     *  Уникальный идентификатор операции
     */
    private String operationUid;
    /**
     *  Время создания сообщения
     */
    private String systemTime;
    /**
     *  Код ответа
     */
    private Codes code;
    /**
     *  Код ошибки
     */
    private ErrorCodes errorCode;
    /**
     *  Сообщение об ошибке
     */
    private ErrorMessages errorMessage;
    /**
     *  Годовая премия
     */
    private Double annualBonus;
    /**
     *  Квартальная премия
     */
    private Double quarterBonus;

}
