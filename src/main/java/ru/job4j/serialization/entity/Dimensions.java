package ru.job4j.serialization.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Dimensions {
    @XmlElement(name = "lowProfile")
    private boolean lowProf;
    @XmlElement(name = "length")
    private int length;
    @XmlElement(name = "width")
    private int width;
    @XmlElement(name = "height")
    private int height;
    @XmlElement(name = "weight")
    private int weight;

    public Dimensions() { }

    public Dimensions(boolean lowProf, int length, int width, int height, int weight) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.lowProf = lowProf;
    }
}
