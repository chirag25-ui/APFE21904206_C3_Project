import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{
        for(Restaurant restaurant:restaurants){
            if(restaurant.getName().equalsIgnoreCase(restaurantName)){
                return restaurant;
            }
        }
        throw new restaurantNotFoundException(restaurantName);

    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public int returns_Total_Order_Value_For_Selected_Menu_Items(Restaurant restaurant,List<String> selectedMenuItems){
        int totalValue=0;
        for(String menuItems:selectedMenuItems){
            for(Item item: restaurant.getMenu()){
                if(item.getName().equals(menuItems)){
                    totalValue +=item.getPrice();
                }
            }
        }
        return totalValue;
    }
}
