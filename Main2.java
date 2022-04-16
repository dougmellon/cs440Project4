import java.util.*;

public class Main2 {
    public static void main (String [] args)
    {

        Deque<Person2> allPeople = new ArrayDeque<>();


        Person2 sean = new Person2(1, 1, 12);
        Person2 grace = new Person2(2, 0, 2);
        Person2 bob = new Person2(3, 0, 10);
        Person2 sean3 = new Person2(4, 0, 7);
        Person2 sean4 = new Person2(5, 1, 1);
        Person2 sean5 = new Person2(6, 0, 3);
        Person2 sean6 = new Person2(7, 0, 2);
        Person2 sean7 = new Person2(8, 1, 6);
        Person2 sean8 = new Person2(9, 1, 9);
        Person2 sean9 = new Person2(10, 1, 1);
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
