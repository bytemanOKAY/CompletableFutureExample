package com.base;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {

    public static void main(String[] args) throws Exception{
        List<Map<String, Object>> list = new CopyOnWriteArrayList<>();

        CompletableFuture<Map<String, Object>> thread1 = CompletableFuture.supplyAsync(() -> Parser.getMapForId(1));
        CompletableFuture<Map<String, Object>> thread2 = CompletableFuture.supplyAsync(() -> Parser.getMapForId(2));
        CompletableFuture<Map<String, Object>> thread3 = CompletableFuture.supplyAsync(() -> Parser.getMapForId(3));
        thread1.thenAccept(list::add);
        thread2.thenAccept(list::add);
        thread3.thenAccept(list::add);

        try{
            CompletableFuture.allOf(thread1, thread2, thread3).get(2, TimeUnit.SECONDS);
        } catch (TimeoutException e){
            System.out.println("timeout was reached");
        }

        list.forEach(System.out::print);
    }
}
