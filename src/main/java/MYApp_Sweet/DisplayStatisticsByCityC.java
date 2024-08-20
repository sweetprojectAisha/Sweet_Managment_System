package MYApp_Sweet;

import java.util.HashMap;
import java.util.Map;

public class DisplayStatisticsByCityC {
    private static DisplayStatisticsByCityC instance;
    private Map<String, Integer> userCountByCity;
    private Map<String, User> users;

    private DisplayStatisticsByCityC() {
        userCountByCity = new HashMap<>();
        users = new HashMap<>();
    }

    public static DisplayStatisticsByCityC getInstance() {
        if (instance == null) {
            instance = new DisplayStatisticsByCityC();
        }
        return instance;
    }

    public void addUser(String name, String city) {
        User user = new User(name, city);
        users.put(name, user);
        registerUser(city);
        System.out.println("User " + name + " from " + city + " has been registered.");
    }

    private void registerUser(String city) {
        userCountByCity.put(city, userCountByCity.getOrDefault(city, 0) + 1);
    }

    public Map<String, Integer> gatherStatisticsByCity() {
        return new HashMap<>(userCountByCity);
    }

    public static class User {
        private String name;
        private String city;

        public User(String name, String city) {
            this.name = name;
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public String getCity() {
            return city;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCity(String city) {
            this.city = city;
        }

        @Override
        public String toString() {
            return "User{name='" + name + "', city='" + city + "'}";
        }
    }
}
