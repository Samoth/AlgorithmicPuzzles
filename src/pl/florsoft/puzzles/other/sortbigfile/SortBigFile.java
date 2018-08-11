package pl.florsoft.puzzles.other.sortbigfile;

import pl.florsoft.puzzles.other.sortbigfile.testimpl.OutputChecker;
import pl.florsoft.puzzles.other.sortbigfile.testimpl.RandomLongGenerator;
import pl.florsoft.puzzles.other.sortbigfile.testimpl.TestBufferManager;
import pl.florsoft.puzzles.sorting.HeapSort;

/**
 * Task: write a method to sort big file (bigger than available memory).
 */
public class SortBigFile {

    /**
     * @param maxMemUsage - max memory in bytes
     */
    public void sortLargeFileOfInt64s(Reader<Long> inputReader, Writer<Long> outputWriter, BufferManager<Long> bufferManager,
                                      long maxMemUsage) {
        int sortedTmpFiles = createSortedOutput(inputReader, bufferManager, maxMemUsage, outputWriter);
        mergeAndWriteToOutput(sortedTmpFiles, 0, outputWriter, bufferManager);
    }

    private int createSortedOutput(Reader<Long> inputReader, BufferManager<Long> bufferManager,
                                   long maxMemUsage, Writer<Long> outputWriter) {
        int maxElemsInOneFile = (int) (maxMemUsage / (Long.SIZE / Byte.SIZE));
        long[] tmpBuffer = new long[maxElemsInOneFile];
        Long currentVal;
        int valCnt = 0, files = 0;
        while ((currentVal = inputReader.read()) != null) {
            tmpBuffer[valCnt++] = currentVal;
            if (valCnt == maxElemsInOneFile) {
                saveToSortedFile(tmpBuffer, valCnt, bufferManager);
                files++;
                valCnt = 0;
            }
        }
        if (valCnt > 0) {
            if (files == 0) { // sort and write to outputWriter: there is no need to use tmp files
                saveToSortedOutput(tmpBuffer, valCnt, outputWriter);
            } else {
                saveToSortedFile(tmpBuffer, valCnt, bufferManager);
                files++;
            }
        }
        return files;
    }

    private void saveToSortedFile(long[] tmpBuffer, int valsToSort, BufferManager<Long> bufferManager) {
        HeapSort.sort(tmpBuffer, 0, valsToSort);
        BufferWriter<Long> bufferWriter = bufferManager.getBufferWriter(0, false);
        for (int i = 0; i < valsToSort; i++) {
            bufferWriter.write(tmpBuffer[i]);
        }
        bufferWriter.endWriting();
    }

    private void saveToSortedOutput(long[] tmpBuffer, int valsToSort, Writer<Long> outputWriter) {
        HeapSort.sort(tmpBuffer, 0, valsToSort);
        for (int i = 0; i < valsToSort; i++) {
            outputWriter.write(tmpBuffer[i]);
        }
    }

    private void mergeAndWriteToOutput(int files, int fileGroup, Writer<Long> outputWriter, BufferManager<Long> bufferManager) {
        if (files == 0) {
            return;
        } else if (files <= 2) {
            BufferReader<Long> firstReader = bufferManager.getBufferReader(fileGroup, 0);
            BufferReader<Long> secondReader = null;
            if (files == 2) {
                secondReader = bufferManager.getBufferReader(fileGroup, 1);
            }
            writeToOutput(outputWriter, firstReader, secondReader);
            return;
        }
        int currentFile = 0, outputFiles = 0;
        while (files > 0) {
            BufferWriter<Long> output = bufferManager.getBufferWriter(fileGroup + 1, true);
            BufferReader<Long> firstReader = bufferManager.getBufferReader(fileGroup, currentFile++);
            BufferReader<Long> secondReader = null;
            files--;
            if (files > 0) {
                secondReader = bufferManager.getBufferReader(fileGroup, currentFile++);
                files--;
            }
            writeToOutput(output, firstReader, secondReader);
            output.endWriting();
            outputFiles++;
        }
        mergeAndWriteToOutput(outputFiles, fileGroup + 1, outputWriter, bufferManager);
    }

    private void writeToOutput(Writer<Long> outputWriter, BufferReader<Long> firstReader, BufferReader<Long> secondReader) {
        Long firstVal = firstReader.read();
        Long secVal = secondReader != null ? secondReader.read() : null;
        while (firstVal != null || secVal != null) {
            if (firstVal != null && secVal != null) {
                if (firstVal <= secVal) {
                    outputWriter.write(firstVal);
                    firstVal = firstReader.read();
                } else {
                    outputWriter.write(secVal);
                    secVal = secondReader.read();
                }
            } else if (firstVal != null) {
                outputWriter.write(firstVal);
                firstVal = firstReader.read();
            } else {
                outputWriter.write(secVal);
                secVal = secondReader.read();
            }
        }
    }

    public static void main(String[] args) {
        int longNumbersInBigFile = 10000000;
        long maxMemUsage = 1000000L; // 1000kB
        new SortBigFile().sortLargeFileOfInt64s(new RandomLongGenerator(longNumbersInBigFile),
                new OutputChecker(), new TestBufferManager(maxMemUsage), maxMemUsage);
    }

}
