This healthcare application for Android devices helps users find and locate doctors of various speciality, laboratories where medical test can be carried out and pharmacies. Users can also find some health guide tips.

Software Design Decisions
The Android Studio IDE was used to create and test this app on real Android devices. While XML is used for the UI elements and layouts for the app's visual appeal, Java is the underlying source language for development. This application uses both Portrait and landscape orientations. 
This project was developed primarily in Android Studio using the build tool version 34.  The underlying language used for the development of the application was Java programming language which is a framework supported by Android studio. The SQLite Database was used to implement the storage of data. GitHub was used for source code management and version control. Trello software development tool was used to organize the software development process.

Login Activity:
On the first launch of the application, a splash screen is seen that shows the name and logo of the app, the user is then directed to the login screen containing only 2 text fields and a login button with their own descriptions. Here the users enter the username and password if they’re an existing user, else, they can find a button bellow to register as a new user.

Main Page Activity:
The users are directed to the Main Screen of the app after successfully logging into the app.
The main screen contains various cards which includes:
•	LAB TEST: This takes the user to an activity that contains different medical test that the user can find in a particular lab with the location given, the user can then select any of the preferred test they wish to perform with the price specified, the user can then add to a cart and checkout by selecting their preferred date and time they wish to perform the test. 

•	FIND A DOCTOR – The find a doctor card takes the user to an activity that shows different specialist doctors ( Dentist, cardiologist etc.), the user can then choose the type of doctor they hope to see, the user will also be able to see names of different doctors in the field, with their years of experience and the consultancy fee, the user can select one and book an appointment with them. 
•	FIND NEARBY HOSPITALS: This activity implements the Google Places API to access a user’s current location and search for nearby healthcare facilities by typing their required location in the search bar
•	BUY MEDICINE: This card allows a user to buy over the counter medicine from the comfort of their homes.
•	HEALTH GUIDE: This card contains fragments where users can fine healthcare tips and guide.
•	EXIT: The exit card will redirect the user back to the login page.



TECHNOLOGIES USED 
CATEGORY	TOOL
IDE	Android Studio
Emulator	Physical Android device
Version control	GitHub
Project management	Trello
API service	
Google search JSON API	
Build Management 	Gradle
Database	SQLite
Programming Language	Java
