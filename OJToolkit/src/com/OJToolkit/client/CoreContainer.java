package com.OJToolkit.client;

import com.OJToolkit.client.Contents.Content;
import com.google.gwt.user.client.ui.Panel;

/**
 * Singleton wrapper class to manage the core panel in the page.
 */
public class CoreContainer {
  private Panel core;
  
  // Singleton
  private static CoreContainer coreContainer;
 
  public static CoreContainer getInstance(){
    if(coreContainer==null)
      throw new NullPointerException();
    return coreContainer;
  }
 
  public static void initialize(Panel core) {
    coreContainer = new CoreContainer(core);
  }

  private CoreContainer(Panel core){
    this.core = core;
  }
 
  public void setContent(Content content){
    core.clear();
    content.setSize("100%", "100%");
    core.add(content);
  }
}