import java.util.*;

public class Main {
    public static void main (String [] args)
    {

        Deque<Person> allPeople = new ArrayDeque<>();


        Person sean = new Person(1, 1, 12);
        Person grace = new Person(2, 0, 2);
        Person bob = new Person(3, 0, 10);
        Person sean3 = new Person(4, 0, 7);
        Person sean4 = new Person(5, 1, 1);
        Person sean5 = new Person(6, 0, 3);
        Person sean6 = new Person(7, 0, 2);
        Person sean7 = new Person(8, 1, 6);
        Person sean8 = new Person(9, 1, 9);
        Person sean9 = new Person(10, 1, 1);
        allPeople.add(sean);
        allPeople.add(grace);
        allPeople.add(bob);
        allPeople.add(sean3);
        allPeople.add(sean4);
        allPeople.add(sean5);
        allPeople.add(sean6);
        allPeople.add(sean7);
        allPeople.add(sean8);
        allPeople.add(sean9);



        //sean.Informational();
        sean.BestArrivals(allPeople);
        //sean.UseFacilities(allPeople);
        //sean.Departures(allPeople);
        //sean.Allmethods(allPeople);

    }
}
