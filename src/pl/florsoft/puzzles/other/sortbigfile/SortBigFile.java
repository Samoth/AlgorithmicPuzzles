package pl.florsoft.puzzles.other.sortbigfile;

import pl.florsoft.puzzles.other.sortbigfile.testimpl.OutputChecker;
import pl.florsoft.puzzles.other.sortbigfile.testimpl.RandomLongGenerator;
import pl.florsoft.puzzles.other.sortbigfile.testimpl.TestBufferManager;

import java.io.IOException;
import java.util.Arrays;

/**
 * Task: write a method to sort big file (bigger than available memory).
 */
public class SortBigFile {

    /**
     * @param maxMemUsage - max memory in bytes
     */
    void sortLargeFileOfInt64s(Reader<Long> inputReader, Writer<Long> outputWriter, BufferManager<Long> bufferManager,
                               long maxMemUsage) {
        int sortedTmpFiles = createSortedTmpFiles(inputReader, bufferManager, maxMemUsage);
        mergeAndWriteToOutput(sortedTmpFiles, 0, outputWriter, bufferManager);
    }

    private int createSortedTmpFiles(Reader<Long> inputReader, BufferManager<Long> bufferManager,
                                     long maxMemUsage) {
        int maxElemsInOneFile = (int) (maxMemUsage / (Long.SIZE / Byte.SIZE));
        Long[] tmpBuffer = new Long[maxElemsInOneFile];
        Long currentVal;
        int savedValsInBuffer = 0, files = 0;
        while ((currentVal = inputReader.read()) != null) {
            tmpBuffer[savedValsInBuffer++] = currentVal;
            if (savedValsInBuffer == maxElemsInOneFile) {
                saveToSortedFile(tmpBuffer, bufferManager);
                files++;
                savedValsInBuffer = 0;
            }
        }
        if (savedValsInBuffer > 0) {
            if (savedValsInBuffer < maxElemsInOneFile) {
                tmpBuffer = createSmallerBuffer(tmpBuffer, savedValsInBuffer);
            }
            saveToSortedFile(tmpBuffer, bufferManager);
            files++;
        }
        return files;
    }

    private Long[] createSmallerBuffer(Long[] tmpBuffer, int savedValsInBuffer) {
        Long[] newArray = new Long[savedValsInBuffer];
        System.arraycopy(tmpBuffer, 0, newArray, 0, savedValsInBuffer);
        return newArray;
    }

    private void saveToSortedFile(Long[] tmpBuffer, BufferManager<Long> bufferManager) {
        Arrays.sort(tmpBuffer);
        BufferWriter<Long> bufferWriter = bufferManager.getBufferWriter(0);
        for (Long val : tmpBuffer) {
            bufferWriter.write(val);
        }
        bufferWriter.endWriting();
    }

    private void mergeFiles(int files, int fileGroup, Writer<Long> outputWriter, BufferManager<Long> bufferManager) {
        if (files == 2) {
            writeToOutput(fileGroup, outputWriter, bufferManager);
            return;
        }
        int currentFile = 0;
        while (files > 0) {
            BufferWriter<Long> output = bufferManager.getBufferWriter(fileGroup + 1);
            BufferReader<Long> firstReader = bufferManager.getBufferReader(fileGroup, ++currentFile);
            files--;
            if (files > 0) {
                BufferReader<Long> secondReader = bufferManager.getBufferReader(fileGroup, ++currentFile);
                files--;

            } else {
                // TODO
//                output.write();
            }
            files -= 2;
        }
        // TODO
//        mergeAndWriteToOutput();
    }

    private void writeToOutput(int fileGroup, Writer<Long> outputWriter, BufferManager<Long> bufferManager) {
        BufferReader<Long> firstReader = bufferManager.getBufferReader(fileGroup, 1);
        BufferReader<Long> secondReader = bufferManager.getBufferReader(fileGroup, 2);
        // TODO
        outputWriter.write(val);
    }

    public static void main(String[] args) throws IOException {
        int longNumbersInBigFile = 1000000;
        long maxMemUsage = 10000L; // 10kB
        new SortBigFile().sortLargeFileOfInt64s(new RandomLongGenerator(longNumbersInBigFile),
                new OutputChecker(), new TestBufferManager(), maxMemUsage);
    }

}
