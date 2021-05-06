package Service.tools;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

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


    public static String GetImageStr(String path)
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = path;//待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }

    public static boolean GenerateImage(String imgStr,String path)
    {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String imgFilePath = path;//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }




    //新版本的Base64
    /**
     * 解密
     *
     * @param imageString
     * @return
     */
    public static BufferedImage decodeToImage(String imageString,String path) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            imageByte = Base64.getDecoder().decode(imageString);
//            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            String imgFilePath = path;//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(imageByte);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return image;
    }

    /**
     * 加密
     *
     * @param image
     * @param type
     * @return
     */
    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();
            imageString = Base64.getEncoder().encodeToString(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }



}
