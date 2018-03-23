package example;

public class MiniNet {

   // author santhan
    public static void main(String[] args){

        //printing the choice menu on the screen to notify user for taking input.
        Driver driver = new Driver();
        driver.populateDataFromManger();
        driver.printChoiceMenu();
        driver.takeInput();



    }
}

