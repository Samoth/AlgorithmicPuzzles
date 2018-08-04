package pl.florsoft.puzzles.other.sortbigfile.testimpl;

import pl.florsoft.puzzles.other.sortbigfile.BufferManager;
import pl.florsoft.puzzles.other.sortbigfile.BufferWriter;

public class TestBufferManager implements BufferManager<Long> {

    @Override
    public BufferWriter<Long> getBufferWriter() {
        return new TestBufferWriter();
    }

}
