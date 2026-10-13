package ru.kutorov.MySecondTestAppSpringBoot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.kutorov.MySecondTestAppSpringBoot.exception.ValidationFailedException;

@Slf4j
@Service
public class RequestValidationService implements ValidationService {
    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException{
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error ->
                    log.error("error validation: {}", error.getDefaultMessage()));
            throw new
                    ValidationFailedException((bindingResult.getAllErrors().toString()));
        }
    }
}
