package pl.florsoft.puzzles.other.sortbigfile.testimpl;

import pl.florsoft.puzzles.other.sortbigfile.BufferManager;
import pl.florsoft.puzzles.other.sortbigfile.BufferReader;
import pl.florsoft.puzzles.other.sortbigfile.BufferWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TestBufferManager implements BufferManager<Long> {

    private Map<Integer, Integer> createdFiles = new HashMap<>();
    private long maxMemoryToUse;
    private long uuid;
    private int actualWriterFileGroup = 0;

    public TestBufferManager(long maxMemoryToUse) {
        uuid = UUID.randomUUID().getLeastSignificantBits();
        this.maxMemoryToUse = maxMemoryToUse;
    }

    @Override
    public BufferReader<Long> getBufferReader(int number) {
        return new TestBufferReader(createFileName(actualWriterFileGroup - 1, number));
    }

    @Override
    public BufferWriter<Long> getBufferWriter(boolean useBuffer) {
        if (!createdFiles.containsKey(actualWriterFileGroup)) {
            createdFiles.put(actualWriterFileGroup, -1);
        }
        Integer files = createdFiles.get(actualWriterFileGroup);
        createdFiles.put(actualWriterFileGroup, ++files);
        return new TestBufferWriter(createFileName(actualWriterFileGroup, files), files, useBuffer, maxMemoryToUse);
    }

    @Override
    public void markAsEndPhaseWriting() {
        createdFiles.remove(actualWriterFileGroup - 1);
        actualWriterFileGroup++;
    }

    private String createFileName(int fileGroup, int fileNumber) {
        return "sorted-file-" + uuid + "---" + fileGroup + "-" + fileNumber + ".long";
    }

}
