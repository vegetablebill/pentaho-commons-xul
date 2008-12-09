package org.pentaho.ui.xul.gwt;


import org.pentaho.gwt.widgets.client.utils.IMessageBundleLoadCallback;
import org.pentaho.gwt.widgets.client.utils.MessageBundle;
import org.pentaho.ui.xul.XulComponent;
import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.XulException;
import org.pentaho.ui.xul.gwt.tags.GwtBox;
import org.pentaho.ui.xul.gwt.tags.GwtButton;
import org.pentaho.ui.xul.gwt.tags.GwtCaption;
import org.pentaho.ui.xul.gwt.tags.GwtCheckbox;
import org.pentaho.ui.xul.gwt.tags.GwtDeck;
import org.pentaho.ui.xul.gwt.tags.GwtGroupBox;
import org.pentaho.ui.xul.gwt.tags.GwtHbox;
import org.pentaho.ui.xul.gwt.tags.GwtLabel;
import org.pentaho.ui.xul.gwt.tags.GwtListbox;
import org.pentaho.ui.xul.gwt.tags.GwtListitem;
import org.pentaho.ui.xul.gwt.tags.GwtOverlay;
import org.pentaho.ui.xul.gwt.tags.GwtScript;
import org.pentaho.ui.xul.gwt.tags.GwtSpacer;
import org.pentaho.ui.xul.gwt.tags.GwtTextbox;
import org.pentaho.ui.xul.gwt.tags.GwtToolbar;
import org.pentaho.ui.xul.gwt.tags.GwtToolbarbutton;
import org.pentaho.ui.xul.gwt.tags.GwtToolbarseparator;
import org.pentaho.ui.xul.gwt.tags.GwtToolbarset;
import org.pentaho.ui.xul.gwt.tags.GwtToolbarspacer;
import org.pentaho.ui.xul.gwt.tags.GwtTree;
import org.pentaho.ui.xul.gwt.tags.GwtTreeCell;
import org.pentaho.ui.xul.gwt.tags.GwtTreeChildren;
import org.pentaho.ui.xul.gwt.tags.GwtTreeCol;
import org.pentaho.ui.xul.gwt.tags.GwtTreeCols;
import org.pentaho.ui.xul.gwt.tags.GwtTreeItem;
import org.pentaho.ui.xul.gwt.tags.GwtTreeRow;
import org.pentaho.ui.xul.gwt.tags.GwtVbox;
import org.pentaho.ui.xul.gwt.tags.GwtWindow;
import org.pentaho.ui.xul.gwt.util.ResourceBundleTranslator;
import org.pentaho.ui.xul.service.XulLoaderService;
import org.pentaho.ui.xul.service.XulLoaderServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;

/**
 * @author nbaker
 *
 */
public class GwtXulLoader implements IMessageBundleLoadCallback{//implements XulLoader{

  private GwtXulParser parser;
  private Document doc;
  public static final XulLoaderServiceAsync SERVICE = (XulLoaderServiceAsync) GWT.create(XulLoaderService.class);
  static{
    ServiceDefTarget endpoint = (ServiceDefTarget) SERVICE;
    String moduleRelativeURL = GWT.getModuleBaseURL() + "XulLoaderService"; //$NON-NLS-1$
    endpoint.setServiceEntryPoint(moduleRelativeURL);
  }
  public GwtXulLoader() throws XulException{
    
    try{
      parser = new GwtXulParser();
    } catch(Exception e){
      throw new XulException("Error getting XulParser Instance, probably a DOM Factory problem: "+e.getMessage(), e);
    }
     
    // attach registers
    GwtBox.register();
    GwtButton.register();
    GwtCaption.register();
    GwtCheckbox.register();
    GwtDeck.register();
    GwtGroupBox.register();
    GwtHbox.register();
    GwtLabel.register();
    GwtListbox.register();
    GwtListitem.register();
    GwtScript.register();
    GwtSpacer.register();
    GwtTextbox.register();
    GwtVbox.register();
    GwtWindow.register();
    GwtToolbar.register();
    GwtToolbarseparator.register();
    GwtToolbarspacer.register();
    GwtToolbarset.register();
    GwtToolbarbutton.register();
    GwtOverlay.register();
    GwtTree.register();
    GwtTreeCols.register();
    GwtTreeCol.register();
    GwtTreeChildren.register();
    GwtTreeItem.register();
    GwtTreeRow.register();
    GwtTreeRow.register();
    GwtTreeCell.register();
      
  }
  
  /* (non-Javadoc)
   * @see org.pentaho.ui.xul.XulLoader#loadXul(org.w3c.dom.Document)
   */
  public GwtXulDomContainer loadXul(Document xulDocument) throws IllegalArgumentException, XulException{

    GwtXulDomContainer container = new GwtXulDomContainer();
    container.setLoader(this);
    parser.setContainer(container);
    parser.parseDocument(xulDocument.getDocumentElement());
   
    return container;
  }
  
  /* (non-Javadoc)
   * @see org.pentaho.ui.xul.XulLoader#loadXul(org.w3c.dom.Document)
   */
  public GwtXulDomContainer loadXul(Document xulDocument, MessageBundle bundle) throws IllegalArgumentException, XulException{
    String translated = ResourceBundleTranslator.translate(xulDocument.toString(), bundle);
    xulDocument = XMLParser.parse(translated);
    
    GwtXulDomContainer container = new GwtXulDomContainer();
    container.setLoader(this);
    parser.setContainer(container);
    parser.parseDocument(xulDocument.getDocumentElement());
   
    return container;
  }


  
  /* (non-Javadoc)
   * @see org.pentaho.ui.xul.XulLoader#loadXulFragment(org.dom4j.Document)
   */
  public XulDomContainer loadXulFragment(Document xulDocument) throws IllegalArgumentException, XulException {
    GwtXulDomContainer container = new GwtXulDomContainer();
    container.setLoader(this);
    parser.setContainer(container);
    parser.parseDocument(xulDocument.getDocumentElement());
    
    return container;
  }

  public XulComponent createElement(String elementName) throws XulException {
    return null;
  }

  public GwtXulLoader getNewInstance() throws XulException {
    return null;
  }

  public String getRootDir() {
    return "";  
  }

  public boolean isRegistered(String elementName) {
    return GwtXulParser.isRegistered(elementName);
  }

  public XulDomContainer loadXul(String resource) throws IllegalArgumentException, XulException {
    return null;  
  }


  public XulDomContainer loadXulFragment(String resource) throws IllegalArgumentException, XulException {
    return null;  
  }


  public void processOverlay(String overlaySrc, org.pentaho.ui.xul.dom.Document targetDocument,
      XulDomContainer container) throws XulException {
    
        // TODO Auto-generated method stub 
      
  }

  public void register(String tagName, String className) {
    
        // TODO Auto-generated method stub 
      
  }

  public void removeOverlay(String overlaySrc, org.pentaho.ui.xul.dom.Document targetDocument, XulDomContainer container)
      throws XulException {
    
        // TODO Auto-generated method stub 
      
  }

  public void setOuterContext(Object context) {
    
        // TODO Auto-generated method stub 
      
  }

  public void setRootDir(String str) {
    
        // TODO Auto-generated method stub 
      
  }


  public void bundleLoaded(String bundleName) {
    
  }

}