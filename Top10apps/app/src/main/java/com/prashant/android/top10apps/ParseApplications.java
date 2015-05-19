package com.prashant.android.top10apps;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by Prashant on 19-05-2015.
 */
public class ParseApplications {
    private String data;
    private ArrayList<Application> applications;

    public ParseApplications(String data) {
        this.data = data;
        applications = new ArrayList<Application>();
    }

    public ArrayList<Application> getApplications() {
        return applications;
    }

    public boolean process(){
        boolean operationStatus = true;
        Application currentRecord = null;
        boolean inEntry = false;
        String textValue="";
        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(this.data));
            int eventType = xpp.getEventType();
            while (eventType!=XmlPullParser.END_DOCUMENT){
                String tagName = xpp.getName();

                if (eventType == XmlPullParser.START_TAG){
                    if(tagName.equalsIgnoreCase("entry")){
                        inEntry = true;
                        currentRecord = new Application();
                    }
                }else if (eventType == XmlPullParser.TEXT){
                    textValue=xpp.getText();


                }else if (eventType == XmlPullParser.END_TAG){
                    if(inEntry){
                        if(tagName.equalsIgnoreCase("entry")){
                            applications.add(currentRecord);
                            inEntry=false;
                        }
                        else if (tagName.equalsIgnoreCase("name")){
                            currentRecord.setName(textValue);
                        }
                        else if (tagName.equalsIgnoreCase("artist")){
                            currentRecord.setArtist(textValue);
                        }
                        else if (tagName.equalsIgnoreCase("releaseDate")){
                            currentRecord.setReleaseDate(textValue);
                        }
                    }
                }
                eventType = xpp.next();
            }

        }
        catch (Exception e){
            e.printStackTrace();
            operationStatus=false;
        }
        for(Application app: applications){
            Log.d("Name",app.getName());
        }

        return operationStatus;
    }


}







