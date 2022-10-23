package ru.job4j.analyse;

import java.util.HashMap;
import java.util.Set;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {

        int added;
        int changed = 0;
        int deleted = 0;

        HashMap<Integer, String> actual = new HashMap<>();
        for (User user : current) {
            actual.put(user.getId(), user.getName());
        }

        for (User user : previous){
            if (!actual.containsKey(user.getId())){
                deleted++;
            } else if (actual.containsKey(user.getId()) && !actual.containsValue(user.getName())) {
                changed++;
            }
        }

        added = actual.size() - previous.size() + deleted;

        return new Info(added, changed, deleted);
    }
}