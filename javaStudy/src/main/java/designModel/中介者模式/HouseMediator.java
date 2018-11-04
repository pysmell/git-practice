package designModel.中介者模式;

public class HouseMediator extends Mediator {
    @Override
    public void operation(Person person, String message) {
        if (person instanceof Renter) {
            for (Person person1 : landlordList) {
                person1.getMessage(message);
            }
        } else {
            for (Person person1 : renterList) {
                person1.getMessage(message);
            }
        }
    }
}
