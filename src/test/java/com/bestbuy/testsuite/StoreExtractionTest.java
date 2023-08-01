package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoreExtractionTest {

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

    //Extract the limit
    @Test

    public void test001() {
        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");

    }

    //Extract the name of 5th store
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th store is : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the names of all the store
    @Test
    public void test004() {
        List<String> storeNames = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all the store : " + storeNames);
        System.out.println("------------------End of Test---------------------------");

    }

    //Extract the storeId of all the store
    @Test
    public void test005() {
        List<Integer> storeIds = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeId of all the store : " + storeIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //Print the size of the data list
    @Test
    public void test006() {
        int size = response.extract().path("data.size");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data list : " + size);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the store where store name St Cloud : " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    // Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<HashMap<String, ?>> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the address of the store where store name is Rochester : " + address);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get all the services of 8th store
    @Test
    public void test009() {
        //Get all the services of 8th store
        List<HashMap<String, ?>> services = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("all the services of 8th store : " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {
        List<HashMap<String, ?>> windowsStore = response.extract().path("data.findAll{it.services.findAll{it.name == 'Windows Store'}}.services.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("store services of the store where service name is Windows Store : " + windowsStore);
        System.out.println("------------------End of Test---------------------------");
    }

    // Get all the storeId of all the store
    @Test
    public void test011() {
        List<HashMap<String, ?>> storeId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the storeId of all the store : " + storeId);
        System.out.println("------------------End of Test---------------------------");

    }

    //Get id of all the store
    @Test
    public void test012() {
        List<Integer> ids = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("id of all stores : " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the store names Where state = ND
    @Test
    public void test013() {
        List<?> storeNames = response.extract().path("data.findAll{it.state == 'ND'}.name");
        //List<?> storeNames = response.extract().path("data.findAll{it.state.findAll('ND')}.name");
        //List<?> storeNames = response.extract().path("data.findAll{it.state == ('ND')}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("store names Where state = ND : " + storeNames);
        System.out.println("------------------End of Test---------------------------");

    }

    // Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {
        List<?> totalServices = response.extract().path("data.findAll{it.name == 'Rochester'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("number of services for the store where store name is Rochester : " + totalServices.size());
        System.out.println("------------------End of Test---------------------------");
    }

    // Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015() {
        List< ?> allServices = response.extract().path("data.findAll{it.services.findAll{it.name == 'Windows Store'}}.services.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("createdAt for all services whose name = “Windows Store” : " + allServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the name of all services Where store name = “Fargo”
    @Test
    public void test016(){

        List<String> storeName = response.extract().path("data.findAll{it.name == 'Fargo'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("name of all services Where store name = “Fargo” : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }
    //Find the zip of all the store
    @Test
    public void test017(){
        List<?> theZip = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the zip of all the store” : " + theZip);
        System.out.println("------------------End of Test---------------------------");
    }
    //Find the zip of store name = Roseville
    @Test
    public void test018(){
        List<HashMap<String, ?>> findZip = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the zip of store name = Roseville” : " + findZip);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019(){
        List<HashMap<String, ?>> name = response.extract().path("data.findAll { it.services.find { it.name == 'Magnolia Home Theater'} != null }.services.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("storeservices details of the service name = Magnolia Home Theater” : " + name);
        System.out.println("------------------End of Test---------------------------");

    }
    //Find the lat of all the stores
    @Test
    public void test020(){
        List<Integer> letitude = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the lat of all the stores” : " + letitude);
        System.out.println("------------------End of Test---------------------------");
    }




}

