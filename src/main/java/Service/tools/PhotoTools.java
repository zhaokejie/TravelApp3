package Service.tools;

import java.io.*;

public class PhotoTools {
    public static void savaPhoto(String data, String path) throws IOException {
        File tempFile = new File(path);
        if (!tempFile.exists()) {
            tempFile.createNewFile();


        }
        FileOutputStream os = new FileOutputStream(tempFile);

        os.write(data.getBytes());

        os.close();


    }

    public static String openPhoto(String path) throws IOException {

        File tempFile = new File(path);
//        if (!tempFile.exists()) {
//            tempFile.mkdirs();
//
//        }

        FileInputStream fileInputStream = new FileInputStream(tempFile);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        char[] temp = new char[5242800];
        String data ;
        bufferedReader.read(temp);
        data = new String(temp);




        fileInputStream.close();
        return data;


    }


}
