package ej.item01;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAnimal {
    public static void main(String[] args) {
        List<Animal> animals = Favorite.getInstance();

        Animal dog = new Dog();
        Animal cat = new Cat();

        animals.add(dog);
        animals.add(cat);
    }
}

class Favorite {
    public static List<Animal> getInstance() {
        return new ArrayList<>();
    }
}

interface Animal {}
class Dog implements Animal {}
class Cat implements Animal {}