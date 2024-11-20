package utils;

import model.enums.CurrencyCode;
import utils.exceptions.CurrencyCodeValidateExeption;

public class CurrencyValidator {
    public static boolean isValidCurrencyCode(String currencyCode){
        boolean currencyValid = false;
        for (CurrencyCode code : CurrencyCode.values()){
            if (code.name().equalsIgnoreCase(currencyCode)) {
                currencyValid = true;
            }
        }
        if (currencyValid == false) {
            String errorMessage = "Введённый код валюты " + currencyCode + " не находится в системе";
            throw new CurrencyCodeValidateExeption(errorMessage);
        }

        return currencyValid;
    }
}
