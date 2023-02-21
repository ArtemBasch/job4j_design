package ru.job4j.serialization.entity;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "device")
public class Device {
    @XmlAttribute
    private String type;
    @XmlAttribute
    private String model;
    @XmlElement
    private Parameters parameters;
    @XmlElement
    private Dimensions dimensions;

    public Device() { }
    public Device(String type, String model, Parameters parameters, Dimensions dimensions) {
        this.type = type;
        this.model = model;
        this.parameters = parameters;
        this.dimensions = dimensions;
    }
    @Override
    public String toString() {
        return "Device{"
                + "type=" + type
                + ", model=" + model
                + ", parameters=" + parameters
                + ", dimensions=" + dimensions
                + '}';
    }
}
