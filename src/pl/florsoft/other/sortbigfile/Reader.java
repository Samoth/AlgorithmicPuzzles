package pl.florsoft.other.sortbigfile;

public interface Reader<T> {

    T read();
    void restart();

}
