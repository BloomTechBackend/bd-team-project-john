package com.bloomtech.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;
import org.joda.time.DateTime;
@Data
@DynamoDBTable(tableName = "expenses")
public class Expense {
    @DynamoDBHashKey
    private String time;
    @DynamoDBRangeKey
    private String origin;
    @DynamoDBAttribute
    private Integer amount;
}