import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {

    static ArrayList<Person> waitLine = new ArrayList<>();
    static ArrayList<Person> stall = new ArrayList<>();
    static int idCount = 0;
    static int clock = 1;
    static int otherStall;
    static String genderString;
    static int departureIndex = 0;
    static int counter;
    static int stall0Index = 0;
    static int stall1Index = 0;
    static int optionSwitch;

    static int generateGender() {

        List<Integer> range = IntStream.rangeClosed(0, 100)
                .boxed().collect(Collectors.toList());


        Random r = new Random();

        if (r.nextInt() <= 60) {
            return 1;
        } else {
            return 0;
        }

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

    static void generateSet(int amount) {
        for (int i = 0; i < amount; i++) {
            Person newPerson = generatePerson();
            waitLine.add(newPerson);

            if (newPerson.getGender() == 0) {
                genderString = "M";
            } else {
                genderString = "F";
            }

            System.out.printf("@t= %s; Person %s (%s) arrives\n", clock, Integer.toString(newPerson.getId()), genderString);
        }
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

    static void arrival(int stallNumber, int clock) {

        String tempGender;

        if (stallNumber == 0) {

            for (int i = 0; i < waitLine.size(); i++) {
                if (stall.get(1) == null || waitLine.get(i).getGender() == stall.get(1).getGender()) {

                    if (waitLine.get(i).getGender() == 0) {
                        tempGender = "M";
                    } else {
                        tempGender = "F";
                    }

                    System.out.printf("t= %s; Person %s (%s) enters the facilities for %s minutes\n", clock, waitLine.get(i).getId(), tempGender, waitLine.get(i).getTime());
                    stall.set(stallNumber, waitLine.get(i));
                    waitLine.remove(i);
                    break;
                } else {
                    stall.set(0, null);
                }
            }

        } else if (stallNumber == 1){

            for (int i = 0; i < waitLine.size(); i++) {
                if (stall.get(0) == null || waitLine.get(i).getGender() == stall.get(0).getGender()) {

                    if (waitLine.get(i).getGender() == 0) {
                        tempGender = "M";
                    } else {
                        tempGender = "F";
                    }

                    System.out.printf("t= %s; Person %s (%s) enters the facilities for %s minutes\n", clock, waitLine.get(i).getId(), tempGender, waitLine.get(i).getTime());
                    stall.set(stallNumber, waitLine.get(i));
                    waitLine.remove(i);
                    break;
                } else {
                    stall.set(1, null);
                }
            }
        }
    }

    static void depart(int stallNumber, int clock) {

        String tempGender;

        if (stall.get(stallNumber).getGender() == 0) {
            tempGender = "M";
        } else {
            tempGender = "F";
        }

        departureIndex++;

        System.out.printf("t= %s; Person %s (%s) exits (departure # %s)\n", clock, stall.get(stallNumber).getId(), tempGender, departureIndex);
        arrival(stallNumber, clock);
    }

    static void useFacilities() {
        // iterate through the line
        while (waitLine.size() > 0) {

            // if the first stall is empty
            if (stall.get(0) == null) {
                arrival(0, clock);
            }

            // if the second stall is empty
            if (stall.get(1) == null) {
                arrival(1, clock);
            }

            // if the first stall is not empty
            if (stall.get(0) != null) {

                if (stall.get(0).getTime() == 1) {
                    depart(0, clock);
                } else {
                    stall.get(0).setTime(stall.get(0).getTime() - 1);
                }
            }

            // if the second stall is not empty
            if (stall.get(1) != null) {

                if (stall.get(1).getTime() == 1) {
                    depart(1, clock);
                } else {
                    stall.get(1).setTime(stall.get(1).getTime() - 1);
                }
            }

            clock++;

            if (optionSwitch == 1) {
                if (clock == 10) {
                    generateSet(5);
                } else if (clock == 20) {
                    generateSet(5);
                } else if (clock == 30) {
                    generateSet(5);
                }
            } else if (optionSwitch == 2) {
                if (clock == 10) {
                    generateSet(10);
                }
            }
        }
    }


    public static void main (String [] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Select option from below:");
        System.out.println("(a) 5 : DELAY(10) : 5 : DELAY(10) : 5 : DELAY(10) : 5");
        System.out.println("(b) 10 : DELAY(10) : 10");
        System.out.println("(c) 20");
        String choice = scanner.nextLine();

        switch (choice) {
            case "a" -> optionSwitch = 1;
            case "b" -> optionSwitch = 2;
            case "c" -> optionSwitch = 3;
            default -> System.exit(0);
        }

        // allow first two people to enter
        if (optionSwitch == 1) {
            generateLine(5);

            // add fist person to stall
            stall.add(waitLine.get(0));
            if (stall.get(0).getGender() == 0) {
                genderString = "M";
            } else {
                genderString = "F";
            }

            System.out.printf("@t= %s; Person %s (%s) has entered the facilities for %s \n", 1, waitLine.get(0).getId(), genderString, waitLine.get(0).getTime());
            waitLine.remove(0);

            // add second person to stall
            for (int i = 0; i < waitLine.size(); i++) {
                if (stall.get(0).getGender() == waitLine.get(i).getGender()) {
                    stall.add(waitLine.get(i));

                    if (stall.get(1).getGender() == 0) {
                        genderString = "M";
                    } else {
                        genderString = "F";
                    }

                    System.out.printf("@t= %s; Person %s (%s) has entered the facilities for %s \n", 1, waitLine.get(i).getId(), genderString, waitLine.get(i).getTime());
                    waitLine.remove(i);
                    break;
                }
            }

            useFacilities();

        } else if (optionSwitch == 2){

            generateSet(10);

            // add fist person to stall
            stall.add(waitLine.get(0));
            if (stall.get(0).getGender() == 0) {
                genderString = "M";
            } else {
                genderString = "F";
            }

            System.out.printf("@t= %s; Person %s (%s) has entered the facilities for %s \n", 1, waitLine.get(0).getId(), genderString, waitLine.get(0).getTime());
            waitLine.remove(0);

            // add second person to stall
            for (int i = 0; i < waitLine.size(); i++) {
                if (stall.get(0).getGender() == waitLine.get(i).getGender()) {
                    stall.add(waitLine.get(i));

                    if (stall.get(1).getGender() == 0) {
                        genderString = "M";
                    } else {
                        genderString = "F";
                    }

                    System.out.printf("@t= %s; Person %s (%s) has entered the facilities for %s \n", 1, waitLine.get(i).getId(), genderString, waitLine.get(i).getTime());
                    waitLine.remove(i);
                    break;
                }
            }

            useFacilities();

        } else if (optionSwitch == 3) {
            generateSet(20);

            // add fist person to stall
            stall.add(waitLine.get(0));
            if (stall.get(0).getGender() == 0) {
                genderString = "M";
            } else {
                genderString = "F";
            }

            System.out.printf("@t= %s; Person %s (%s) has entered the facilities for %s \n", 1, waitLine.get(0).getId(), genderString, waitLine.get(0).getTime());
            waitLine.remove(0);

            // add second person to stall
            for (int i = 0; i < waitLine.size(); i++) {
                if (stall.get(0).getGender() == waitLine.get(i).getGender()) {
                    stall.add(waitLine.get(i));

                    if (stall.get(1).getGender() == 0) {
                        genderString = "M";
                    } else {
                        genderString = "F";
                    }

                    System.out.printf("@t= %s; Person %s (%s) has entered the facilities for %s \n", 1, waitLine.get(i).getId(), genderString, waitLine.get(i).getTime());
                    waitLine.remove(i);
                    break;
                }
            }

            useFacilities();
        }


        // empty the stalls
        while (stall.get(0) != null || stall.get(1) != null) {
            // if the first stall is not empty
            if (stall.get(0) != null) {

                if (stall.get(0).getTime() == 1) {
                    depart(0, clock);
                    stall.set(0, null);
                } else {
                    stall.get(0).setTime(stall.get(0).getTime() - 1);
                }
            }

            // if the second stall is not empty
            if (stall.get(1) != null) {

                if (stall.get(1).getTime() == 1) {
                    depart(1, clock);
                    stall.set(1, null);
                } else {
                    stall.get(1).setTime(stall.get(1).getTime() - 1);
                }
            }

            clock++;
        }

    }
}