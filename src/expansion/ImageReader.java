package expansion;

public class ImageReader extends data.FileReader implements java.lang.AutoCloseable {

    public ImageReader(String fileName) {
        super(fileName);
    }

    @Override
    public void close() throws Exception {
        System.out.close();
    }

}
