package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {

    public List<User> mUsers;
    DataManager mDataManager = DataManager.getDataManager();
    String name;



    public void printChoiceMenu(){
        System.out.println("Mininet Menu\n" +
                "===============================================\n" +
                "1.List everyone\n" +
                "2.Select a person\n" +
                "3.Are these two direct friend\n" +
                "4.Add a person\n" +
                "5.Show parents\n" +
                "6.Show children\n" +
                "\n" +
                "? Exit\n");


    }

    public void takeInput(){
        Scanner sc = new Scanner(System.in);
        processInput(sc.nextLine());
    }

    public void processInput(String input){
        //processing the input to execute desired methods.
        switch (input){
            case "1":
                listEveryone(mUsers);
                break;
            case "2":
                selectPerson();
                break;
            case "3":
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter first friend's name ");
                String name1 =sc.nextLine();
                System.out.println("Enter second friend's name ");
                String name2 = sc.nextLine();
                if(detectDirectFriends(name1,name2)){
                    System.out.println("YES ");
                }else{
                    System.out.println("NO ");
                }
                printChoiceMenu();
                takeInput();
                break;
            case "4":
                addUser();
                break;

            case "5":

                showParents();
                break;

            case "6":
                showChildrens();
                break;
            case "?":

                System.exit(0);
                break;
            default:


        }

    }

    public void populateDataFromManger(){

        mUsers = mDataManager.getData();
    }


    public void listEveryone(List <User> userList){
        for(int i =0 ; i< userList.size();i++){
            System.out.println(i+". "+userList.get(i).getmProfile().getName());
        }
        printChoiceMenu();
        takeInput();
    }


    public void selectPerson(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter user name");
        name =sc.nextLine();
        boolean isNameExist = false;
        for(int i =0;i<mUsers.size();i++){
            if(name.equals(mUsers.get(i).getmProfile().getName())){
                isNameExist = true;
                printProfile(mUsers.get(i).getmProfile(),i);
            }
        }
        if(!isNameExist) {
            System.out.println("User Does not exist");
            printChoiceMenu();
            takeInput();
        }

    }

    public void printProfile(Profile profile,int position){

        System.out.println("USER PROFILE");
        System.out.println("====================================================");
        System.out.println("Name: "+profile.getName());
        System.out.println(" ");
        System.out.println("Status: "+profile.getStatus());
        System.out.println(" ");
        System.out.println("Friends List: ");
        System.out.println(" ");

        if(profile != null && profile.getmFriendList() !=null)
        for(Friend friend : profile.getmFriendList()){
            System.out.println(" "+friend.mUser.getmProfile().getName());
        }

        System.out.println(" ");
        System.out.println("Please select following options on selected user: ");
        System.out.println("1. Update Name ");
        System.out.println("2. Update Status ");
        System.out.println("3. Add new friend ");
        System.out.println("4. Delete user ");
        System.out.println("5. Return to main menu ");
        Scanner sc = new Scanner(System.in);
        String choice =sc.nextLine();
        switch (choice){

            case "1":
                System.out.println("Please enter updated name");
                String name = sc.nextLine();
                updateName(name,position);
                printChoiceMenu();
                takeInput();
                break;
            case "2":
                System.out.println("Please enter updated status");
                String status = sc.nextLine();
                updateStatus(status, position);
                printChoiceMenu();
                takeInput();
                break;
            case "3":
                System.out.println("Enter the name of friend.");
                String friendName = sc.nextLine();
                if(mUsers.get(position).getmAge() > 16){
                    addNewFriend(position,friendName);
                }else{
                    addNewYoungFriend(position,friendName);
                }
                break;
            case "4":
               //delete the user.
                mUsers.remove(position);
                printChoiceMenu();
                takeInput();

                break;
            case "5":
                printChoiceMenu();
                takeInput();
                break;
            default:
                System.out.println("Invalid choice.. returning to main menu");
                printChoiceMenu();
                takeInput();
                break;


        }

    }

    public void updateName(String userName,int position){
        mUsers.get(position).getmProfile().setName(userName);
        System.out.println("Username updated successfully");

    }

    public void updateStatus(String userName,int position){
        mUsers.get(position).getmProfile().setStatus(userName);
        System.out.println("Status updated successfully");
    }

    public void addUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name:");
        String name = sc.nextLine();
        System.out.println("Enter age:");
        int age = Integer.parseInt(sc.nextLine());
        System.out.println("Enter status:");
        String status = sc.nextLine();
        User user = new User();
        user.setmAge(age);
        Profile profile = new Profile();
        profile.setName(name);
        profile.setStatus(status);
        if(age<=16){
            System.out.println("Enter father's name:");
            String fatherName = sc.nextLine();
            User father = connectParent(fatherName,user);
            if (father == null) {
                System.out.println("Parent does not exist. Exiting");
                printChoiceMenu();
                takeInput();
                return;
            }
            System.out.println("Enter mother's name:");
            String motherName = sc.nextLine();
            User mother = connectParent(motherName,user);
            if (mother == null) {
                System.out.println("Parent does not exist. Exiting");
                printChoiceMenu();
                takeInput();
                return;
            }
            List<User> parents = new ArrayList<>();
            parents.add(father);
            parents.add(mother);
            user.setParents(parents);
        }
        user.setmProfile(profile);
        System.out.println("User successfully added.");
        mUsers.add(user);

        printChoiceMenu();
        takeInput();

    }

    public User connectParent(String parentName,User user){
        boolean isnameexist = false;
        for(int i =0;i<mUsers.size();i++){
            if(parentName.equals(mUsers.get(i).getmProfile().getName())) {
                mUsers.get(i).getChildren().add(user);
                return mUsers.get(i);
            }
        }
        return null;
    }

    public void addNewFriend(int position,String friendName) {
        for (int i = 0; i < mUsers.size(); i++) {
            if (friendName.equals(mUsers.get(i).getmProfile().getName())) {
                if(mUsers.get(i).getmAge() > 16){
                AdultFriend friend = new AdultFriend();
                friend.mUser = mUsers.get(position);
                    AdultFriend reverseFriend = new AdultFriend();
                    reverseFriend.mUser = mUsers.get(i);
                mUsers.get(i).getmProfile().getmFriendList().add(friend);
                    mUsers.get(position).getmProfile().getmFriendList().add(reverseFriend);
                    System.out.println("Friend Added successfully");
                    printChoiceMenu();
                    takeInput();
                    return;
                }else{
                    System.out.println("User is too young to be friends with you!!");
                    printChoiceMenu();
                    takeInput();
                    return;
                }


            }
        }
        System.out.println("User does not exist");
        printChoiceMenu();
        takeInput();
    }

    public void addNewYoungFriend(int position,String friendName) {
        for (int i = 0; i < mUsers.size(); i++) {
            if (friendName.equals(mUsers.get(i).getmProfile().getName())) {
                if(mUsers.get(i).getmAge() < 16){
                    YoungFriend friend = new YoungFriend();
                    friend.mUser = mUsers.get(position);
                    YoungFriend reverseFriend = new YoungFriend();
                    reverseFriend.mUser = mUsers.get(i);
                    if(!(Math.abs(mUsers.get(i).getmAge() - mUsers.get(position).getmAge()) > 3)){
                    mUsers.get(i).getmProfile().getmFriendList().add(friend);
                    mUsers.get(position).getmProfile().getmFriendList().add(reverseFriend);
                        System.out.println("Friend Added successfully");
                        printChoiceMenu();
                        takeInput();
                    }else{
                        System.out.println("Age difference is greater than 3");
                        printChoiceMenu();
                        takeInput();
                    }
                    return;

                }else{
                    System.out.println("User is too old to be friends with you!!");
                    printChoiceMenu();
                    takeInput();
                    return;
                }


            }
        }
        System.out.println("User does not exist");
        printChoiceMenu();
        takeInput();
    }

    public boolean detectDirectFriends(String name1,String name2){
        for (int i = 0; i < mUsers.size(); i++) {
            if (name1.equals(mUsers.get(i).getmProfile().getName())) {
                    for(Friend friend:mUsers.get(i).getmProfile().getmFriendList()){
                        if(friend.mUser.getmProfile().getName().equals(name2)){
                            return true;
                        }
                    }
            }
        }
        return false;
    }

    public void showParents(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter user name:");
        String name = sc.nextLine();

        for (int i = 0; i < mUsers.size(); i++) {
            if (name.equals(mUsers.get(i).getmProfile().getName())) {
                if(mUsers.get(i).getmAge() <= 16 && mUsers.get(i).getParents() != null && !mUsers.get(i).getParents().isEmpty()) {

                        System.out.println(mUsers.get(i).getParents().get(0).getmProfile().getName());
                        System.out.println(mUsers.get(i).getParents().get(1).getmProfile().getName());

                }else{
                    System.out.println("User is adult.. parents not required.");

                }

            }
        }
        printChoiceMenu();
        takeInput();

    }

    public void showChildrens(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter user name:");
        String name = sc.nextLine();

        for (int i = 0; i < mUsers.size(); i++) {
            if (name.equals(mUsers.get(i).getmProfile().getName())) {
                if(mUsers.get(i).getChildren() != null && !mUsers.get(i).getChildren().isEmpty()){
                    for(User user: mUsers.get(i).getChildren()){
                        System.out.println(user.getmProfile().getName());
                    }

                }else{
                    System.out.println("User has no children registered.");
                }

            }
        }
        printChoiceMenu();
        takeInput();
    }
}
