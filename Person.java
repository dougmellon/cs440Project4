import java.awt.desktop.QuitEvent;
import java.util.*;
public class Person {

    Deque<Person> restroom = new ArrayDeque<>();
    Deque<Person> isReady = new ArrayDeque<>();
    String sumGender = null;
    int id;
    int gender;
    int time;
    int departure;
    int delay;
    int numberAllowedToArrive;
    int maleCounter = 0;
    int femaleCounter = 0;
    Person test = new Person(1,1,1);

    public Person(int id, int gender, int time) {
        this.id = id;
        this.gender = gender;
        this.time = time;
    }

    public int getId() {
        return this.id;
    }

    public int getGender() {
        return this.gender;
    }

    public int getTime() {
        return this.time;
    }

    public void getInfo() {
        System.out.println("ID: " + this.id + " Gender: " + this.gender + " time: " + this.time);
    }

//    public void TestMethod( Queue<Person> people){
//        for(Person peeps: people)
//        {
//            System.out.println("ID: "+peeps.id +" Gender: "+ peeps.gender + " Time: "+peeps.time);
//        }
//    }

    public void Informational(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select one: ");
        System.out.println("a) 5 : DELAY(10): 5 DELAY(10) : 5 DELAY(10) : 5");
        System.out.println("b) 10: DELAY(10) : 10");
        System.out.println("c) 20");
        System.out.println("Enter the letter of which option you would like to choose");
        String choice = scanner.nextLine();
        if(choice.contains("a"))
        {
            this.delay = 10;
            this.numberAllowedToArrive = 5;
        }
        else if(choice.contains("b"))
        {
            this.delay = 10;
            this.numberAllowedToArrive = 10;
        }
    }

    public void BestArrivals(Deque<Person> allPeople) {
        int personCounter = 0;


        // want to take into account numberAllowedToArrive

        maleCounter = 0;
        femaleCounter = 0;
        for(Person person: allPeople)
        {
            if (person.gender == 1) {
                sumGender = "M";
            } else if (person.gender == 0) {
                sumGender = "F";
            }
            System.out.println("Person: " + person.id + " " + person.gender + " "  +sumGender + " has arrived");
        }
        test.UseFacilities(allPeople);
        test.Departures(allPeople);


    }


    public void UseFacilities(Deque<Person> allPeople) {
        // males = 1
        // females = 0
        System.out.println("Arrivals:");

        int maleCounter = 0;
        int femaleCounter = 0;

        for (Person temp : allPeople) {

            if (temp.gender == 1) {
                sumGender = "M";
                maleCounter++;
            } else if (temp.gender == 0) {
                sumGender = "F";
                femaleCounter++;
            }
            if (maleCounter > 2 && femaleCounter > 2)
            {
                System.out.println("Person: "+ temp.id + " Cannot Enter");
            }
            else {
                System.out.println("@t = 0; Person: " + temp.id + " " + sumGender + " enters the facilities for " + temp.time + " minutes");
                restroom.add(temp);
                // adding the people to the restroom list
            }

        }
    }


    public void Departures(Deque<Person> allPeople)
    {
        System.out.println();
        System.out.println("Departures");

        for(Person departed: restroom)
        {
            departure +=1;
            if (departed.gender == 1) {
                sumGender = "M";
            } else if (departed.gender == 0) {
                sumGender = "F";
            }
            System.out.println("@t= "+ this.delay+ "; Person "+ departed.id + " " + sumGender + " exits (departure # " + departure + ")");
        }
    }

    public void Allmethods(Deque<Person> allPeople)
    {

        test.BestArrivals(allPeople);
        test.UseFacilities(allPeople);
        test.Departures(allPeople);
    }
}
