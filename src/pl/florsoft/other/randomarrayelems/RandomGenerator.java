package pl.florsoft.other.randomarrayelems;

public interface RandomGenerator<T> {

    T[] getRandomElemsFromArray(int m, T[] array);

    T[] prepareUniqueArray(int arrayLength);

}
