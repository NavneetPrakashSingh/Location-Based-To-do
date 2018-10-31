# Identification
#### To-do List Application <img src="/ProjectDocumentation/LogoDesignFiles/todo.png" alt="Logo Icon" style="float: right; margin-right: 10px;" />

Group Members:
| Name  	|Banner ID   	|
|---	|---	|
|  Jessica Castelino 	|    	|
|   Navneet Prakash Singh 	|   	|
| Shubham Narang  	|   	|
| Sharon Alva  	|  B00813350 	|
|   Utsav Soni   	|  B00812689	|
| Jismy Johnson| B00813344|

Contact email: jessica.castelino@dal.ca

The repository contains the code as well as project related documents.  


# Project Summary
To-Do list in an application that provides an easy way in managing day-to-day tasks. It not only reminds the user of its pending tasks, but also lets you prioritize them. To-Do app is targeted specifically for students. It helps students to be more productive and manage their time efficiently. The students can make grocery lists, write notes, set reminders, capture pictures and save it. The app allows students to set reminders based on location and automatically reminds them when they are near the location. Apart from creating a list of tasks, students can sort tasks based on priority and date.Each task can be tagged to a location. This feature provides the user with location-based notification when user is near that location. Task progress of each task is shown based on list items pending. Additionally, we can give voice inputs to further save the time it takes to make these lists and plan the day. All in all, the app aids in distributing time and efforts wisely.


## Libraries
Provide a list of **ALL** the libraries you used for your project. Example:

**v7 Support Libraries:** CardView package of this library was used for displaying tasks list. Source [here](https://developer.android.com/topic/libraries/support-library/packages)


## Problems Encountered

Initially updated project structure was not as per the Coding standards. This created trouble in merging all future code blocks.

```
Code snippet goes here
```

## Feature Section

User can create, update and remove task from the to-do list. A view group, name, ListView will be used to achieve this. The user will have the ability to sort task according to date and priority. The priority will have three levels that indicate their level of importance and moreover, are easily understandable by their term: High, Medium, and Low. Support form, that will connect the user to the customer assistance

The user will have an alert in the notification panel related to the pending task which will help the user to keep the track of their remaining goals. For this we will be using _NotificationCompat APIs_ of Android support library. Using Google API and location services, we will achieve to location-based adding of a to-do list, where a user can tag a location to their task and can get notified upon being near to that location. We will be using _android.location_ package to provide our application access to location services. For map, we will be using MapView class of Google Maps Android API to display it. 

Visualization for task’s progress i.e. showing how many pending to-do list items are left in the form of a progress bar. Providing images support to the app, where a user will be using camera feature, to add to do list. For this, we will be using _android.hardware.camera2 API_ that is supported by Android Framework.

List all the main features of your application with a brief description of each feature.

## Project Status
We have completed around 30% of the project. We have implemented the backend functionalities and some of the UI implementation as well. Status of the functionalities are listed below.

#### Minimum Functionality
- Feature 1 Create, update and remove tasks in list (Completed)
- Feature 2 Task sorting (Not Implemented)
- Feature 3 Support form (Not Implemented)

#### Expected Functionality
- Feature 1 Notification Alert (Completed)
- Feature 2 Location tagging for task (Completed)

#### Bonus Functionality
- Feature 1 Task progress visualization (Not Implemented)
- Feature 2 Image addition in list (Not Implemented)

## Sources
[1] Developers, G. (2018). App permissions best practices | Android Developers. Retrieved from https://developer.android.com/training/permissions/usage-notes<br/>
[2] Developers, G. Reduce the APK size | Android Developers. Retrieved from https://developer.android.com/topic/performance/reduce-apk-size <br/>
[3] Developers, G. Optimize for Doze and App Standby | Android Developers. Retrieved from https://developer.android.com/training/monitoring-device-state/doze-standby <br/>
[4] LocationManager  |  Android Developers. Retrieved from https://developer.android.com/reference/android/location/LocationManager.html#requestLocationUpdates(long,%20float,%20android.location.Criteria,%20android.app.PendingIntent) <br/>
[5] Location and context overview  |  Android Developers. Retrieved from https://developer.android.com/training/location/receive-location-updates <br/>
[6] Location strategies  |  Android Developers. (2018). Retrieved from https://developer.android.com/guide/topics/location/strategies#java <br/>
[7] Analyze power use with Battery Historian  |  Android Developers. Retrieved from https://developer.android.com/topic/performance/power/battery-historian <br/>
[8] Sensor  |  Android Developers. Retrieved from https://developer.android.com/reference/android/hardware/Sensor#TYPE_SIGNIFICANT_MOTION <br/>
