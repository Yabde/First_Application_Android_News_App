package com.example.appnews.model;

import java.util.ArrayList;

public class SourceList {

    // Idee : Creation de cette classe pour stocker nos differentes valeurs JSON
    // Et pouvoir ensuite l'appeler dans la méthode : spinner.setOnItemSelectedListener(...) de notre spinner

    private ArrayList<Source> source;

    public SourceList() {
        source = new ArrayList<>();
    }

    public ArrayList<Source> getSources() {
        return source;
    }

    public void addSource(Source source){
        this.source.add(source);
    }

    public void clearSources(){
        source.clear();
    }

    public Source get(int i){
        return source.get(i);
    }

    public int size(){
        return source.size();
    }

    private static final SourceList list = new SourceList();
    public static SourceList getInstance(){
        return list;
    }

}
