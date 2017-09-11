import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        ZooTvSvc zoo = new ZooTvSvc();
        try {
            System.out.println(zoo.json());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}