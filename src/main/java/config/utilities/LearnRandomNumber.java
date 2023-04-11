package config.utilities;

import java.util.Random;

public class LearnRandomNumber {


    public static void main(String[] args) {
        double [] priceList = new double[10];

        int [] stId = new int[50];

//        stId[0]=200;
//        stId[1]=300;
//        stId[2]=400;
//        stId[3]=500;
//        stId[4]=600;


        Random random = new Random(1);
        int randomNumber=random.nextInt(100);;
        System.out.println(randomNumber);

//        for (int i = 0; i < stId.length; i++) {
//            stId[i]=random.nextInt(100);
//            System.out.println(stId[i]);
//        }

        System.out.println("+++++++++++Generate Random Number +++++++++++++++++++");

        randomNumberGenerate();
        System.out.println("++++++++++++Generate Random/ Unique Name++++++++++++++++++");
        generateUniqueName();
        System.out.println("++++++++++++Generate Random/ Unique Email++++++++++++++++++");
        generateUniqueEmail();
        System.out.println("++++++++++++Generate Random/ Unique Phone Number++++++++++++++++++");
        System.out.println("646-779-1"+randomNumberGenerate());
        System.out.println("++++++++++++Generate Random/ Unique new Approach ++++++++++++++++++");
       // System.out.println(randomNumberGenerateNew());
        System.out.println("50"+randomNumberGenerateNew());
        System.out.println(randomNumberGenerate()+randomNumberGenerateNew());
    }

    public static int randomNumberGenerate(){
        Random random = new Random();
        int randomNumber=random.nextInt(1000);;
       // System.out.println(randomNumber);
        return randomNumber;
    }


    public static double randomNumberGenerateNew(){
        double randomNumber=Math.random();
        // System.out.println(randomNumber);
        return randomNumber;
    }


    // Generate unique name
    public static String generateUniqueName(){
        int randomNumber=randomNumberGenerate();

        String uniqueName="James"+randomNumber;
        System.out.println(uniqueName);
        return uniqueName;
    }

    // Generate unique email address

    public static String generateUniqueEmail(){
        int randomNumber=randomNumberGenerate();
        String uniqueEmail="James"+randomNumber+"@gmail.com";
        System.out.println(uniqueEmail);
        return uniqueEmail;
    }




}
