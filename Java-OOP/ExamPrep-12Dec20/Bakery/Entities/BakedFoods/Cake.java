package bakery.entities.bakedFoods;

public class Cake extends BaseFood {
    private static final double InitialCakePortion=245;
    public Cake(String name, double price) {
        super(name,InitialCakePortion, price );
    }
}
