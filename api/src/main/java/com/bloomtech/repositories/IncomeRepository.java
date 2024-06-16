package com.bloomtech.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.bloomtech.entities.Income;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class IncomeRepository {
    @Autowired
    private DynamoDBMapper mapper;

    // Create an income
    public Income save(Income income) {
        mapper.save(income);
        return mapper.load(Income.class, income.getTime(), income.getOrigin());
    }

    //Get the current month expense
    public List<Income> findCurrentMonth() {
        Map<String, AttributeValue> values = new HashMap<String, AttributeValue>();
        String monthStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0).with(TemporalAdjusters.firstDayOfMonth()).toString();
        String monthEnd = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0).with(TemporalAdjusters.lastDayOfMonth()).toString();
        values.put(":low", new AttributeValue().withS(monthStart));
        values.put(":high", new AttributeValue().withS(monthEnd));

        Map<String, String> attributeNames = new HashMap<>();
        attributeNames.put("#t", "time");

        DynamoDBScanExpression condition = new DynamoDBScanExpression()
                .withFilterExpression("#t >= :low and #t <= :high")
                .withExpressionAttributeNames(attributeNames)
                .withExpressionAttributeValues(values);
        return mapper.scan(Income.class, condition);
    }
    public List<Income> findLastMonth() {
        Map<String, AttributeValue> values = new HashMap<String, AttributeValue>();
        String lastMonthStart = LocalDateTime.now().minusMonths(1).withHour(0).withMinute(0).withSecond(0).withNano(0).with(TemporalAdjusters.firstDayOfMonth()).toString();
        String lastMonthEnd = LocalDateTime.now().minusMonths(1).withHour(0).withMinute(0).withSecond(0).withNano(0).with(TemporalAdjusters.lastDayOfMonth()).toString();
        values.put(":low", new AttributeValue().withS(lastMonthStart));
        values.put(":high", new AttributeValue().withS(lastMonthEnd));
        Map<String, String> attributeNames = new HashMap<>();
        attributeNames.put("#t", "time");
        DynamoDBScanExpression condition = new DynamoDBScanExpression()
                .withFilterExpression("#t >= :low and #t <= :high")
                .withExpressionAttributeNames(attributeNames)
                .withExpressionAttributeValues(values);
        return mapper.scan(Income.class, condition);
    }
}
