package pl.florsoft.puzzles.other.sortbigfile;

public interface BufferManager<T> {

    BufferReader<T> getBufferReader(int number);

    BufferWriter<T> getBufferWriter(boolean useBuffer);

    void markAsEndPhaseWriting();

}
