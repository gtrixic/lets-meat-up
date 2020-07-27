*Welcome to Let’s Meat Up!*

**Team members:**

1.  Benjamin Lai Zhe Wei (S10193638)
    
2.  Cammy Lim Zhi Yu (S10196467)
    
3.  Cher Gek Teng (S10196753)
    
4.  Tan Jia Wen (S10196330)
    

  

**App Description:**

*An assignment for partial fulfilment of the coursework of Mobile Applications Development AY2020/21 in Ngee Ann Polytechnic*

Love food? Let’s Meat Up!
Find food buddies, forge new friendships and dine at unique places with this easy-to-use food/social app. It’s the perfect platform for you to expand your social circle!

Swipe through profiles of like-minded individuals, view their interests and make your choices. Send a request, and if they accept your invitation, it’s a match!

Personality Questions Quiz:

Through your responses, we highlight your preferences and show you the profiles of like-minded individuals. This ensures that you will match with people that you can connect with.

Pick User:

Click Start, and be introduced with a new friend with only the basics of their profile!

Accept User:

Like Pick User, other users can pick you & send a request too! 

Chat Function:

Get to know each other and plan to meet up through our chat function! This enables you to focus only on the connections you’re interested in without unnecessary distractions.

Suggested Restaurants List:

Having trouble looking for a good place to eat at? Fret not - we have a solution! Get a random restaurant through the many restaurants we have in our application, and simply add your favourites to our Suggested Restaurants list shared between you and your partner. Fuss-free and efficient, this makes deciding on a place to eat much simpler.

**Roles & Contributions of Members:**

Benjamin (Group Leader):
1.  Made most application function ideas
    
2.  In-charge of user and restaurant database creation
    
3.  In-charge of Creating User Account, Chat Function
    
4.  Implemented Yelp.API
    
Cammy:
1.  Created most of the app design using Figma
    
2.  In-charge of Login & Forget Password, Pick User, Suggested Restaurant Function
    
3.  Assisted on other Figma-to-XML implementations

4.  Implemented Map.API
    

Gek Teng:
1.  Assisted in app design using Figma
    
2.  In-charge of Personality Questions, Accept User Requests Function, View User Request Profile
    
3.  In-charge of Figma-to-XML implementation
    

Jia Wen:
1.  Assisted in app design using Figma
    
2.  In-charge of User Profile & Edit Profile Function

3.  In-charge of Figma-to-XML implementation

4.  In-charge of App Description

  

**User Guide**

  

![](https://lh3.googleusercontent.com/cs3d_j9_vHkd_Op_eJSdfyFaQwtEJCaNEaS6om0Dskt2eGPYlP47T0ZiCxaLjEX7Hed-zKIHwUpXIFsSm7JtvoMq5gl7qEsuYI7LnaG9yBjFPdpRzaQFDelRSGtL4ECQCgPB8cw)

  

*Welcome to Let’s Meat Up. This will be the User Guide for the application.*

*Font used in Application: Josefin Sans*


**Start Up (Main Page & Sign-up/Login Page)**

The app first opens to the main page, which displays the logo for 5 seconds before transitioning to the Sign-up or Login Selection page with the use Intent and Handler.postDelayed.

In the Sign-up or Login Selection page, users can choose between creating a new account or logging in to an existing account using Intent.

“Let’s Get Started” is an ImageButton, while the “I Have An Account” is a Button.

![](https://i.postimg.cc/nzKhDw9c/Screenshot-20200727-172822-com-example-letsmeatup.jpg)
![](https://i.postimg.cc/rpFJbvc7/1.jpg)


**Sign-Up (Create User Page 1)**

Users will be directed to this page, Create User Page 1, if they had chosen to create a new account.
They are then prompted to enter their Full Name, Username, Password, Re-enter Password, Email, Gender, and DOB.

After confirming their information, users will be directed to Create User Page 2 using Intent. If the user tries to go to the next page without entering all the necessary information, a Toast will be shown to alert them.

All the information entered is recorded into the UserAccounts database using Firebase.

“Full Name”, “Username” and “DOB” uses EditText (Plain Text), “Password” and “Re-enter Password” uses EditText (Password), “Email” uses EditText (Email) and “Gender” uses spinner. The “Previous Page Button” and the “Next Page Button” are ImageButtons.

![](https://i.postimg.cc/LsCTMN85/2.jpg)
![](https://i.postimg.cc/JnsQ7Zsq/3.jpg)


**Sign-Up (Create User Page 2)**

In this page, the user can choose to add a profile picture for their account. They can press the “Next Page” Button to go to Create User Page 3 after doing so.

The profile picture will be added to the UserAccount database using Firebase.

The “Previous Page Button” and the “Next Page Button” are ImageButtons.

![](https://i.postimg.cc/BnJcLd8t/4.jpg)
![](https://i.postimg.cc/Hn54Xm48/5.jpg)


**Sign-Up (Create User Page 3)**

Upon being directed to this page, the user will be prompted to state their allergies/preferences and their diet (e.g. Halal, Vegetarian, etc.).

After stating any information needed to be stated, they can confirm their profile and be redirected to the Personality Questions Page.

The “Previous Page Button” is an ImageButton, “Allergies/Preferences (If Any)” is a TextView, the box to enter their allergies/preferences and the “Diet” input are EditText (Plain Text), “Confirm Profile” is a Button.

![](https://i.postimg.cc/J4VQmyMN/6.jpg)
![](https://i.postimg.cc/vBCt4T9d/7.jpg)


**Sign-Up (Personality Questions RecyclerView page)**

Users will be asked to answer some simple personality questions. Upon submitting their responses, the user will be redirected to the main page of the app.

The options chosen will be translated into a code (matchID), which will be tagged to each user. It will be used to compare with other users’ code tags (matchID). The more similar the code tags, the higher the chance of being matched.

This enables the matching of like-minded users.

An error message toast will be shown if there are unanswered questions.

The page is created using RecyclerView where there is a TextView of each question and its selected answer, as well as two answer Buttons to choose from. “Submit” is also a Button.

![](https://i.postimg.cc/fTcfbwR6/8.jpg)
![](https://i.postimg.cc/Kcg5d7Sc/9.jpg)
![](https://i.postimg.cc/wjF5h0J2/10.jpg)
![](https://i.postimg.cc/1tVp1Sd7/11.jpg)
![](https://i.postimg.cc/3NM2K658/12.jpg)
  

**Login Page**

Users will be directed to this page if they have selected the “I HAVE AN ACCOUNT” Button.
They are required to enter either the username or email and password that corresponds to their account.

All information is checked against the database that was created using Firebase.

If the email or password entered is incorrect, a toast error message will be shown.

“Username/Email” is an EditText, “Password” uses EditText(Password). The login arrow is an ImageButton.

![](https://i.postimg.cc/Bv6TNHB0/13.jpg)
![](https://i.postimg.cc/Wbv0CQwS/14.jpg)

**Forget Password (Page 1)**

The user will be asked to enter either their email. If the email is valid in the database, they will be directed to the next page using Intent.

The small “Let’s Meat Up” icon is an ImageView, “Email” is an EditText (Plain Text), the “Next Page Button” and "Back Arrow Button" is an ImageButton.

![](https://i.postimg.cc/Gp5kftXs/15.jpg)
![](https://i.postimg.cc/d38rDBw8/16.jpg)

**Forget Password (Page 2)**

After proceeding to this page, the user will be prompted to enter their new password twice.
If the passwords entered are identical, the change will be updated in the database and the user will be directed to the next page using Intent.

“Enter Password” and “Re-enter Password” are EditText(Password), the “Let’s Meat Up” icon is an ImageView, the “Back” button is an ImageView, and the “Next Page” button is an ImageButton.

![](https://i.postimg.cc/BZDxhqHX/17.jpg)


**Forget Password (Page 3)**

This page will show up in the event of a successful password change in the database.
Users will be allowed to redirect themselves to the Login Page, where they will be able to login to their account with the new password using Intent.

The “Let’s Meat Up” icon is an ImageView, “Change of password successful!” is a TextView, and the “Next Page Button” is an ImageButton.

![](https://i.postimg.cc/YC2YqYVW/18.jpg)


**Home (Find new matches/Accept match requests/Profile/Chat Page)**

After the user successfully logs in to their account, they will be directed to this page where they can find new people to match with or accept incoming match requests.

Depending on the option they select, the user will either be sent to the Pick User page or the Incoming Requests page through the use of Intent.

The “Pick User”, “Accept User”and “Profile” Buttons at the top left corner are all ImageButtons.

![](https://i.postimg.cc/YCXg4jKy/19.jpg)


**Pick User (Find New Matches)**

The user will be directed here If they select the “Pick User” Button.

Upon pressing “Start”, a match for the user will be generated based on the users’ code tags (matchID). When a match is found, the name, age, gender of the matched user will be displayed. The user is then given the option to send a request or ignore the matched user.

If the user selects “Request”, a pop-up will appear to alert the user that the request has been sent. The pop-up also allows the user to choose if they want to return to the home screen or continue searching for more matches.

The “Start”, “Request”, “Ignore” and “Profile” Buttons at the top left corner are all ImageButtons.

![](https://i.postimg.cc/tgY6Fy9R/20.jpg)
![](https://i.postimg.cc/R0yHR807/21.jpg)
![](https://i.postimg.cc/9M9qNp4v/22.jpg)
![](https://i.postimg.cc/qqX6pvxw/23.jpg)
![](https://i.postimg.cc/j2rJV7PC/24.jpg)


**View Profile**

If the user selects the “Profile” button, they will be directed to this page.

On this page, the user can view their own profile, which consists of their Profile Picture, Username, Name, Age, Gender, Date of Birth and Allergies.

The user can edit their profile information through clicking the “Edit” Button, which will direct them to the Edit Profile Page via Intent.

The user can also log out of their account by clicking the Logout button in the top right corner, which will direct them back to the login page via Intent.

“Edit” is a Button, while the Logout button is an ImageView.

![](https://i.postimg.cc/44z96WRc/25.jpg)
![](https://i.postimg.cc/P5qvkhpb/26.jpg)
![](https://i.postimg.cc/gksL5rJk/27.jpg)

**Edit Profile**

If the user selects the "Edit" button, they will be directed to this page.

Apart from their email and dietary requirements, the user can edit the rest of their profile details on this page.

The Username, Name and Allergy fields are EditText (Plain Text), while the Date Of Birth field is EditText (Date). The gender selection is a spinner, the Email field is a Text View, the Profile Picture is an ImageView, "Confirm" is a Button, and the back arrow is an ImageView.

![](https://i.postimg.cc/yNgJkPTk/28.jpg)


**View User Requests**

If the user selects the "Accept User" ImageButton, they will be directed to this page.

This is where users can accept user requests to start to chat. Users can find out more about the other user by pressing on the request. This will direct the user to the View User Request Profile page by Intent.

The Tick Button is an ImageButton that will confirm the user request and the Cross Button is also an ImageButton that declines the user request.

This page is created using RecyclerView where the ProfilePicture is an ImageView while the Username is a TextView.

![](https://i.postimg.cc/gJb0rPJT/42.jpg)
![](https://i.postimg.cc/gkm6N3GT/29.jpg)

**View User Request Profile**

If the user selects a certain user request, they will be directed to this page.

This is where users can see the information of the user that sent the request.

The Profile Picture is an ImageView while the other fields are TextViews. The back arrow is an ImageButton.

![](https://i.postimg.cc/qvZhJYyP/30.jpg)

**View All Chats**

This page shows the chats of users that has accepted their request or users that they have accepted. The user can then select a user to begin/continue chatting with.

If the user selects the "Chat" button, they will be directed to this page. If the user confirms a user request, the two users will be able to use this function.

This page is created using RecyclerView where the Profile Picture is an ImageView while the Username, Last Message and Time are TextViews.

![](https://i.postimg.cc/SRqj1pbM/31.jpg)
![](https://i.postimg.cc/vmJ49VPX/32.jpg)
![](https://i.postimg.cc/ZR6CL52m/33.jpg)


**Chat**

Users will be directed to this page when the user selects a chat.

This page will show messages of the 2 users via Firebase. Users will enter a message in the EditText box and clicked send, the Message recyclerView will update Real-Time. Their message will pop up in a pink message box while their partner's messages will pop up in a grey message box. Their messages will appear with a Time that follows the time the message is sent.

The user Name is a Textview, backArrow, userProfile, sendButton are ImageButtons, the message is a RecyclerView with ImageView to surround the message and the message and time are TextViews.

![](https://i.postimg.cc/xT6q57xX/34.jpg)
![](https://i.postimg.cc/7PM5GbSM/35.jpg)
![](https://i.postimg.cc/ZKbCrZck/36.jpg)


**Chat Profile Page**

If the user selects the Profile Button on the top right hand corner of the chat page, they will be directed to this page.

This page shows the profile details of the other user that the user is chatting with. There is also a Suggested Restaurants list feature, where both users can add to it by pressing the Add Button. A random restaurant will be shown to the user, and the user can choose to add it to the list, or reject it.

After adding a restaurant to the Suggested Restaurant list, the user can view the restaurant address by pressing the restaurant name in the list. To get directions to the restaurant, the user can press the Get Directions Button and Google Maps will open with the location.

The Username, Name, Age, Gender, Date of Birth, Allergies and Diet are all TextViews while the Profile Picture is an ImageView. The Suggested Restaurant list is a RecyclerView with Textviews. The back arrow is an ImageButton where Add is a Button w/ Background.

![](https://i.postimg.cc/Jzg0VXWm/37.jpg)
![](https://i.postimg.cc/dVD3xBp4/38.jpg)
![](https://i.postimg.cc/ZqdRTqTN/39.jpg)
![](https://i.postimg.cc/1XJRWnKk/40.jpg)
![](https://i.postimg.cc/QCXxkkcg/41.jpg)


