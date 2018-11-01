# &quot;Relief&quot;

Relief is an Android app for disaster management at the very personal level. This app is helpful for everyone especially for the people living in a disaster-prone area. The app helps people to know about the ongoing disaster so that they can know wether they are in a disaster prone area on not. App also tells what do it in a situation if the user or someone else is disaster-struck. It will also provide the platform to find missing people.

## Installation
   Downoad the below given .apk file into your android phone. App works fine for android version kitkat and above. We have tried our best to make the app bug free but let us know if found any.
  
   > [relief.apk](https://reliefdisaseter.blob.core.windows.net/relief-app/relief.apk)
  

## Key Features

- ### Provide Realtime data of Ongoing Disaster
    Realtime data of ongoing disaster is shown with their location, type and latitude and longitude with the help of json parsing. We have used and api provided by the nasa which provide the data about all the disaster that took place in last one year and the ongoing disaster to get the data. We have used json filtering to extract the desired information of ongoing diaster.
    API used:
    ```
    https://eonet.sci.gsfc.nasa.gov/api/v2.1/events/
    ```
- ### Showing location of disasters on Maps
   The latitude and longitude of the locations where the disaster is ongoing is parsed from the api. These are then used to locate the location on the google maps. For Map we have used Google Maps api.
    
- ### Lost and Found
     The app provides the functionality to the user to search for any of his/her relatives, friends etc. who is lost in the disaster by providing the details of the lost person. These details will be displayed on the dashboard so that if everybody knows about the lost person and they can inform the concerned person in case they find him/her.
- ### SOS
    In disaster there might be a scenerio that a person might stuck in acute condition and does not have time to serach for the helpline numbers or might left with very less battey backup.
    This app provides the one click call facility. A person can click on SOS button and select the helpline number he/she wants to call to. Helpline numbers consist of disaster helpline numbers, numbers for medical emrgency, contact of firebrigade, etc.
    
- ### Safety  Tips 
    App provides the info about the various do's and don'ts at the time of diaster. It also provides some preventive measures to minimize the loss at the time of the disaster.
