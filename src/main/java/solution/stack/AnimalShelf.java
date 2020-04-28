package solution.stack;

import java.util.*;

public class AnimalShelf {

    private LinkedList<int []> allList;
    private List<int []> catList;
    private List<int []> dogList;

    public AnimalShelf() {
        this.allList = new LinkedList<>();
        this.catList = new LinkedList<>();
        this.dogList = new LinkedList<>();
    }

    public void enqueue(int[] animal) {
        allList.add(animal);
        if (animal[1] == 0){
            catList.add(animal);
        }else {
            dogList.add(animal);
        }
    }

    public int[] dequeueAny() {
        if (allList.size() > 0){
            int[] removedAnimal = allList.remove(0);
            if (removedAnimal[1] == 0){
                catList.remove(0);
            }else{
                dogList.remove(0);
            }
            return removedAnimal;
        }
        return new int[] {-1, -1};
    }

    public int[] dequeueDog() {
        if (dogList.size() > 0){
            int[] removedDog = dogList.remove(0);
            Iterator<int[]> it = allList.iterator();
            for (; it.hasNext(); ){
                int[] animal = it.next();
                if (animal[1] == 1){
                    it.remove();
                    break;
                }
            }
            return removedDog;
        }
        return new int[] {-1, -1};
    }

    public int[] dequeueCat() {
        if (catList.size() > 0){
            int[] removedCat = catList.remove(0);
            Iterator<int[]> it = allList.iterator();
            for (; it.hasNext(); ){
                int[] animal = it.next();
                if (animal[1] == 0){
                    it.remove();
                    break;
                }
            }
            return removedCat;
        }
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        AnimalShelf animalShelf = new AnimalShelf();
        animalShelf.enqueue(new int[]{0, 0});
        animalShelf.enqueue(new int[]{1, 0});
        animalShelf.enqueue(new int[]{2, 1});

        System.out.println(Arrays.toString(animalShelf.dequeueDog()));
        System.out.println(Arrays.toString(animalShelf.dequeueCat()));
        System.out.println(Arrays.toString(animalShelf.dequeueAny()));
    }
}
