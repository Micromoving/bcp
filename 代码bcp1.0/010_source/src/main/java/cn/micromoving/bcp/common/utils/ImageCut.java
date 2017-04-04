package cn.micromoving.bcp.common.utils;

import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.Graphics;
import java.awt.color.ColorSpace;

import javax.imageio.ImageIO;

import cn.micromoving.bcp.modules.sys.utils.UserUtils;

public class ImageCut {

	/**
	 * ͼ���и�ģ� *
	 * 
	 * @param srcImageFile
	 *            Դͼ���ַ
	 * @param dirImageFile
	 *            ��ͼ���ַ
	 * @param x
	 *            Ŀ����Ƭ���x���
	 * @param y
	 *            Ŀ����Ƭ���y���
	 * @param destWidth
	 *            Ŀ����Ƭ���
	 * @param destHeight
	 *            Ŀ����Ƭ�߶�
	 */
	public static void abscut(String srcImageFile, String dirImageFile, int x,
			int y, int destWidth, int destHeight) {
		try {
			Image img;
			ImageFilter cropFilter;
			// ��ȡԴͼ��
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			int srcWidth = bi.getWidth(); // Դͼ���
			int srcHeight = bi.getHeight(); // Դͼ�߶�
			if (srcWidth >= destWidth && srcHeight >= destHeight) {
				Image image = bi.getScaledInstance(srcWidth, srcHeight,
						Image.SCALE_DEFAULT);
				// �Ľ���뷨:�Ƿ���ö��̼߳ӿ��и��ٶ�
				// �ĸ�����ֱ�Ϊͼ��������Ϳ��
				// ��: CropImageFilter(int x,int y,int width,int height)
				cropFilter = new CropImageFilter(x, y, destWidth, destHeight);
				img = Toolkit.getDefaultToolkit().createImage(
						new FilteredImageSource(image.getSource(), cropFilter));
				BufferedImage tag = new BufferedImage(destWidth, destHeight,
						BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(img, 0, 0, null); // ������С���ͼ
				g.dispose();
				// ���Ϊ�ļ�
				ImageIO.write(tag, "JPEG", new File(dirImageFile));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����ͼ��
	 * 
	 * @param srcImageFile
	 *            Դͼ���ļ���ַ
	 * @param result
	 *            ���ź��ͼ���ַ
	 * @param scale
	 *            ���ű���
	 * @param flag
	 *            ����ѡ��:true �Ŵ�; false ��С;
	 */
	public static void scale(String srcImageFile, String result, int scale,
			boolean flag) {
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile)); // �����ļ�
			int width = src.getWidth(); // �õ�Դͼ��
			int height = src.getHeight(); // �õ�Դͼ��
			if (flag) {
				// �Ŵ�
				width = width * scale;
				height = height * scale;
			} else {
				// ��С
				width = width / scale;
				height = height / scale;
			}
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // ������С���ͼ
			g.dispose();
			ImageIO.write(tag, "JPEG", new File(result));// ������ļ���
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������ɰ�ָ����Ⱥ͸߶ȵ�ͼ��
	 * 
	 * @param srcImageFile
	 *            Դͼ���ļ���ַ
	 * @param result
	 *            �µ�ͼ���ַ
	 * @param _width
	 *            �����µ�ͼ����
	 * @param _height
	 *            �����µ�ͼ��߶�
	 */
	public static void scale(String srcImageFile, String result, int _width,
			int _height) {
		scale(srcImageFile, result, _width, _height, 0, 0);
	}

	public static void scale(String srcImageFile, String result, int width,
			int height, int x, int y) {
		try {

			BufferedImage src = ImageIO.read(new File(srcImageFile)); // �����ļ�

			Image image = src.getScaledInstance(width, height,
					Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, x, y, null); // ������С���ͼ
			g.dispose();
			ImageIO.write(tag, "JPEG", new File(result));// ������ļ���
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ͼ������ת�� GIF->JPG GIF->PNG PNG->JPG PNG->GIF(X)
	 */
	public static void convert(String source, String result) {
		try {
			File f = new File(source);
			f.canRead();
			f.canWrite();
			BufferedImage src = ImageIO.read(f);
			ImageIO.write(src, "JPG", new File(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ɫתΪ�ڰ�
	 * 
	 * @param source
	 * @param result
	 */
	public static void gray(String source, String result) {
		try {
			BufferedImage src = ImageIO.read(new File(source));
			ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
			ColorConvertOp op = new ColorConvertOp(cs, null);
			src = op.filter(src, null);
			ImageIO.write(src, "JPEG", new File(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void cut(String fileName) throws IOException {
		double imgWidth;
		double imgHeight;
		BufferedImage src;

		try {
			/*将目标文件读入到ImageIO流中*/
			src = ImageIO.read(new File(fileName));
			/*获取图片文件的宽*/
			int imgSrcWidth = src.getWidth();
			/*获取图片文件的高*/
			int imgSrcHeight = src.getHeight();
			/*如果图片的宽高比例大于默认比例，则按指定规则进行缩略*/
			if (imgSrcWidth / imgSrcHeight > UserUtils.DEFAULT_WEIGHT
					/ UserUtils.DEFAULT_HEIGHT) {
				imgWidth = UserUtils.DEFAULT_WEIGHT;
				imgHeight = imgSrcHeight
						* (UserUtils.DEFAULT_WEIGHT / imgSrcWidth);
			} else {
				imgHeight = UserUtils.DEFAULT_HEIGHT;
				imgWidth = imgSrcWidth
						* (UserUtils.DEFAULT_HEIGHT / imgSrcHeight);
			}
			/* 如果图片宽度/高度大于３或者<1/3,表示图片太宽或者太高。 */
			if (imgSrcWidth / imgSrcHeight > 3
					|| imgSrcHeight / imgSrcWidth > 3) {
				imgWidth = UserUtils.DEFAULT_WEIGHT;
				imgHeight = UserUtils.DEFAULT_HEIGHT;
			}
			
			/*将源文件按照缩略好的宽高值进行缩略，并将源文件覆盖掉*/
			ImageCut.scale(fileName, fileName, (int) imgWidth, (int) imgHeight);
			// File f = new File(filePath);
			// return f;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// �Ρ�������ɶ����...
		// cut("c:/images/ipomoea.jpg", "c:/images/t/ipomoea.jpg", 200, 150);
		// ok
		// gray("c:/images/ipomoea.jpg", "c:/images/t/ipomoea.jpg");
		// convert("c:/images/ipomoea.jpg", "c:/images/t/ipomoea.gif");
		// scale("c:/images/5105049910001020110718648723.jpg",
		// "c:/images/t/5105049910001020110718648725.jpg",154,166,157,208);
		// scale("c:/images/rose1.jpg",
		// "c:/images/t/rose1.jpg",154,166,157,208);
		scale("c:/images/rose1.jpg", "c:/images/t/rose2.jpg", 154, 166, 10, 10);

	}
}