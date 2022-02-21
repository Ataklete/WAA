package com.waa.lab2.controller;

import com.waa.lab2.model.Item;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class Product {

    @GetMapping("/addproductpage")
    public ModelAndView getProduct(HttpSession session){
        Map<String,Item> item = new HashMap<>();
        item.put("item", new Item());
        return new ModelAndView("addproductpage", item);
    }
    @PostMapping("/products")
    public ModelAndView postProduct(@Valid  HttpSession session, @ModelAttribute("item") Item item, BindingResult bindingResult){
        Map<String, Object> params = new HashMap<>();
        if (bindingResult.hasErrors()) {
            return new ModelAndView("addproductpage", params);
        }
        Map<String, Object> items = new HashMap<>();
        if (item != null) {
            Map<String, Item> itemList = (Map<String, Item>) session.getAttribute("itemList");
            if (itemList == null) {
                itemList = new HashMap<String, Item>();
                session.setAttribute("itemList", itemList);
            }
            itemList.put(item.getProductNumber(), item);
            items.put("itemList", itemList.values());
        }
        return new ModelAndView("product", items);
    }
    @PostMapping("/removeProduct")
    public ModelAndView deleteProduct(HttpSession session, @RequestParam("productNumber") String productNumber){
        Map<String, Object> items = new HashMap<>();
        Map<String, Item> itemList = (Map<String, Item>) session.getAttribute("itemList");
        itemList.remove(productNumber);
        items.put("itemList", itemList.values());
        return new ModelAndView("product", items);
    }

    @PostMapping("/showCart")
    public ModelAndView showCart(HttpSession session, @RequestParam("productNumber") String productNumber){
        Map<String, Integer> items = new HashMap<>();
        Map<String, Item> itemList = (Map<String, Item>) session.getAttribute("itemList");
        for (int j =0; j<=items.size(); j++){
            if(!items.containsKey(productNumber)){
                items.put(productNumber,1);
            }
            items.put(productNumber,items.get(productNumber) + 1);
        }
        Map<String,Object> item2 = new HashMap<>();
        item2.put("itemList", itemList.values());
        item2.put("quantity",items.get(productNumber));
        return new ModelAndView("showCart", items);
    }

    @GetMapping("/health")
    public String health() {
     return "\"healthy\": true";
    }

}
