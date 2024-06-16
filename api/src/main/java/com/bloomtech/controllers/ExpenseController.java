package com.bloomtech.controllers;

import com.bloomtech.entities.Expense;
import com.bloomtech.exceptions.InvalidPathParameterException;
import com.bloomtech.exceptions.UnsucessfullDatabaseOperationException;
import com.bloomtech.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@CrossOrigin(origins="*")
public class ExpenseController {
    @Autowired
    private ExpenseRepository expenseRepository;

    @PostMapping
    public Expense save(@RequestBody Expense expense) { return expenseRepository.save(expense); }

    @GetMapping
    public List<Expense> findGet(@RequestParam(required=false) String period) {
        if (period == null || period.equals("default") || period.equals("current")){
            return expenseRepository.findCurrentMonth();
        } else if(period.equals("last")){
            return expenseRepository.findLastMonth();
        }
        else{
            throw new InvalidPathParameterException("The period path parameter is invalid");
        }
    }
}

