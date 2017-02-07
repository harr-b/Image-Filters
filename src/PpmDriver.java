import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Color;

public class PpmDriver {

	static String format;

	/**
	 * This method takes a .ppm file and turns that data into a 2D array of
	 * Color objects
	 * 
	 * @param file
	 *            Gives the method a .ppm file to extract data from
	 * @return The 2D Color array, to be passed to the Ppm class to be filtered
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static Color[][] load(String file) throws IOException {
		BufferedReader begin = new BufferedReader(new FileReader(file));
		File startFile = new File(file);
		int column = 0;
		int row = 0;
		Color[][] totalData = null;
		int x = 0;
		int y = 0;
		
		Scanner fileScanner = new Scanner(startFile);
		format = fileScanner.next();

		while (fileScanner.hasNext()) {
			// Handles comments
			String line = fileScanner.nextLine();
			if ((line.startsWith("#"))) {
				continue;
			}
			
			row = fileScanner.nextInt();
			column = fileScanner.nextInt();
			
			
			//Skips the Max color value
			fileScanner.next();
			
			totalData = new Color[row][column];
			x = 0;
			y = 0;
			
			// Sets the values of the 2D array of Color data to be returned
			while (fileScanner.hasNext()) {	
				if (y == column) {
					x++;
					y = 0;
				}
				Color next = new Color(fileScanner.nextInt(), fileScanner.nextInt(), fileScanner.nextInt());
				
				totalData[x][y] = next;
				y++;
			}
			
		}
		fileScanner.close();
		begin.close();
		return totalData;
	}

	/**
	 * This method is used to save the filtered .ppm data to the system
	 * 
	 * @param image
	 *            The Ppm object that needs to be saved to the directory of the
	 *            program
	 * @throws FileNotFoundException 
	 */
	public static void save(Ppm image, String fileName) {	
		Color[][] newData = image.getData();
		int dim1Length = newData.length;
		int dim2Length = newData[0].length;
		PrintWriter newPic = null;
		File name;
		
		try{
			name = new File(fileName);
			newPic = new PrintWriter(name);
			
			//Starts writing text to new .ppm file
			newPic.println("P3");
			newPic.println(dim1Length + " " + dim2Length);
			newPic.println(255);
			
			//Iterates through data and adds it to the file
			for (int x = 0; x < dim1Length; x++) {
				for (int y = 0; y < dim2Length; y++) {
					int red = newData[x][y].getRed();
					int green = newData[x][y].getGreen();
					int blue = newData[x][y].getBlue();
					
					newPic.println(red);
					newPic.println(green);
					newPic.println(blue);
				}
			}			
			newPic.close();
		}catch (FileNotFoundException e){
			System.out.println("File could not be found");
		}catch (IOException i){
		}finally{
			if(newPic != null){
				newPic.close();
			}
		}
	}

	/**
	 * Main method to run the overall program
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String fileName = "";
		Color[][] totalData = null;
		boolean ok = false;
		
		
		//Makes sure file that is loaded can be found
		while (ok == false) {
			try {
				System.out.println("What is the name of the file you would like to load?");
				fileName = keyboard.next();
				totalData = load(fileName);
				ok = true;
			} catch (FileNotFoundException e) {
				System.out.println("This file cannot be found");
			} catch (IOException i) {
				System.out.println("There was a problem when loading your file");
			}
					
		}
		keyboard.close();
		
		Ppm image = new Ppm(totalData);
		int indexOfDot = fileName.indexOf('.');
		String beforeDot = fileName.substring(0, indexOfDot);

		// Runs the method through all 8 filters
		// Ppm.getData method
		System.out.println(image.getData());

		// Ppm.darken method
		save(image.darken(), (beforeDot + "_darken.ppm"));

		// Ppm.lighten method
		save(image.lighten(), (beforeDot + "_lighten.ppm"));

		// Ppm.greyscale method
		save(image.greyscale(), (beforeDot + "_greyscale.ppm"));

		// Ppm.invert method
		save(image.invert(), (beforeDot + "_invert.ppm"));

		// Ppm.flipVertical method
		save(image.flipVertical(), (beforeDot + "_flipVertical.ppm"));

		// Ppm.flipHorizontal method
		save(image.flipHorizontal(), (beforeDot + "_flipHorizontal.ppm"));

		// Ppm.addNoise method
		save(image.addNoise(), (beforeDot + "_addNoise.ppm"));

		// Ppm.posterize method
		save(image.posterize(), (beforeDot + "_posterize.ppm"));

	}
}
