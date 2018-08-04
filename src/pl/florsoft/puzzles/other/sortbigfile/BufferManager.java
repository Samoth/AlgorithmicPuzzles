package pl.florsoft.puzzles.other.sortbigfile;

public interface BufferManager<T> {

    BufferWriter<T> getBufferWriter();

}