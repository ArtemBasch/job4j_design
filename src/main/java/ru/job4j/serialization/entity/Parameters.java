package ru.job4j.serialization.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Parameters {

    @XmlElement(name = "graphicProcessor")
    private String graphicProcessor;
    @XmlElement(name = "microArchitecture")
    private String microArchitecture;
    @XmlElement(name = "techProcess")
    private String techProcess;
    @XmlElement(name = "miningSuitability")
    boolean miningSuitability;

    public Parameters() { }

    public Parameters(String graphicProcessor, String microArchitecture, String techProcess, boolean miningSuitability) {
        this.graphicProcessor = graphicProcessor;
        this.microArchitecture = microArchitecture;
        this.techProcess = techProcess;
        this.miningSuitability = miningSuitability;
    }

    @Override
    public String toString() {
        return "Parameters{"
                + "graphicProcessor=" + graphicProcessor
                + ", microArchitecture=" + microArchitecture
                + ", techProcess=" + techProcess
                + '}';
    }
}
