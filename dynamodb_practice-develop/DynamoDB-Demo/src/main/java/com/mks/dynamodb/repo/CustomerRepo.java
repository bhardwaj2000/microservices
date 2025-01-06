package com.mks.dynamodb.repo;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.mks.dynamodb.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class CustomerRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Customer saveCustomer(Customer customer) {
        dynamoDBMapper.save(customer);
        return customer;
    }

    public Customer getCustomerById(String customerId) {
        /*   To get costomer by customerId using query method.
       Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":v1",new AttributeValue("f8227ad6-44e0-434a-8a9a-063284ededb7"));
        DynamoDBQueryExpression<Customer> queryExpression= new DynamoDBQueryExpression<Customer>()
                .withKeyConditionExpression("customerId = :v1")
                .withExpressionAttributeValues(eav);

        return dynamoDBMapper.query(Customer.class, queryExpression);
     */
        return dynamoDBMapper.load(Customer.class, customerId);
    }

    public List<Customer> getAllCustomer() {
        List<Customer> customerList = dynamoDBMapper.scan(Customer.class, new DynamoDBScanExpression());
        return customerList;
    }
    public String deleteCustomerById(String customerId) {
        dynamoDBMapper.delete(dynamoDBMapper.load(Customer.class, customerId));
        return "Customer Id : "+ customerId +" Deleted!";
    }

    public String updateCustomer(String customerId, Customer customer) {
        dynamoDBMapper.save(customer,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("customerId",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(customerId)
                                )));
        return customerId;
    }
}
