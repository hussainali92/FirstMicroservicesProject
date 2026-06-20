package com.example.in28mintues.microservices.limits_services.bean;

import org.springframework.context.annotation.Bean;


public class Limits {
    private int minimum ;
    private int maximum ;

    public Limits(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }
}
