package com.ttknp.understandsecurityandtestusingmokito.service;


import com.ttknp.understandsecurityandtestusingmokito.entity.Dog;

import java.util.ArrayList;
import java.util.List;

public class DogService {

    private List<Dog> dogs;

    public DogService() {
        dogs = new ArrayList<>();
        dogs.add(new Dog(1001L, "Harry", "Alaskan Malamute", "Male", 12000.0));
        dogs.add(new Dog(1002L, "Jia Ant", "Basset Hound", "Male", 19000.0));
        dogs.add(new Dog(1003L, "Harry", "Beagle", "Female", 22000.0));
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public Dog getDog(long id) {
        return dogs
                .stream()
                .filter(dog -> dog.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Boolean addDog(Dog dog) {
        return dogs.add(dog);
    }

    public Boolean editDog(Dog dog, long id) {
        boolean isFoundTheDog = false;
        // *** Should know
        // this loop works for changing properties of dog
        // *** it means dogIsMemberOfDogs changes dogs change too
        for (Dog dogIsMemberOfDogs : dogs) {
            if (dogIsMemberOfDogs.getId() == id) {
                dogIsMemberOfDogs.setName(dog.getName());
                dogIsMemberOfDogs.setGender(dog.getGender());
                dogIsMemberOfDogs.setPrice(dog.getPrice());
                dogIsMemberOfDogs.setType(dog.getType());
                isFoundTheDog = true;
            }
        }
        return isFoundTheDog;
    }

    public Boolean removeDog(long id) {
        return dogs.removeIf(dog -> dog.getId() == id);
    }
}
