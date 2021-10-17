package com.chenguang.ptam;

import java.io.IOException;
import java.util.List;

import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.util.Log;


/*
 * Manages the camera allocation/deallocation and provides an interface to grab
 * a frame
 */
public class VideoSource implements Camera.PreviewCallback {

	private Camera mCamera;
	private byte[] _frame;

	// private List<Camera.Size> mSupportedPreviewSizes;

	public VideoSource() {
		if (mCamera == null) {
			mCamera = getCameraInstance();
		}
		_frame = null;

		Parameters newParam = mCamera.getParameters();

		// https://juejin.im/entry/6844903638037430285

		List<Camera.Size> mSupportedPreviewSizes = newParam.getSupportedPreviewSizes();

		// boolean bBestSizeSelected = false;
		// for (Size size : mSupportedPreviewSizes) {					
		// 	int longer = size.height>=size.width ? size.height : size.width;
		// 	int smaller= size.height< size.width ? size.height : size.width;
		// 	float ratio = longer / (float)smaller;
		// 	if(ratio>1.3 && ratio<1.4 && longer>1000 && longer<5000 && !bBestSizeSelected){
		// 		newParam.setPreviewSize(size.width, size.height);
		// 		bBestSizeSelected = true;
		// 	}
		// }

		// newParam.set("orientation", "landscape");
		// newParam.setRotation(0);		

		newParam.setPreviewSize(640, 480);
		newParam.setPreviewFormat(ImageFormat.YV12);
		// newParam.setFlashMode(Parameters.FLASH_MODE_TORCH);
		// newParam.setFocusMode(Parameters.FOCUS_MODE_INFINITY);

		mCamera.setDisplayOrientation(90);

		mCamera.setParameters(newParam);
		mCamera.setPreviewCallback(this);
	}
	
	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {
		_frame = data;
	}
	
	public void set_texture(SurfaceTexture tex) {
		try {
			mCamera.setPreviewTexture(tex);
			mCamera.startPreview();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** A safe way to get an instance of the Camera object. */
	public static Camera getCameraInstance() {
		Camera c = null;
		try {
			c = Camera.open();
		} catch (Exception e) {
			Log.i("PTAM","cannot get camera");
		}
		return c;
	}

	public int[] getSize() {
		int[] size = { mCamera.getParameters().getPreviewSize().width,
				mCamera.getParameters().getPreviewSize().height };
		return size;
	}
	
	public void camera_release() {
		if (mCamera != null) {
			mCamera.setPreviewCallback(null);
			mCamera.release();
			mCamera = null;
		}
	}
	
	public byte[] getFrame() {
		return _frame;
	}
}