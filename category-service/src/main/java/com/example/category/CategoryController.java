package com.example.category;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
 private final List<String> categories = new CopyOnWriteArrayList<>(List.of("Travel","Food","Office","Utilities","Training"));
 @GetMapping public List<String> all(){return categories;}
 @PostMapping public List<String> add(@RequestBody Map<String,String> body){
   String name=body.get("name");
   if(name!=null && !name.isBlank() && !categories.contains(name)) categories.add(name);
   return categories;
 }
}
