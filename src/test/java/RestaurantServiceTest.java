import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    Restaurant restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);


    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        restaurant.addToMenu("Sweet corn soup",119);
        Restaurant restaurantByName = service.findRestaurantByName("Amelie's cafe");
        assertTrue(restaurantByName instanceof  Restaurant);
    }

    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        Restaurant restaurantByName = service.findRestaurantByName("Amelies cafe");
        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        restaurant.addToMenu("Sweet corn soup",119);
        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}