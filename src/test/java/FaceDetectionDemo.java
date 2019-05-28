import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
 
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
 
public class FaceDetectionDemo {
 
    public static void main(String[] args) throws IOException {
    	//加载dll
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //获取resource目录下的haarcascade_frontalface_alt.xml
        URL url = new URL(Thread.currentThread().getContextClassLoader().getResource("")+
        		"haarcascade_frontalface_alt.xml");
		CascadeClassifier faceDetector = new CascadeClassifier(new File(url.getPath()).toString());
        // 打开摄像头或者视频文件
        VideoCapture capture = new VideoCapture();
        //打开摄像头
//        capture.open(0);
//        打开视频文件 
        capture.open("http://vali-dns.cp31.ott.cibntv.net/6572374C8FE4471B78B4B48E3/03000807005BDD8A16ACA03559D58B7B66A48F-1057-4595-86BB-969B9825D756.mp4?ccode=0501&duration=300&expire=18000&psid=fbcd2f2387fd47f6fea1cb5ba008cfeb&ups_client_netip=71d7bd4f&ups_ts=1547783136&ups_userid=&utid=HOdFFDduVxMCAXHXvEIAXfix&vid=XMzg5NTEwNDg4OA&vkey=A949c7b96d1a234a3934b238a56045964&s=efbfbdd2805befbfbd57&sp=");
//        capture.open("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear2/prog_index.m3u8");
        //判断是否能加载视频
        if(!capture.isOpened()) {
            System.out.println("无法加载视频数据……");
            return;
        }
      //获取帧的宽度
        int frameWidth = (int)capture.get(3);
      //获取帧的高度
        int frameHeight = (int)capture.get(4);
        //创建容器
        JFrameGUI gui = new JFrameGUI();
        gui.createWin("嘤嘤嘤的人脸识别器", new Dimension(frameWidth, frameHeight));
        //创建图像容器类
        Mat frame = new Mat();
        while(true) {
        	//读取一帧
            boolean have = capture.read(frame);
//          会翻转
//            Core.flip(frame, frame, 1);// Win上摄像头
            // 进行人脸检测
            MatOfRect faceDetections = new MatOfRect();
    		faceDetector.detectMultiScale(frame, faceDetections);
    		System.out.println(String.format("检测到人脸： %s", faceDetections.toArray().length));
    		for (Rect rect : faceDetections.toArray()) {
    			Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
    					new Scalar(0, 255, 0), 1);
    		}
            if(!have) break;
            if(!frame.empty()) {
            	//Mat转换BufferedImage并刷新
                gui.imshow(conver2Image(frame));
                gui.repaint();
            }
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
 
    }
 
    /**
     * Mat类转换BufferedImage类
     * @param mat
     * @return
     */
    public static BufferedImage conver2Image(Mat mat) {
        int width = mat.cols();
        int height = mat.rows();
        int dims = mat.channels();
        int[] pixels = new int[width*height];
        byte[] rgbdata = new byte[width*height*dims];
        mat.get(0, 0, rgbdata);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int index = 0;
        int r=0, g=0, b=0;
        for(int row=0; row<height; row++) {
            for(int col=0; col<width; col++) {
                if(dims == 3) {
                    index = row*width*dims + col*dims;
                    b = rgbdata[index]&0xff;
                    g = rgbdata[index+1]&0xff;
                    r = rgbdata[index+2]&0xff;
                    pixels[row*width+col] = ((255&0xff)<<24) | ((r&0xff)<<16) | ((g&0xff)<<8) | b&0xff; 
                }
                if(dims == 1) {
                    index = row*width + col;
                    b = rgbdata[index]&0xff;
                    pixels[row*width+col] = ((255&0xff)<<24) | ((b&0xff)<<16) | ((b&0xff)<<8) | b&0xff; 
                }
            }
        }
        setRGB( image, 0, 0, width, height, pixels);
        return image;
    }
 
    /**
     * 图像中设置ARGB像素
     */
    public static void setRGB( BufferedImage image, int x, int y, int width, int height, int[] pixels ) {
        int type = image.getType();
        if ( type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB )
            image.getRaster().setDataElements( x, y, width, height, pixels );
        else
            image.setRGB( x, y, width, height, pixels, 0, width );
    }
 
}
