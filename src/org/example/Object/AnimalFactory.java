package org.example.Object;

public class AnimalFactory {

    public static Animal CreateAnimal(int input){
        if(input%2 == 0){
            Cat cat = new Cat();
            cat.id = input;
            cat.numFoot = 4;
            return cat;
        }
        else {
            Duck duck = new Duck();
            duck.id = input;
            duck.numFoot =2;
            return duck;
        }
    }
}
