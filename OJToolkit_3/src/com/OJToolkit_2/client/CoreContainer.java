package com.OJToolkit_2.client;

import com.google.gwt.user.client.ui.Panel;

public class CoreContainer {
 
 private static CoreContainer coreContainer;
 public static CoreContainer getInstance(){
  if(coreContainer==null)
   throw new NullPointerException();
  return coreContainer;
 }
 
 public static void initialize(Panel core) {
  coreContainer = new CoreContainer(core);
 }

 private Panel core;
 
 private CoreContainer(Panel core){
  this.core = core;
 }
 
 public void setContent(Content content){
  core.clear();
  content.setSize("100%", "100%");
  core.add(content);
  
 }
}