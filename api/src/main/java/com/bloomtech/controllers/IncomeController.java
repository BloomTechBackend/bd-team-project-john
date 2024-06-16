package com.bloomtech.controllers;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.bloomtech.entities.Income;
import com.bloomtech.exceptions.InvalidPathParameterException;
import com.bloomtech.exceptions.UnsucessfullDatabaseOperationException;
import com.bloomtech.repositories.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/incomes")
@CrossOrigin(origins="*")
public class IncomeController {
    @Autowired
    private IncomeRepository incomeRepository;

    @PostMapping
    public Income save(@RequestBody Income income) {
        Income incomeReturned = null;
        try {
            incomeReturned = incomeRepository.save(income);
        } catch (AmazonDynamoDBException ex) {
            throw new UnsucessfullDatabaseOperationException("The database have not successfully completed the operation");
        }
        return incomeReturned;
    }

    @GetMapping
    public List<Income> findGet(@RequestParam(required=false) String period) {
        if (period == null || period.equals("default") || period.equals("current")){
            return incomeRepository.findCurrentMonth();
        } else if(period.equals("last")){
            return incomeRepository.findLastMonth();
        }
        else{
            throw new InvalidPathParameterException("The period path parameter is invalid");
        }
    }
}

