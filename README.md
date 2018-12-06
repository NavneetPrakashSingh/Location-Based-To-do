# Identification
#### To-do List Application <img align="right" src="/ProjectDocumentation/LogoDesignFiles/logo_rounded.png" alt="Logo Icon"  width="512" height="413" style="float: right; margin-right: 10px;" />

Group Members: 

- Jessica Castelino  B00804805	
- Navneet Singh      B00810744	
- Shubham Narang     B00810159
- Sharon Alva        B00813350 	
- Utsav Soni         B00812689
- Jismy Johnson      B00813344

Contact email: jessica.castelino@dal.ca

The code will be available on github on the link given below:

```
https://git.cs.dal.ca/castelino/To-Do-List

```

# Project Summary
To-Do list in an application that provides an easy way in managing day-to-day tasks. It not only reminds the user of its pending tasks, but also lets you prioritize them. To-Do app is targeted specifically for students. It helps students to be more productive and manage their time efficiently. The students can make grocery lists, write notes, set reminders, capture pictures and save it. The app allows students to set reminders based on location and automatically reminds them when they are near the location. Apart from creating a list of tasks, students can sort tasks based on priority and date.Each task can be tagged to a location. This feature provides the user with location-based notification when user is near that location. Task progress of each task is shown based on list items pending. Additionally, we can give voice inputs to further save the time it takes to make these lists and plan the day. All in all, the app aids in distributing time and efforts wisely.


## Libraries
List of all the libraries used in the project

**v7 Support Libraries:** CardView package of this library was used for displaying tasks list. Source [here](https://developer.android.com/topic/libraries/support-library/packages)

**Google Play Services** Inorder to run certain services like taking location of the user in the background is done using Google play services. Source [here](https://developers.google.com/android/guides/setup)

**Room Persistence** Room database library is used create and store database within the device of the user. Source [here](https://developer.android.com/guide/topics/location/)

## Installation Notes
Kindly perform the below steps to run the code:

```
1) Run the command 'git clone https://git.cs.dal.ca/castelino/To-Do-List'.
2) Import the code in Android Studio.
3) Ensure that the gradle version denoted in build.gradle file is 3.1.4.
4) Click the button 'Sync Project With Gradle Files' or go to Tools -> Android -> Sync Project with Gradle Files.
5) Run the app on the emulator or the mobile phone (App is for Android API Version 23 or higher).

```
## Code Examples

**Problem 1: We needed a method to convert BitMap image file to byte array and vice versa**

 We basically converted the image file (BitMap) in to byte array streams and then stored it in Room column whose data type is BLOB (Room database supports it).
```
// convert  bitmap image file to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

// Source: Wikipedia Java [1]
```
To read and render it back the image from BLOB storage, we reversed the process - that is reading the byte streams from BLOB storage of Room database and convert in to a BitMap.

```
// convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

// Source: Wikipedia Java [1]
```

**Problem 2: Code abstraction to access same feature from multiple places**

We had a major issue of code present in multiple places within the applicaiton. To solve this problem we had restructured our code and used classes to access it from multiple places providing proper abstraction. Consider the code to access location, which is used when the app launches and when the app is in backgruond to get the current location of the user.
```
// Sample input : source from where it is called
// Sample output: returns the logitude and latitude of the user

            @Override
            public String onPlaceSelected(String source) {
                return place.getLatLang();
            }
            
```


**Problem 3: Code to check internet connectivity**

 As we are including fetching location from Google API which requires internet connectivity, we do need to have something to indicate users in case of no connectivity. For this we uses Connectivity Manager

```
// Return true if no internet connectivity
//Checking Internet connectivity is there or not

    public static boolean checkNetworkAvailability(Context context)
    {
        //return if connected
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

// Source: Android Developer [9]    
            
```


## Progress Section
1. Paper Wireframes: https://git.cs.dal.ca/castelino/To-Do-List/blob/master/ProjectDocumentation/PaperWireframes/paperWireframes.jpg
3. Low Fidelity Prototypes: https://git.cs.dal.ca/castelino/To-Do-List/tree/master/ProjectDocumentation/Prototype
2. Clickstreams: https://git.cs.dal.ca/castelino/To-Do-List/blob/master/ProjectDocumentation/Clickstreams/Clickstreams_video.mp4?expanded=true&viewer=rich
3. High Fidelity prototypes: https://git.cs.dal.ca/castelino/To-Do-List/tree/master/ProjectDocumentation/Wireframe
4. Analysis : https://git.cs.dal.ca/castelino/To-Do-List/tree/master/ProjectDocumentation/Analysis

## Feature Section
1. **Add new task**
&nbsp;The application allows users to add a new task such as “Grocery”. This task can have multiple sub-tasks such as “Milk, bread, cheese” added under Grocery. The task title and its sub-task elements are stored in the database. 

2. **View tasks on home screen**
&nbsp;Every task created will be displayed on the home screen of the application along with visualization of amount of sub-tasks completed. Instead of displaying all the subtasks at home screen, only the task title is displayed. This removes clutter on the home screen. 

3. **Checking/unchecking task**
&nbsp;The application allows the user to check off the task is complete. Once the task is complete, a user may want to either check or uncheck the task. This gives the user an idea of how many tasks are completed. It helps the user to focus on other pending activities. 

4. **Update existing task**
&nbsp;The application allows the user to update an existing task. As user freedom and control is of high importance, users can update an already created task. The app allows editing the task title, location, reminder date and the subtasks listed. This helps users in updating and changing as per their needs. 

5. **Delete task**
&nbsp;The application allows the user to delete an existing task. Once the task is selected, an option to delete is available on the menu bar and user can delete the task. The task can also be deleted from the main screen by using the swipe action. There are times when the task is of no value, too old to maintain, or an incorrect task detail. This feature allows users to delete unwanted tasks. 

6. **Camera feature**
&nbsp;The application allows a user to upload To Do list as an image. It provides two options to get an image. First, a user can use his camera to click a picture of a list and the application will upload that in the database. Second, a user can upload an existing picture from gallery.   

7. **Location tagging**
&nbsp;Location can be tagged against a task. User searches for the address or location and a list appear which is taken from Google API. Once the user selects the task, details are stored and reflected against the task that location. 

8. **Location-based reminder**
&nbsp;The application gives a provision to the user to set a location for every task. In the future, if the user happens to be around that location, a reminder will be sent to the user in the form of a notification. 

9. **Support & Feedback**
&nbsp;A simple feedback form will be displayed for the user to send their concerns and queries regarding the application. This feature will be accessible from the top right side of the application in action bar. By providing support regarding the app, it helps not only them with their concerns, but also us as to how effectively the application was developed. 


## Project Status
We have completed 100% of the project. Some bugs have been identified during testing and we are currently resolving them. We are also enhancing the UI to make it more intuitive. All the functionalities that were promised in project update 2 have been successfully completed. Additionally, we have implemented an extra feature which displays a progress bar to show the completion status of each task. This will help the students to monitor their progress. The future scope of this project will be to allow the students to use this app for note-taking. We can also support videos and voice notes along with images which will be really helpful and interesting.

#### Minimum Functionality
- Feature 1 Add new task (Completed)
- Feature 2 View tasks on home screen (Completed)
- Feature 3 Delete, Update existing task (Completed)
- Feature 4 Support & Feedback (Completed)

#### Expected Functionality
- Feature 1 Checking/unchecking task (Completed)
- Feature 2 Camera feature (Completed)

#### Bonus Functionality
- Feature 1 Location tagging (Completed)
- Feature 2 Location-based reminder (Completed)
- Feature 3 Visualise completed tasks (Completed)


## Sources
[1] Developers, G. (2018). App permissions best practices | Android Developers. Retrieved from https://developer.android.com/training/permissions/usage-notes<br/>
[2] Developers, G. Reduce the APK size | Android Developers. Retrieved from https://developer.android.com/topic/performance/reduce-apk-size <br/>
[3] Developers, G. Optimize for Doze and App Standby | Android Developers. Retrieved from https://developer.android.com/training/monitoring-device-state/doze-standby <br/>
[4] LocationManager  |  Android Developers. Retrieved from https://developer.android.com/reference/android/location/LocationManager.html#requestLocationUpdates(long,%20float,%20android.location.Criteria,%20android.app.PendingIntent) <br/>
[5] Location and context overview  |  Android Developers. Retrieved from https://developer.android.com/training/location/receive-location-updates <br/>
[6] Location strategies  |  Android Developers. (2018). Retrieved from https://developer.android.com/guide/topics/location/strategies#java <br/>
[7] Analyze power use with Battery Historian  |  Android Developers. Retrieved from https://developer.android.com/topic/performance/power/battery-historian <br/>
[8] Sensor  |  Android Developers. Retrieved from https://developer.android.com/reference/android/hardware/Sensor#TYPE_SIGNIFICANT_MOTION <br/>
[9] ConnectivityManager  |  Android Developers. Retrieved from https://developer.android.com/reference/android/net/ConnectivityManager<br/>
[10] Email, Camera and Calendar Icons - Free Download, PNG and SVG, Icons8.com. (2018). [Online]. Retrieved from https://icons8.com/icon/set/email/all.<br/>
[11] G. Training, "14.1A: Room, LiveData, ViewModel · Advanced Android Development Course- Practicals", Google-developer-training.gitbooks.io, 2018. [Online]. Available [here](https://google-developer-training.gitbooks.io/android-developer-advanced-course-practicals/content/unit-6-working-with-architecture-components/lesson-14-room,-livedata,-viewmodel/14-1-a-room-livedata-viewmodel/14-1-a-room-livedata-viewmodel.html). [Accessed: 05- Dec- 2018].