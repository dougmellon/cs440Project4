import java.util.*;


public class Main {

    static ArrayList<Person> waitLine = new ArrayList<>();
    static ArrayList<Person> stall = new ArrayList<>();
    static int idCount = 0;
    static int clock = 1;
    static int otherStall;
    static String genderString;
    static int departureIndex = 0;
    static int counter;

    static int generateGender() {
        Random r = new Random();
        return r.nextInt(2);
    }

    static Person generatePerson() {
        // Generate ID
        idCount += 1;

        // Generate Gender
        int gender = generateGender();

        // Generate Time
        Random rTime = new Random();
        int time = rTime.nextInt(8 - 3 + 1) + 3;

        return new Person(idCount, gender, time);
    }

    static void generateLine(int numberOfPeople) {
        for (int i = 0; i < numberOfPeople; i++) {
            Person temp = generatePerson();

            if (temp.getGender() == 0) {
                genderString = "M";
            } else {
                genderString = "F";
            }

            System.out.printf("@t= %s; Person %s (%s) arrives\n", clock, Integer.toString(temp.getId()), genderString);
            waitLine.add(temp);
        }
    }

    static void arrive(int clock, int index) {

        counter = 0;

        for (int i = 0; i < waitLine.size(); i++) {

            if (index == 0) {
                otherStall = 1;
            } else {
                otherStall = 0;
            }


            if (waitLine.get(i) != null) {

                if (waitLine.get(i).getGender() == stall.get(otherStall).getGender()) {
                    stall.set(index, waitLine.get(i));
                    counter = i;

                    if (waitLine.get(i).getGender() == 0) {
                        genderString = "M";
                    } else {
                        genderString = "F";
                    }

                    System.out.printf("@t= %s; Person %s (%s) has entered the facilities for %s \n", clock, waitLine.get(i).getId(), genderString, waitLine.get(i).getTime());
                    break;
                }
            } else if (waitLine.get(i) == null) {
                arrive(clock, index);
            } else {
                stall.set(index, null);
            }
        }

        waitLine.remove(counter);
    }

    static void depart(int clock, int index) {
        Person temp = stall.get(index);
        System.out.printf("@t= %s, Person %s exits (departure # %s)\n", clock, temp.getId(), departureIndex);

        // fill the stall
        arrive(clock, index);

        departureIndex++;

    }

    public static void main (String [] args) {

        // generate wait line
        generateLine(10);

        // add fist person to stall
        stall.add(waitLine.get(0));
        if (stall.get(0).getGender() == 0) {
            genderString = "M";
        } else {
            genderString = "F";
        }
        System.out.printf("@t= %s; Person %s (%s) has entered the facilities for %s \n", 0, waitLine.get(0).getId(), genderString, waitLine.get(0).getTime());
        waitLine.remove(0);

//
        // add second person to stall
        for (int i = 0; i < waitLine.size(); i++) {
            if (stall.get(0).getGender() == waitLine.get(i).getGender()) {
                stall.add(waitLine.get(i));

                if (stall.get(1).getGender() == 0) {
                    genderString = "M";
                } else {
                    genderString = "F";
                }

                System.out.printf("@t= %s; Person %s (%s) has entered the facilities for %s \n", 0, waitLine.get(i).getId(), genderString, waitLine.get(i).getTime());
                waitLine.remove(i);
                break;
            }
        }

        do {

            if (stall.get(1) != null ) {
                if (stall.get(0).getTime() > 1) {
                    stall.get(0).setTime(stall.get(0).getTime() - 1);
                } else if (stall.get(0) != null) {
                    depart(clock, 0);
                } else {
                    arrive(clock, 0);
                }

                if (stall.get(1).getTime() > 1) {
                    stall.get(1).setTime(stall.get(1).getTime() - 1);
                } else if (stall.get(1) != null) {
                    depart(clock, 1);
                } else {
                    arrive(clock, 1);
                }
            }

            clock++;

        } while (waitLine.size() > 0);
    }
}