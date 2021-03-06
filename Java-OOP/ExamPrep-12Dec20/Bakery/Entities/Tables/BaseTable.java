package bakery.entities.tables;

import bakery.common.ExceptionMessages;

import bakery.entities.bakedFoods.interfaces.BakedFood;

import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.interfaces.Table;

import java.util.*;

public abstract class BaseTable implements Table {
    private Collection<BakedFood> foodOrders;
    private Collection<Drink> drinkOrders;
    private int tableNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    protected BaseTable(int tableNumber, int capacity, double pricePerPerson) {
        this.setTableNumber(tableNumber);
        this.setCapacity(capacity);
        this.setPricePerPerson(pricePerPerson);
        this.foodOrders = new ArrayList<>();
        this.drinkOrders = new ArrayList<>();
        this.price = 0;

    }

    private void setNumberOfPeople(int numberOfPeople) {
        if(numberOfPeople<=0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    private void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    private void setCapacity(int capacity) {
        if(capacity<0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
    }

    private void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    @Override
    public int getTableNumber() {
        return this.tableNumber;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double getPricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReserved() {
        return this.isReserved;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void reserve(int numberOfPeople) {
        this.setNumberOfPeople(numberOfPeople);
        this.isReserved = true;
        this.price = numberOfPeople*getPricePerPerson();
    }

    @Override
    public void orderFood(BakedFood food) {
        this.foodOrders.add(food);
    }

    @Override
    public void orderDrink(Drink drink) {
        this.drinkOrders.add(drink);
    }

    @Override
    public double getBill() {
        double bill =getPrice();
        bill+=this.foodOrders.stream().mapToDouble(BakedFood::getPrice).sum();
        bill+=this.drinkOrders.stream().mapToDouble(Drink::getPrice).sum();
        return bill;
    }

    @Override
    public void clear() {
        this.foodOrders.clear();
        this.drinkOrders.clear();
        this.isReserved = false;
        this.price=0;
    }

    @Override
    public String getFreeTableInfo() {

        return String.format("Table: %d%n" +
                "Type: %s%n" +
                "Capacity: %d%n" +
                "Price per Person: %.2f%n",this.tableNumber,this.getClass().getSimpleName(),this.capacity,this.pricePerPerson);
    }

}
