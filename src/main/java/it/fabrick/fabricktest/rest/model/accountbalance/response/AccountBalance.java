package it.fabrick.fabricktest.rest.model.accountbalance.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountBalance {

    private Date date;
    private Double balance;
    private Double availableBalance;
    private String currency;
}
