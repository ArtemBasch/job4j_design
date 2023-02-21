package ru.job4j.serialization.entity;

import javax.xml.bind.*;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Device device = new Device("Graphic card", "MSI AMD Radeon RX 6700 XT GAMING X",
                new Parameters("Radeon RX 6700 XT", "AMD RDNA 2", "7nm", false), new Dimensions(false, 279, 131, 58, 1179));
        JAXBContext context = JAXBContext.newInstance(Device.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(device, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Device result = (Device) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
