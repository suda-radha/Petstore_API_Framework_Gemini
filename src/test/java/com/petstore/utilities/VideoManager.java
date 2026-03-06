package com.petstore.utilities;

import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import java.awt.*;
import java.io.File;
import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class VideoManager {
	private static ScreenRecorder screenRecorder;

	public static void startRecording(String fileName) throws Exception {
		// 1. Define the folder
		File folder = new File("test-output/Videos/");
		if (!folder.exists())
			folder.mkdirs();

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();

		// 2. Initialize the recorder
		screenRecorder = new ScreenRecorder(gc, gc.getBounds(),
				new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
						CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
						Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
				null, folder) {

			@Override
			protected File createMovieFile(Format fileFormat) throws java.io.IOException {
				// We use 'folder' directly here instead of getMovieFolder()
				return new File(folder, fileName.replaceAll("[^a-zA-Z0-9]", "_") + ".avi");
			}
		};

		screenRecorder.start();
	}

	public static void stopRecording() throws Exception {
		if (screenRecorder != null) {
			screenRecorder.stop();
		}
	}
}
