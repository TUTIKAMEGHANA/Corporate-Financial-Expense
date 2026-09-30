package com.example.report;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
 private final RestClient client;
 public ReportController(RestClient client){this.client=client;}
 @GetMapping("/summary")
 public Map<String,Object> summary(){
   Expense[] expenses=client.get().uri("http://EXPENSE-SERVICE/api/expenses").retrieve().body(Expense[].class);
   if(expenses==null) expenses=new Expense[0];
   double total=Arrays.stream(expenses).mapToDouble(Expense::amount).sum();
   Map<String,Double> byCategory=Arrays.stream(expenses).collect(Collectors.groupingBy(Expense::category,Collectors.summingDouble(Expense::amount)));
   return Map.of("budget",100000,"totalExpenses",total,"remainingBudget",100000-total,"transactions",expenses.length,"byCategory",byCategory);
 }
 record Expense(long id,String description,String category,double amount,String date){}
}
