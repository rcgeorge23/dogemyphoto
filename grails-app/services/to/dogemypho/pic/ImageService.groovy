package to.dogemypho.pic

import java.awt.Color
import java.awt.Graphics2D
import java.awt.Image
import java.awt.RenderingHints
import java.awt.geom.AffineTransform
import java.awt.image.BufferedImage

import javax.imageio.ImageIO
import javax.swing.ImageIcon

import com.sun.image.codec.jpeg.JPEGCodec
import com.sun.image.codec.jpeg.JPEGImageEncoder

class ImageService {

	def grailsApplication

	def byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		// Get the size of the file
		long length = file.length();
		if (length > Integer.MAX_VALUE) {
			// File is too large
			return null
		}
		// Create the byte array to hold the data
		byte[] bytes = new byte[(int)length];
		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
		&& (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
			offset += numRead;
		}
		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "+file.getName());
		}
		// Close the input stream and return bytes
		is.close();
		return bytes;
	}
	
	public byte[] createThumbnail(File file, int largestDimension) {
		return createThumbnail(getBytesFromFile(file), largestDimension)
	}

	public byte[] createThumbnail(byte[] bytes, int largestDimension) {
		log.debug("Going to thumbnail image. Largest dimension=${largestDimension}px")

		if (bytes == null || bytes.length == 0) {
			log.debug("Image byte[] is empty, so not going to bother...")
			return bytes
		}

		double scale;
		int sizeDifference, originalImageLargestDim;
		Image inImage = ImageIO.read(new ByteArrayInputStream(bytes));

		//find biggest dimension
		if (inImage.getWidth(null) > inImage.getHeight(null)) {
			scale = (double)largestDimension/(double)inImage.getWidth(null);
			sizeDifference = inImage.getWidth(null) - largestDimension;
			originalImageLargestDim = inImage.getWidth(null);
		} else {
			scale = (double)largestDimension/(double)inImage.getHeight(null);
			sizeDifference = inImage.getHeight(null) - largestDimension;
			originalImageLargestDim = inImage.getHeight(null);
		}
		//create an image buffer to draw to
		BufferedImage outImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB); //arbitrary init so code compiles
		Graphics2D g2d;
		AffineTransform tx;
		if(scale < 1.0d) //only scale if desired size is smaller than original
		{
			double numSteps = sizeDifference / 100;
			double stepSize = sizeDifference / numSteps;
			double stepWeight = stepSize/2;
			double heavierStepSize = stepSize + stepWeight;
			double lighterStepSize = stepSize - stepWeight;
			double currentStepSize, centerStep;
			double scaledW = inImage.getWidth(null);
			double scaledH = inImage.getHeight(null);
			if(numSteps % 2 == 1) //if there's an odd number of steps
				centerStep = (int)Math.ceil((double)numSteps / 2d); //find the center step
			else
				centerStep = -1; //set it to -1 so it's ignored later
			Integer intermediateSize = originalImageLargestDim, previousIntermediateSize = originalImageLargestDim;
			for(Integer i=0; i<numSteps; i++)
			{
				if(i+1 != centerStep) //if this isn't the center step
				{
					if(i == numSteps-1) //if this is the last step
					{
						//fix the stepsize to account for decimal place errors previously
						currentStepSize = previousIntermediateSize - largestDimension;
					}
					else
					{
						if(numSteps - i > numSteps/2) //if we're in the first half of the reductions
							currentStepSize = heavierStepSize;
						else
							currentStepSize = lighterStepSize;
					}
				}
				else //center step, use natural step size
				{
					currentStepSize = stepSize;
				}
				intermediateSize = previousIntermediateSize - currentStepSize;
				scale = (double)intermediateSize/(double)previousIntermediateSize;
				scaledW = (int)scaledW*scale;
				scaledH = (int)scaledH*scale;
				outImage = new BufferedImage((int)scaledW, (int)scaledH, BufferedImage.TYPE_INT_RGB);
				g2d = outImage.createGraphics();
				g2d.setBackground(Color.WHITE);
				g2d.clearRect(0, 0, outImage.getWidth(), outImage.getHeight());
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				tx = new AffineTransform();
				tx.scale(scale, scale);
				g2d.drawImage(inImage, tx, null);
				g2d.dispose();
				inImage = new ImageIcon(outImage).getImage();
				previousIntermediateSize = intermediateSize;
			}
		}
		else
		{
			//just copy the original
			outImage = new BufferedImage(inImage.getWidth(null), inImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
			g2d = outImage.createGraphics();
			g2d.setBackground(Color.WHITE);
			g2d.clearRect(0, 0, outImage.getWidth(), outImage.getHeight());
			tx = new AffineTransform();
			tx.setToIdentity(); //use identity matrix so image is copied exactly
			g2d.drawImage(inImage, tx, null);
			g2d.dispose();
		}
		//JPEG-encode the image and write to file.
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
		encoder.encode(outImage);
		return os.toByteArray();
	}
}
