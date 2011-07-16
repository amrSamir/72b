package com.OJToolkit.client.Contents;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

/**
 * @author 72B
 *
 */
public interface MyResource extends ClientBundle {
	MyResource INSTANCE = GWT.create(MyResource.class);

	
	@Source("TimusProblems.txt")
	public ExternalTextResource TimusProblems();
	@Source("SPOJProblems.txt")
	public ExternalTextResource SPOJProblems();
	@Source("UVAProblems.txt")
	public ExternalTextResource UVAProblems();
	@Source("LiveArchiveProblems.txt")
	public ExternalTextResource LiveArchiveProblems();
	
	@Source("TimusProblemsText.txt")
	public ExternalTextResource TimusProblemText();
	
	@Source("SPOJ1.txt")
	public ExternalTextResource SPOJProblemText1();
	
	@Source("SPOJ2.txt")
	public ExternalTextResource SPOJProblemText2();

	@Source("uva1.txt")
	public ExternalTextResource UVAProblemText1();

	
	@Source("uva2.txt")
	public ExternalTextResource UVAProblemText2();
	
	
	@Source("uva3.txt")
	public ExternalTextResource UVAProblemText3();
	
	
	@Source("uva4.txt")
	public ExternalTextResource UVAProblemText4();
	
	
	@Source("uva5.txt")
	public ExternalTextResource UVAProblemText5();
	
	
	@Source("uva6.txt")
	public ExternalTextResource UVAProblemText6();
	
	
	@Source("uva7.txt")
	public ExternalTextResource UVAProblemText7();
	
	
	@Source("uva8.txt")
	public ExternalTextResource UVAProblemText8();
	
	

	@Source("Banner.jpg")
	public ImageResource imgBanner();
	
	
	@Source("loading.gif")
	public ImageResource imgLoading();

	@Source("Banner_Vertical.jpg")
	public ImageResource imgVertical();
	
	@Source("Logo.png")
	public ImageResource imgLogo();
	
	@Source("LogoText.png")
	public ImageResource imgLogoText();

	@Source("feedback.png")
	public ImageResource imgFeedback();
	
	@Source("feedback2.png")
	public ImageResource imgFeedback2();
	
	@Source("cu_logo.jpg")
	public ImageResource imgCULogo();
	
	@Source("fci_logo.gif")
	public ImageResource imgFCILogo();

	
	@Source("amr.jpg")
	public ImageResource imgAmr();
	
	@Source("azraq.jpg")
	public ImageResource imgAzraq();
	
	@Source("magdi.jpg")
	public ImageResource imgMagdi();
	
	@Source("nasser.jpg")
	public ImageResource imgNasser();
	
	
	@Source("omar.jpg")
	public ImageResource imgOmar();
	
}
