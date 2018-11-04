package designModel.中介者模式;

public class Test {

    public static void main(String[] args) {

        Mediator mediator = new HouseMediator();

        Person renter = new Renter(mediator, "小刘");

        Person landlord = new Landlord(mediator, "王房东");
        Person landlord1 = new Landlord(mediator, "林房东");

        mediator.registerRenter(renter);

        mediator.registerLandlord(landlord);
        mediator.registerLandlord(landlord1);

        renter.sendMessage("在天河公园附近租套房子，价格1000元左右一个月");

    }

}
