package com.example.expense;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
 private final AtomicLong ids=new AtomicLong(0);
 private final List<Expense> expenses=new CopyOnWriteArrayList<>(List.of(
  new Expense(ids.incrementAndGet(),"Business Travel","Travel",12000,LocalDate.now().minusDays(3)),
  new Expense(ids.incrementAndGet(),"Client Meeting","Travel",6000,LocalDate.now().minusDays(4)),
  new Expense(ids.incrementAndGet(),"Team Lunch","Food",7250,LocalDate.now().minusDays(5))
 ));
 @GetMapping public List<Expense> all(){return expenses;}
 @PostMapping public Expense add(@RequestBody ExpenseRequest r){
   if(r.amount()<=0) throw new IllegalArgumentException("Amount must be positive");
   Expense e=new Expense(ids.incrementAndGet(),r.description(),r.category(),r.amount(),r.date()==null?LocalDate.now():r.date());
   expenses.add(0,e); return e;
 }
 public record Expense(long id,String description,String category,double amount,LocalDate date){}
 public record ExpenseRequest(String description,String category,double amount,LocalDate date){}
}
