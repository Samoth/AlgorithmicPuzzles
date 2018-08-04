package pl.florsoft.puzzles.other.sortbigfile;

public interface Reader<T> {

    T read();
    void restart();

}
