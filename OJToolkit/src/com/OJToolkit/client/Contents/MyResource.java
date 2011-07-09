package com.OJToolkit.client.Contents;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

/**
 * @author 72B
 *
 */
public interface MyResource extends ClientBundle {
	MyResource INSTANCE = GWT.create(MyResource.class);

	@Source("problemsSPOJ.txt")
	public TextResource defaultText();
	
	@Source("SPOJProblemText.txt")
	public TextResource SPOJProblemText();
	

	@Source("Banner.jpg")
	public ImageResource imgBanner();

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
}
