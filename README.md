# Identification
#### To-do List Application <img align="right" src="/ProjectDocumentation/LogoDesignFiles/todo.png" alt="Logo Icon"  width="150" height="113" style="float: right; margin-right: 10px;" />

Group Members: 

- Jessica Castelino 	
- Navneet Prakash Singh B00810744	
- Shubham Narang
- Sharon Alva        B00813350 	
- Utsav Soni         B00812689
- Jismy Johnson      B00813344

Contact email: jessica.castelino@dal.ca

The repository contains the code as well as project related documents.  


# Project Summary
To-Do list in an application that provides an easy way in managing day-to-day tasks. It not only reminds the user of its pending tasks, but also lets you prioritize them. To-Do app is targeted specifically for students. It helps students to be more productive and manage their time efficiently. The students can make grocery lists, write notes, set reminders, capture pictures and save it. The app allows students to set reminders based on location and automatically reminds them when they are near the location. Apart from creating a list of tasks, students can sort tasks based on priority and date.Each task can be tagged to a location. This feature provides the user with location-based notification when user is near that location. Task progress of each task is shown based on list items pending. Additionally, we can give voice inputs to further save the time it takes to make these lists and plan the day. All in all, the app aids in distributing time and efforts wisely.


## Libraries
List of all the libraries used in the project

**v7 Support Libraries:** CardView package of this library was used for displaying tasks list. Source [here](https://developer.android.com/topic/libraries/support-library/packages)


## Problems Encountered

- Internal project updates 

During the initial development phase, there was a lot of confusion on the amount of work completed. Some team members sent updates by e-mail, others on WhatsApp and there was no single place where we could keep a record of what has been done and what is coming next. To resolve this issue, we made use of Slack app. All the communication regarding project updates were restricted to Slack to ensure that everyone was on the same page. 

- Adhering to coding conventions 

After the initial commits on GitHub, it was noticed that the code developed violated the Java coding conventions on various levels. As a team, we investigated the problem, invested our time to read the Java coding conventions defined on the Oracle website, and ensured that all rectify it without affecting the timelines.  

- Integration efforts 

Integration is crucial to ensure that all parts of the project fit together. Our milestones incorporated development of individual tasks, however, the integration efforts required were not considered. This affected our timelines.  

- Bugs 

Minor bugs in the code were detected during the implementation. This was then resolved by discussing within the group. Using this method helped in accomplishing the deliverables faster, as a different approach for a problem, solved the issue. 

- Differences in Gradle version 

Some team members found a difficulty to run the code pulled from GitHub after the initial commits were made. On investigating the problem, we realized that there was a mismatch in the Gradle versions used by every team member. As a result, the code updated to GitHub could not be executed and problems related to Gradle sync were encountered. We updated the Gradle to the same version on our machines to resolve this issue. 

- Change in design 

Initial wireframe was difficult to implement and due to our limited android knowledge, we couldn’t replicate the design completely.  

- Database restructuring 

The database was normalized till NF form. We had kept separate tables to store the individual tasks and the reminder details. The location-based reminder functionality, which runs as a background service, compares the user’s location with the location details stored in the database. This required a constant join operation which was inefficient and unreasonable. Hence, database restructuring was done.

- Reducing memory footprint: 

The location-based reminder functionality runs as a background service, constantly polling for the user’s GPS coordinates and comparing it with the destination location stored in the database. During the analysis phase, we realized that such a service can increase the memory footprint.

## Feature Section

- Add new task: 

The application allows users to add a new task such as “Grocery”. This task can have multiple sub-tasks such as “Milk, bread, cheese” added under Grocery. The task title and its sub-task elements are stored in the database. 

- View tasks on home screen: 

Every task created will be displayed on the home screen of the application. The order of display is as per the creation time. Instead of displaying all the subtasks at home screen, only the task title is displayed. This removes clutter on the home screen. 

- Checking / unchecking task: 

The application allows the user to check off the task is complete. Once the task is complete, a user may want to either check or uncheck the task. This gives the user an idea of how many tasks are completed. It helps the user to focus on other pending activities. 

- Update existing task: 

The application allows the user to update an existing task. As user freedom and control is of high importance, users can update an already created task. The app allows editing the task title, location, reminder date and the subtasks listed. This helps users in updating and changing as per their needs. 

- Delete task: 

The application allows the user to delete an existing task. Once the task is selected, an option to delete is available on the menu bar and user can delete the task. There are times when the task is of no value, too old to maintain, or an incorrect task detail. This feature allows users to delete unwanted tasks. 

- Camera feature: 

The application allows a user to upload To Do list as an image. It provides two options to get an image. First, a user can use his camera to click a picture of a list and the application will upload that in the database. Second, a user can upload an existing picture from gallery.   

- Location tagging: 

Location can be tagged against a task. User searches for the address or location and a list appear which is taken from Google API. Once the user selects the task, details are stored and reflected against the task that location. 

- Location-based reminder: 

The application gives a provision to the user to set a location for every task. In the future, if the user happens to be around that location, a reminder will be sent to the user in the form of a notification. 

- Support & Feedback: 

A simple feedback form will be displayed for the user to send their concerns and queries regarding the application. This feature will be accessible from the drawable menu. By providing support regarding the app, it helps not only them with their concerns, but also us as to how effectively the application was developed. 

## Project Status
We have completed around 30% of the project. We have implemented the backend functionalities and some of the UI implementation as well. Status of the functionalities are listed below.

#### Minimum Functionality
- Feature 1 Create, update and remove tasks in list (Partially Implemented)
- Feature 2 Task sorting (Partially Implemented)
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
