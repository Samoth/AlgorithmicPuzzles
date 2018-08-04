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

    public TestBufferManager(long maxMemoryToUse) {
        uuid = UUID.randomUUID().getLeastSignificantBits();
        this.maxMemoryToUse = maxMemoryToUse;
    }

    @Override
    public BufferReader<Long> getBufferReader(int group, int number) {
        return new TestBufferReader(createFileName(group, number));
    }

    @Override
    public BufferWriter<Long> getBufferWriter(int fileGroup, boolean useBuffer) {
        if (!createdFiles.containsKey(fileGroup)) {
            createdFiles.put(fileGroup, -1);
        }
        Integer files = createdFiles.get(fileGroup);
        createdFiles.put(fileGroup, ++files);
        return new TestBufferWriter(createFileName(fileGroup, files), files, useBuffer, maxMemoryToUse);
    }

    private String createFileName(int fileGroup, int fileNumber) {
        return "sorted-file-" + uuid + "---" + fileGroup + "-" + fileNumber + ".long";
    }

}
