package coffee.hashcode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class FileUtilities {

    /**
     * Constructs a {@link FileInputStream} from the given file and returns the result of {@link #readStreamFully(InputStream)}. No checks are made on the existence of the file
     *
     * @param file the file to read
     * @return the data contained in the file
     * @throws IOException if there is an error reading (see {@link FileInputStream#FileInputStream(File)} and {@link #readStreamFully(InputStream)}
     */
    public static String readFileFully(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            return readStreamFully(fis);
        }
    }

    /**
     * Reads an input stream fully in 8Kb chunks storing it temporarily in a {@link ByteArrayOutputStream} before converting to string
     *
     * @param stream the input stream to read
     * @return the data contained in the stream
     * @throws IOException if there is an error reading from the file (see {@link InputStream#read(byte[])}
     */
    public static String readStreamFully(InputStream stream) throws IOException {
        byte[] buffer = new byte[1024 * 8];
        ByteArrayOutputStream fileStore = new ByteArrayOutputStream();
        int read;

        while ((read = stream.read(buffer)) != -1) {
            fileStore.write(buffer, 0, read);
        }

        return fileStore.toString();
    }

}
