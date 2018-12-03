# Identification
#### To-do List Application <img align="right" src="/ProjectDocumentation/LogoDesignFiles/todo.png" alt="Logo Icon"  width="150" height="113" style="float: right; margin-right: 10px;" />

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

## Installation Notes
Kindly perform the below steps to run the code:

```
1) Run the command 'git clone https://git.cs.dal.ca/castelino/To-Do-List'.
2) Import the code in Android Studio.
3) Ensure that the gradle version denoted in build.gradle file is 3.1.4.
4) Click the button 'Sync Project With Gradle Files' or go to Tools -> Android -> Sync Project with Gradle Files.
5) Run the app on the emulator or the mobile phone.

```
## Code Examples

**Problem 1: We needed a method to convert BitMap image file to byte array and vice versa **

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
## Feature Section
1) Add new task
The application allows users to add a new task such as “Grocery”. This task can have multiple sub-tasks such as “Milk, bread, cheese” added under Grocery. The task title and its sub-task elements are stored in the database. 

2) View tasks on home screen
Every task created will be displayed on the home screen of the application. The order of display is as per the creation time. Instead of displaying all the subtasks at home screen, only the task title is displayed. This removes clutter on the home screen. 

3) Checking/unchecking task
The application allows the user to check off the task is complete. Once the task is complete, a user may want to either check or uncheck the task. This gives the user an idea of how many tasks are completed. It helps the user to focus on other pending activities. 

4) Update existing task
The application allows the user to update an existing task. As user freedom and control is of high importance, users can update an already created task. The app allows editing the task title, location, reminder date and the subtasks listed. This helps users in updating and changing as per their needs. 

5) Delete task
The application allows the user to delete an existing task. Once the task is selected, an option to delete is available on the menu bar and user can delete the task. There are times when the task is of no value, too old to maintain, or an incorrect task detail. This feature allows users to delete unwanted tasks. 

6) Camera feature
The application allows a user to upload To Do list as an image. It provides two options to get an image. First, a user can use his camera to click a picture of a list and the application will upload that in the database. Second, a user can upload an existing picture from gallery.   

7) Location tagging
Location can be tagged against a task. User searches for the address or location and a list appear which is taken from Google API. Once the user selects the task, details are stored and reflected against the task that location. 

8) Location-based reminder
The application gives a provision to the user to set a location for every task. In the future, if the user happens to be around that location, a reminder will be sent to the user in the form of a notification. 

9) Support & Feedback
A simple feedback form will be displayed for the user to send their concerns and queries regarding the application. This feature will be accessible from the drawable menu. By providing support regarding the app, it helps not only them with their concerns, but also us as to how effectively the application was developed. 


## Project Status
We have completed 90% of the project implementation. We are polishing the UI and resolving bugs that have been discovered as a part of our testing.The status of the functionalities are listed below:

#### Minimum Functionality
- Feature 1: Create a task. and remove tasks in list (Completed)
- Feature 2: Update a task. (Completed)
- Feature 3: View a task. (Completed)
- Feature 4: Delete a task. (Completed)

#### Expected Functionality
- Feature 1: Ping to-do list to a particular location. (Completed)
- Feature 2: Notification when the user is near the location. (Completed)
- Feature 3: Adding subtask to main task. (Completed)

#### Bonus Functionality
- Feature 1: Add images to their to-do task. (Completed)
- Feature 2: Task visualization. (Completed)

## Sources
[1] Developers, G. (2018). App permissions best practices | Android Developers. Retrieved from https://developer.android.com/training/permissions/usage-notes<br/>
[2] Developers, G. Reduce the APK size | Android Developers. Retrieved from https://developer.android.com/topic/performance/reduce-apk-size <br/>
[3] Developers, G. Optimize for Doze and App Standby | Android Developers. Retrieved from https://developer.android.com/training/monitoring-device-state/doze-standby <br/>
[4] LocationManager  |  Android Developers. Retrieved from https://developer.android.com/reference/android/location/LocationManager.html#requestLocationUpdates(long,%20float,%20android.location.Criteria,%20android.app.PendingIntent) <br/>
[5] Location and context overview  |  Android Developers. Retrieved from https://developer.android.com/training/location/receive-location-updates <br/>
[6] Location strategies  |  Android Developers. (2018). Retrieved from https://developer.android.com/guide/topics/location/strategies#java <br/>
[7] Analyze power use with Battery Historian  |  Android Developers. Retrieved from https://developer.android.com/topic/performance/power/battery-historian <br/>
[8] Sensor  |  Android Developers. Retrieved from https://developer.android.com/reference/android/hardware/Sensor#TYPE_SIGNIFICANT_MOTION <br/>
