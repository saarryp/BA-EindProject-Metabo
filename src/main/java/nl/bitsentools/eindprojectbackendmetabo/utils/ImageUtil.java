package nl.bitsentools.eindprojectbackendmetabo.utils;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

@Component
public class ImageUtil {

    public static byte[] compressImage(byte[] data){
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
    byte[] tmp = new byte[4 + 1024];
    while (!deflater.finished()){
        int size = deflater.deflate(tmp);
        outputStream.write(tmp, 0, size);

    }
    try {
        outputStream.close();
    } catch (IOException e) {
        throw new RuntimeException(e);
    } return outputStream.toByteArray();
    }
}
