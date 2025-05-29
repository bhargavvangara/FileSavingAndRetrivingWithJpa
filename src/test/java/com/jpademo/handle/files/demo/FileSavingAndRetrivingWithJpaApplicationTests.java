package com.jpademo.handle.files.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpademo.handle.files.demo.entity.Image;

@SpringBootTest
class FileSavingAndRetrivingWithJpaApplicationTests {

	@Autowired
	private ImageRepository imageRepository;
	
	@Test
	void saveImage() throws IOException {
		Image image = new Image();
		image.setName("hanvika.jpg");
		File file = new File("C:\\Users\\Bhargav Vangara\\OneDrive\\Desktop\\hanvika.jpg");
		FileInputStream fileInputStream = new FileInputStream(file);
		byte[] bytes = fileInputStream.readAllBytes();
		fileInputStream.close();
		image.setData(bytes);
		imageRepository.save(image);
	}
	
	@Test
	void getImage() {
		Image image = imageRepository.findById(1L).orElse(null);
		if (image != null) {
			System.out.println("Image Name: " + image.getName());
			System.out.println("Image Data Length: " + image.getData().length);
			File outputFile = new File("C:\\Users\\Bhargav Vangara\\OneDrive\\Desktop\\output\\" + image.getName());
			outputFile.getParentFile().mkdirs(); // Ensure the directory exists
			try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
				outputStream.write(image.getData());
				System.out.println("Image saved to: " + outputFile.getAbsolutePath());
			} catch (IOException e) {
				System.err.println("Error saving image: " + e.getMessage());
			}
		} else {
			System.out.println("Image not found");
		}
	}

}
