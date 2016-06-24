package com.example.alejandrosanchezaristizabal.xmlparserexample.interfaces;

/**
 * Created by alejandrosanchezaristizabal on 22/06/16.
 *
 * <h1>AsyncTaskCompleteListener</h1>
 * Represents a callback mechanism in order to abstract AsyncTasks out into separate classes and
 * avoid inner ones.
 */
public interface AsyncTaskCompleteListener<T> {

  void onTaskComplete(T result);
}
