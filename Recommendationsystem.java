import java.io.*;
import java.util.*;

public class Recommendationsystem{

    public static void main(String[] args) throws Exception {

        Map<Integer, Map<Integer, Integer>> data = new HashMap<>();

        // Read CSV file
        BufferedReader br = new BufferedReader(new FileReader("ratings.csv"));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            int user = Integer.parseInt(parts[0]);
            int item = Integer.parseInt(parts[1]);
            int rating = Integer.parseInt(parts[2]);

            data.putIfAbsent(user, new HashMap<>());
            data.get(user).put(item, rating);
        }
        br.close();

        int targetUser = 1;

        Set<Integer> recommendedItems = new HashSet<>();

        for (int user : data.keySet()) {
            if (user != targetUser) {
                for (int item : data.get(user).keySet()) {
                    if (!data.get(targetUser).containsKey(item)) {
                        recommendedItems.add(item);
                    }
                }
            }
        }

        System.out.println("Recommended items for User " + targetUser + ":");
        for (int item : recommendedItems) {
            System.out.println("Item ID: " + item);
        }
    }
}
