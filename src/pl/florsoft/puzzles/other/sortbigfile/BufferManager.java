package pl.florsoft.puzzles.other.sortbigfile;

public interface BufferManager<T> {

    BufferReader<T> getBufferReader(int group, int number);

    BufferWriter<T> getBufferWriter(int group);

}