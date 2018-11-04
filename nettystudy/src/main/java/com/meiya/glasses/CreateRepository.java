package com.meiya.glasses;

public class CreateRepository {

	public native int init(String strDeployFile, String strTypeFile, String strDetector, String strAlignment, int nDeviceNo, int nFeatureDim);

	public native float[][] getFeature(String strImgPath,int nMinFace);

	public native int[] extractFace(String strImgPath, int nMinFace);

	public native float compareFeature(float[] featureOne, float[] featureTwo);

	public native void releaseFaceProcInstance();
	
	static{
		System.loadLibrary("GlassesProject");
	}
	
	public static CreateRepository dll = new CreateRepository();
		
	public static void main(String[] args){

		int flag = dll.init("c:\\caffemodel\\1.001.128.001\\PTFD3C8TVHJHS85",
				"c:\\caffemodel\\1.001.128.001\\CAFD3C8TVHJHS85",
				"c:\\caffemodel\\cpu\\FaceDetModel",
				"",-1, 128);
		System.out.println(111);
		float[][] feature = {};
		try{
			feature = CreateRepository.dll.getFeature("D:\\exportFace\\20180129185239\\4567.jpg",60);
		}catch(Throwable e){
			e.printStackTrace();
		}
		
		System.out.print(feature[0][0] + "");
	}
}
