package com.example.loadbalancerl7.interfaces;


public interface WorkerPool {


      void add(Worker worker);

      void remove(Worker worker);

      int size();

      Worker getWorker(String identifier);

}
