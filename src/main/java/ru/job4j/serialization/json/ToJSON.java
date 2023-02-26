package ru.job4j.serialization.json;

import org.json.JSONObject;
import ru.job4j.serialization.Contact;
import ru.job4j.serialization.Person;

public class ToJSON {
    public static void main(String[] args) {
        Contact contact = new Contact(111111, "11-111");
        String[] status = new String[] {"Worker", "Married"};
        final Person person = new Person(false, 30, contact, status);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.isSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", contact);
        jsonObject.put("status", status);

        System.out.println(jsonObject);
        System.out.println(new JSONObject(person));


    }
}
