package com.disaster.relief.relief;

/**
 * Created by aryan on 26/10/18.
 */

public class urlBook {
    String urls[]={
            "https://eonet.sci.gsfc.nasa.gov/api/v2.1/categories/9?status=open",//floods
            "https://eonet.sci.gsfc.nasa.gov/api/v2.1/categories/16?status=open", //earthquakes
            "https://eonet.sci.gsfc.nasa.gov/api/v2.1/categories/14?status=open", //Landslides
            "https://eonet.sci.gsfc.nasa.gov/api/v2.1/categories/10?status=open" //Severe Storms
    };
    String getString(int i){

        return urls[i];
    };
    int getSize(){
        return urls.length;
    }
}
