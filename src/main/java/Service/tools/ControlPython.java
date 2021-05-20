package Service.tools;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ControlPython{
    /**
     getPythonDemo2 中参数pyPath, args1, args2 可通过前端(AJAX)传过来
     注意：（1）参数pyPath ，python脚本路径最好使用绝对路径，如果python(.py)脚本中也有文件路径读取，同样推荐使用绝对路径
     （2）该案例中，python脚本接受的参数 args1, args2是String类型，务必确认python脚本中使用的参数类型是否是String类型，根据需要进行相应的类型转换
     */

    /**
     * 调用python
     * 参数pyPath, args1, args2 可通过前端(AJAX)传过来
     */
//    @ResponseBody
//    @RequestMapping(value="/getPythonDemo.ajax")
//    public int getPythonDemo2(String pyPath, String args1, String args2){
//        DemoController demo = new DemoController ();   //实例化类
//        System.out.println("pyPath= "+pyPath);
//        System.out.println("args1= "+args1);
//        System.out.println("args2= "+ar
//
//        gs2);
//        int res =demo.getPythonDemo(pyPath, args1, args2);      //调用python的方法
//        return res;
//    }


    /**
     * 调用python脚本  该方法支持python中的第三方库
     * @param pyPath python脚本路径
     * @param args1 参数1
     */
    public String getPythonDemo(String pyPath, String[] args1){
        Process proc;
        String line = null;
        List<String> lines = new ArrayList<String>();
        try {
            String[] args = new String[] {"python",pyPath, args1[0], args1[1], args1[2], args1[3]};
//            String[] args = new String[] {"python", pyPath };
            System.out.println("args:"+args1[0]+args1[1]+args1[2]+args1[3]);
            proc = Runtime.getRuntime().exec(args);  //该方法参数必须是String类型的

            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "gbk"));   //gbk 避免汉字乱码
            while ((line = in.readLine()) != null) {
//                System.out.println(":"+line);
//                lines.add(line);   //把Python的print值保存了下来
            }

            in.close();
            int exitVal = proc.waitFor();
            System.out.println("Process exitValue: " + exitVal);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Java调Python脚本结束");
        String lineData = lines.toString();
        System.out.println(lineData);
        return lineData;
    }


    public static void main(String[] args) {
        String picFileName = "sunyhj20210506224154";
        String[] args1 = new String[] { "--content_image_path=C:\\yhj\\data\\mapTransfer\\rawMap\\"+picFileName+".jpg",
                "--content_seg_path=C:\\yhj\\data\\mapTransfer\\rawMap\\"+picFileName+".jpg",
                "--style_image_path=C:\\yhj\\code\\PycharmProject\\mapTransfer\\style.jpg",
                "--rawMapName="+picFileName+".jpg"};
        ControlPython c = new ControlPython();
        c.getPythonDemo("C:\\yhj\\code\\PycharmProject\\mapTransfer\\demo.py", args1);

    }

}