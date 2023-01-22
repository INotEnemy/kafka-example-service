package com.example.kafkaforpractice.navigator;

import java.util.LinkedList;
import com.example.kafkaforpractice.navigator.additioal.Pair;

public class Wave
{
    public int[][] map; // наша целочисленная карта

    public Pair<Integer, Integer> finish; // финишная позиция (когда найдена)
    private LinkedList <Pair<Integer, Integer>> queue; // список вершин

    public Wave (int[][] map)
    {
        this.map = map;
        finish = null;
        queue = new LinkedList<>();
    }

    synchronized public void addToQueue (Pair<Integer, Integer> point)
    {
        this.queue.add(point);
    }

    synchronized public Pair<Integer, Integer> getPoint ()
    {
        return this.queue.poll(); // вытаскиваем первый элемент из очереди в точку
    }

    synchronized public boolean isEmptyQueue ()
    {
        return this.queue.isEmpty();
    }
}
