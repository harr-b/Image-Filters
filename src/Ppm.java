import java.awt.Color;

/**
 * Class to run through each pixel in a .ppm image that will be passed through
 * the PpmDriver class
 */
public class Ppm extends PpmDriver {

	public Color[][] data;

	/**
	 * Constructor for the Ppm object for that specific pixel, it's RGB values
	 * and it's position
	 * 
	 * @param data
	 */
	public Ppm(Color[][] data) {
		this.data = data;
	}

	/**
	 * Returns the data in the 2D array OK
	 * 
	 * @return Returns the 2d array of the data for that specific image
	 */
	public Color[][] getData() {
		return data;
	}

	/**
	 * Darkens all the pixels in the image OK
	 * 
	 * @return Image type that is being returned
	 */
	public Ppm darken() {
		int row = data.length;
		int column = data[0].length;
		//System.out.println(column); // 4
		//System.out.println(row); // 3

		Color[][] darkenData = new Color[row][column];

		// Goes through each pixel with these two loops
		for (int x = 0; x < row; x++) {
			for (int y = 0; y < column; y++) {
				// System.out.println(y);
				int red = data[x][y].getRed();
				int green = data[x][y].getGreen();
				int blue = data[x][y].getBlue();

				// Lowers the RGB values of each.
				// These if statements make sure not to make the values
				// go below 0.
				if (red < 50) {
					red = 0;
				} else {
					red = red - 50;
				}

				if (green < 50) {
					green = 0;
				} else {
					green = green - 50;
				}

				if (blue < 50) {
					blue = 0;
				} else {
					blue = blue - 50;
				}

				Color dark = new Color(red, green, blue);
				darkenData[x][y] = dark;
			}
		}

		Ppm darken = new Ppm(darkenData);
		return darken;
	}

	/**
	 * Lightens all the pixels in the image OK
	 * 
	 * @return Image type that is being returned
	 */
	public Ppm lighten() {
		int column = data.length;
		int row = data[0].length;
		Color[][] lightenData = new Color[column][row];

		// Goes through each pixel with these two loops
		for (int x = 0; x < column; x++) {
			for (int y = 0; y < row; y++) {
				int red = data[x][y].getRed();
				int green = data[x][y].getGreen();
				int blue = data[x][y].getBlue();

				// Increases the RGB values of each.
				// These if statements make sure not to make the values
				// go above 255.
				if (red > 205) {
					red = 255;
				} else {
					red = red + 50;
				}

				if (green > 205) {
					green = 255;
				} else {
					green = green + 50;
				}

				if (blue > 205) {
					blue = 255;
				} else {
					blue = blue + 50;
				}

				Color light = new Color(red, green, blue);
				lightenData[x][y] = light;
			}
		}

		Ppm lighten = new Ppm(lightenData);
		return lighten;
	}

	/**
	 * Converts the color image to greyscale. Does this by averaging the values
	 * of R, G and B for each pixel. OK
	 * 
	 * @return Image type that is being returned
	 */
	public Ppm greyscale() {
		int column = data.length;
		int row = data[0].length;
		Color[][] greyscaleData = new Color[column][row];

		// Goes through each pixel with these two loops
		for (int x = 0; x < column; x++) {
			for (int y = 0; y < row; y++) {
				int red = data[x][y].getRed();
				int green = data[x][y].getGreen();
				int blue = data[x][y].getBlue();

				int average = (red + green + blue) / 3;
				red = average;
				green = average;
				blue = average;

				Color grey = new Color(red, green, blue);
				greyscaleData[x][y] = grey;
			}
		}
		Ppm greyscale = new Ppm(greyscaleData);
		return greyscale;
	}

	/**
	 * Converts all the pixels in the image to the negative. Does this by taking
	 * the absolute value of each R, G and B value in each pixel subtracted by
	 * 255. OK
	 * 
	 * @return Image type that is being returned
	 */
	public Ppm invert() {
		int column = data.length;
		int row = data[0].length;
		Color[][] invertData = new Color[column][row];

		// Goes through each pixel with these two loops
		for (int x = 0; x < column; x++) {
			for (int y = 0; y < row; y++) {
				int red = data[x][y].getRed();
				int green = data[x][y].getGreen();
				int blue = data[x][y].getBlue();

				red = Math.abs(red - 255);
				green = Math.abs(green - 255);
				blue = Math.abs(blue - 255);

				Color invertO = new Color(red, green, blue);
				invertData[x][y] = invertO;
			}
		}

		Ppm invert = new Ppm(invertData);
		return invert;
	}

	/**
	 * Converts the image to a upside down version of the original OK OK
	 * 
	 * @return Image type that is being returned
	 */
	public Ppm flipVertical() {
		int column = data.length;
		int row = data[0].length;
		Color[][] flipVerticalData = new Color[column][row];

		// Goes through each pixel with these two loops
		for (int x = 0; x < column; x++) {
			for (int y = 0; y < row; y++) {
				int red = data[column - x - 1][y].getRed();
				int green = data[column - x - 1][y].getGreen();
				int blue = data[column - x - 1][y].getBlue();

				Color flipVerticalO = new Color(red, green, blue);
				flipVerticalData[x][y] = flipVerticalO;
			}
		}
		Ppm flipVertical = new Ppm(flipVerticalData);
		return flipVertical;
	}

	/**
	 * Converts the image to a side to side version of the original OK OK
	 * 
	 * @return Image type that is being returned
	 */
	public Ppm flipHorizontal() {
		int column = data.length;
		int row = data[0].length;

		Color[][] flipHorizontalData = new Color[column][row];

		// Goes through each pixel with these two loops
		for (int x = 0; x < column; x++) {
			for (int y = 0; y < row; y++) {
				int red = data[x][row - y - 1].getRed();
				int green = data[x][row - y - 1].getGreen();
				int blue = data[x][row - y - 1].getBlue();

				Color flipHorizontalO = new Color(red, green, blue);
				flipHorizontalData[x][y] = flipHorizontalO;
			}
		}
		Ppm flipHorizontal = new Ppm(flipHorizontalData);
		return flipHorizontal;
	}

	/**
	 * Adds random color values to every 5th pixel in the image OK
	 * 
	 * @return The new Ppm object
	 */
	public Ppm addNoise() {
		int column = data.length;
		int row = data[0].length;
		int red = 0;
		int green = 0;
		int blue = 0;
		Color[][] addNoiseData = new Color[column][row];

		// Goes through each pixel with these two loops
		for (int x = 0; x < column; x++) {
			for (int y = 0; y < row; y++) {

				// If the specific pixel is chosen at random to be changed (To
				// add noise to image)
				if (Math.random() > 0.8) {
					/*
					 * Code relevant to the red(r) variable in the Color Object
					 */
					int randomRed = (int) (Math.random() * ((75) + 1));
					red = data[x][y].getRed();
					// Randomly adds or subtracts the random numbers
					// Adds
					if (Math.random() > .5) {
						// Checks if pixel value will be out of bounds
						if (red + randomRed > 255) {
							red = 255;
						} else {
							red = red + randomRed;
						}
					}
					// Subtract
					else {
						// Checks if pixel value will be out of bounds
						if (red - randomRed < 0) {
							red = 0;
						} else {
							red = red - randomRed;
						}
					}

					/*
					 * Code relevant to the green(g) variable in the Color
					 * Object
					 */
					int randomGreen = (int) (Math.random() * ((75) + 1));
					green = data[x][y].getGreen();
					// Randomly adds or subtracts the random numbers
					// Adds
					if (Math.random() > .5) {
						// Checks if pixel value will be out of bounds
						if (green + randomGreen > 255) {
							green = 255;
						} else {
							green = green + randomGreen;
						}
					}
					// Subtract
					else {
						// Checks if pixel value will be out of bounds
						if (green - randomGreen < 0) {
							green = 0;
						} else {
							green = green - randomGreen;
						}
					}

					/*
					 * Code relevant to the blue(b) variable in the Color Object
					 */
					int randomBlue = (int) (Math.random() * ((75) + 1));
					blue = data[x][y].getBlue();
					// Randomly adds or subtracts the random numbers
					// Adds
					if (Math.random() > .5) {
						// Checks if pixel value will be out of bounds
						if (blue + randomBlue > 255) {
							blue = 255;
						} else {
							blue = blue + randomBlue;
						}
					}
					// Subtract
					else {
						// Checks if pixel value will be out of bounds
						if (blue - randomBlue < 0) {
							blue = 0;
						} else {
							blue = blue - randomBlue;
						}
					}

				}
				// If specific pixel is not chosen to be changed based on the
				// Math.random operation
				else {
					red = data[x][y].getRed();
					green = data[x][y].getGreen();
					blue = data[x][y].getBlue();
				}
				Color addNoiseO = new Color(red, green, blue);
				addNoiseData[x][y] = addNoiseO;
			}
		}
		Ppm addNoise = new Ppm(addNoiseData);
		return addNoise;
	}

	/**
	 * Changes each rgb value to either 255, or 0 depending on how OK
	 * 
	 * @return
	 */
	public Ppm posterize() {
		int column = data.length;
		int row = data[0].length;
		Color[][] posterizeData = new Color[column][row];

		// Goes through each pixel with these two loops
		for (int x = 0; x < column; x++) {
			for (int y = 0; y < row; y++) {
				int red = data[x][y].getRed();
				int green = data[x][y].getGreen();
				int blue = data[x][y].getBlue();

				/*
				 * For Red
				 */
				if (red > 127) {
					red = 255;
				} else {
					red = 0;
				}

				/*
				 * For Green
				 */
				if (green > 127) {
					green = 255;
				} else {
					green = 0;
				}

				/*
				 * For Blue
				 */
				if (blue > 127) {
					blue = 255;
				} else {
					blue = 0;
				}

				Color posterizeO = new Color(red, green, blue);
				posterizeData[x][y] = posterizeO;
			}
		}

		Ppm posterize = new Ppm(posterizeData);
		return posterize;
	}
}