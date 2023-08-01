package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);

    }

    // Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }


    // Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the name of 5th product
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the name of 5th product" + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the names of all the products
    @Test
    public void test004() {
        List<?> name = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("names of all the products" + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the productId of all the products
    @Test
    public void test005() {
        List<Integer> iD = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the productId of all the products" + iD);
        System.out.println("------------------End of Test---------------------------");
    }

    //Print the size of the data list
    @Test
    public void test006() {
        int list = response.extract().path("data.size");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the size of the data list is" + list);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test007() {
        List<HashMap<String, ?>> value = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)" + value);
        System.out.println("------------------End of Test---------------------------");
    }

    // Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test008() {
        List<HashMap<String, ?>> product = response.extract().path("data.findAll{it.name = 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)" + product);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get all the categories of 8th products
    @Test
    public void test009() {
        List<?> category = response.extract().path("data[7].categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the categories of 8th products" + category);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get categories of the store where product id = 150115
    @Test
    public void test010() {
        List<HashMap<String, ?>> categories = response.extract().path("data.findAll{it.id == '150115'}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get categories of the store where product id = 150115" + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get all the descriptions of all the products
    @Test
    public void test011() {
        List<HashMap<String, ?>> description = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the descriptions of all the products" + description);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get id of all the all categories of all the products
    @Test
    public void test012() {
        List<?> all = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get id of all the all categories of all the products" + all);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the product names Where type = HardGood
    @Test
    public void test013() {
        List<HashMap<String, ?>> type = response.extract().path("data.findAll{it.type == 'HardGood'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the product names Where type = HardGood" + type);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the Total number of categories for the product where product name = Duracell - AA1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test014() {
        List<HashMap<String, ?>> total = response.extract().path("data.findAll{it.name = 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the Total number of categories for the product where product name = Duracell - AA1.5V CopperTop Batteries (4-Pack) is:" + total.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the createdAt for all products whose price < 5.49
    @Test
    public void test015() {
        List<HashMap<String, ?>> all = response.extract().path("data.findAll{it.price < '5.49'}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the createdAt for all products whose price < 5.49:" + all );
        System.out.println("------------------End of Test---------------------------");
    }
    //Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4- Pack)”
    @Test
    public void test016(){
        List<HashMap<String, ?>> product = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.name.categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the name of all categories Where product name = “Energizer - MAX Batteries AA (4- Pack)”:" + product );
        System.out.println("------------------End of Test---------------------------");
    }
    // Find the manufacturer of all the products
    @Test
    public void test017(){
        List<?> all = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the manufacturer of all the products:" + all );
        System.out.println("------------------End of Test---------------------------");
    }
    //Find the imge of products whose manufacturer is = Energizer
    @Test
    public void test018(){
        List<HashMap<String,?>> image = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the imge of products whose manufacturer is = Energizer:" + image );
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test019(){
        List<HashMap<String,?>> all = response.extract().path("data.findAll{it.price > '5.99'}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the createdAt for all categories products whose price > 5.99:" + all );
        System.out.println("------------------End of Test---------------------------");
    }

    //. Find the uri of all the products
    @Test
    public void test020(){
        List<?> all = response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the uri of all the products:" + all );
        System.out.println("------------------End of Test---------------------------");

    }

}