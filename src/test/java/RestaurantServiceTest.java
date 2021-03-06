import org.junit.jupiter.api.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

     RestaurantService service = new RestaurantService();
     Restaurant restaurant,restaurant1, restaurant2, restaurant3, restaurant4,restaurant5;



    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        assertEquals(restaurant,service.findRestaurantByName("Amelie's cafe"));
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant1 = service.addRestaurant("Chirag's cafe","Cuttack",openingTime,closingTime);
        restaurant1.addToMenu("Sweet corn soup",119);
        restaurant1.addToMenu("Vegetable lasagne", 269);
        assertThrows(restaurantNotFoundException.class,()->service.findRestaurantByName("Pantry d'or"));
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant2 = service.addRestaurant("Shalini's cafe","Chennai",openingTime,closingTime);
        restaurant2.addToMenu("Sweet corn soup",119);
        restaurant2.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Shalini's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant3 = service.addRestaurant("Aditya's cafe","Chennai",openingTime,closingTime);
        restaurant3.addToMenu("Sweet corn soup",119);
        restaurant3.addToMenu("Vegetable lasagne", 269);

        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant4 = service.addRestaurant("Dama MAharaj's cafe","Chennai",openingTime,closingTime);
        restaurant4.addToMenu("Sweet corn soup",119);
        restaurant4.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test

    public void should_return_totalValue_for_selected_items_in_the_menu_for_a_selected_restaurant(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant5 = service.addRestaurant("Chirag's resto","Chennai",openingTime,closingTime);
        restaurant5.addToMenu("Sweet corn soup",100);
        restaurant5.addToMenu("Vegetable lasagne", 200);
        restaurant5.addToMenu("mix veg", 300);
        restaurant5.addToMenu("Paneer Butter Masala", 400);
        List<String> selectedItems = new ArrayList<>();
        selectedItems.add("Vegetable lasagne");
        selectedItems.add("Paneer Butter Masala");

        int total = service.returns_Total_Order_Value_For_Selected_Menu_Items(restaurant5,selectedItems);
        assertEquals(600,total);
    }
}