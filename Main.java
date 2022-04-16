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
    static int stall0Index = 0;
    static int stall1Index = 0;

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

    static void arrival(int stallNumber, int clock) {

        String tempGender;

        if (stallNumber == 0) {

            for (int i = 0; i < waitLine.size(); i++) {
                if (stall.get(1) == null || waitLine.get(i).getGender() == stall.get(1).getGender()) {

                    if (waitLine.get(i).getGender() == 0) {
                        tempGender = "m";
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
                if (waitLine.get(i).getGender() == stall.get(0).getGender() || stall.get(0) == null) {

                    if (waitLine.get(i).getGender() == 0) {
                        tempGender = "m";
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
            tempGender = "m";
        } else {
            tempGender = "F";
        }

        System.out.printf("t= %s; Person %s (%s) exits\n", clock, stall.get(stallNumber).getId(), tempGender);
        arrival(stallNumber, clock);
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

        int clock = 1;

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
        }




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