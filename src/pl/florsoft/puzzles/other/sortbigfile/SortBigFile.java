package pl.florsoft.puzzles.other.sortbigfile;

import pl.florsoft.puzzles.other.sortbigfile.testimpl.OutputChecker;
import pl.florsoft.puzzles.other.sortbigfile.testimpl.RandomLongGenerator;
import pl.florsoft.puzzles.other.sortbigfile.testimpl.TestBufferManager;

import java.io.IOException;
import java.util.*;

/**
 * Task: write a method to sort big file (bigger than available memory).
 */
public class SortBigFile {

    /**
     * @param maxMemUsage - max memory in bytes
     */
    void sortLargeFileOfInt64s(Reader<Long> inputReader, Writer<Long> outputWriter, BufferManager<Long> bufferManager,
                               long maxMemUsage) {
        List<BufferReader<Long>> sortedTmpFiles = createSortedTmpFiles(inputReader, bufferManager, maxMemUsage);
        mergeAndWriteToOutput(sortedTmpFiles, outputWriter, maxMemUsage);
    }

    private List<BufferReader<Long>> createSortedTmpFiles(Reader<Long> inputReader, BufferManager<Long> bufferManager,
                                                          long maxMemUsage) {
        List<BufferReader<Long>> tmpFiles = new ArrayList<>();
        int maxElemsInOneFile = (int) (maxMemUsage / (Long.SIZE / Byte.SIZE));
        List<Long> tmpBuffer = new ArrayList<>(maxElemsInOneFile);
        Long currentVal;
        while ((currentVal = inputReader.read()) != null) {
            tmpBuffer.add(currentVal);
            if (tmpBuffer.size() == maxElemsInOneFile) {
                tmpFiles.add(saveToSortedFile(tmpBuffer, bufferManager));
                tmpBuffer.clear();
            }
        }
        if (!tmpBuffer.isEmpty()) {
            tmpFiles.add(saveToSortedFile(tmpBuffer, bufferManager));
            tmpBuffer.clear();
        }
        return tmpFiles;
    }

    private BufferReader<Long> saveToSortedFile(List<Long> tmpBuffer, BufferManager<Long> bufferManager) {
        Collections.sort(tmpBuffer);
        BufferWriter<Long> bufferWriter = bufferManager.getBufferWriter();
        for (Long val: tmpBuffer) {
            bufferWriter.write(val);
        }
        return bufferWriter.reopenAsReader();
    }

    private void mergeAndWriteToOutput(List<BufferReader<Long>> files, Writer<Long> outputWriter, long maxMemUsage) {
        Queue<FileValue> currentMinVals = initHeap(files, maxMemUsage);
        while (!currentMinVals.isEmpty()) {
            FileValue value = currentMinVals.poll();
            outputWriter.write(value.val);
            Long newVal = files.get(value.fileNumber).read();
            if (newVal != null) {
                currentMinVals.add(new FileValue(value.fileNumber, newVal));
            }
        }
    }

    private Queue<FileValue> initHeap(List<BufferReader<Long>> files, long maxMemUsage) {
        int maxElems = (int) (maxMemUsage / (Long.SIZE / Byte.SIZE));
        int maxElemsFromOneFile = maxElems / files.size();
        Queue<FileValue> heap = new PriorityQueue<>(maxElems);
        for (int fileNum = 0; fileNum < files.size(); fileNum++) {
            BufferReader<Long> file = files.get(fileNum);
            for (int i = 0; i < maxElemsFromOneFile; i++) {
                heap.add(new FileValue(fileNum, file.read()));
            }
        }
        return heap;
    }

    class FileValue implements Comparable<FileValue> {
        int fileNumber;
        Long val;

        public FileValue(int fileNumber, Long val) {
            this.fileNumber = fileNumber;
            this.val = val;
        }

        @Override
        public int compareTo(FileValue o) {
            return val.compareTo(o.val);
        }
    }

    public static void main(String[] args) throws IOException {
        int longNumbersInBigFile = 1000000;
        long maxMemUsage = 10000L; // 10kB
        new SortBigFile().sortLargeFileOfInt64s(new RandomLongGenerator(longNumbersInBigFile),
                new OutputChecker(), new TestBufferManager(), maxMemUsage);
    }

}
