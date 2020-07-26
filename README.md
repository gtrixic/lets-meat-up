*Welcome to Let’s Meat Up!*

**Team members:**

1.  Benjamin Lai Zhe Wei (S10193638)
    
2.  Cammy Lim Zhi Yu (S10196467)
    
3.  Cher Gek Teng (S10196753)
    
4.  Tan Jia Wen (S10196330)
    

  

**App Description:**

Make new friends or potential partners with this application with someone random and get recommendations on restaurants to have your first meal together.

  

**Roles & Contributions of Members:**

Benjamin (Group Leader):
1.  Made most application function ideas
    
2.  In-charge of user and restaurant database creation
    
3.  In-charge of CreateUser and CreateRestaurant Activities
    
4.  Implemented Yelp.API
    
Cammy:
1.  Created most of the app design using Figma
    
2.  In-charge of Login and ForgetPassword Activities and XMLs
    
3.  Assisted on other Figma-to-XML implementations
    

Gek Teng:
1.  Assisted in app design using Figma
    
2.  In-charge of PersonalityQuestions Activity and XMLs
    
3.  In-charge of CreateUser and CreateRestaurant XMLs
    

Jia Wen:
1.  Assisted in app design using Figma
    
2.  Created most of the base outlines for the functions
    

  

**User Guide**

  

![](https://lh3.googleusercontent.com/cs3d_j9_vHkd_Op_eJSdfyFaQwtEJCaNEaS6om0Dskt2eGPYlP47T0ZiCxaLjEX7Hed-zKIHwUpXIFsSm7JtvoMq5gl7qEsuYI7LnaG9yBjFPdpRzaQFDelRSGtL4ECQCgPB8cw)

  

*Welcome to Let’s Meat Up. This will be the User Guide for the application.*

*Font used in Application: Josefin Sans*

**Start Up (Main Page & Sign-up/Login Page)**

![](https://lh3.googleusercontent.com/BoI1UfoVfktp-dAJEZJS0W8QxyxJBsJ4PZgNQXKBfrxIZ4wZIRVr9yIRyDBP_-NcMzznDwY3GNCsnDeTLGQkimaRcQLOiEt9n0QqEVKW)
![](https://lh6.googleusercontent.com/ppLCzODoyTrK08Q728HKbgu2EMgmxQlWnOY5aH5WUUp2Cs_okbpEGH2cTcuVc5iEuijEUCT136KZALwW4LVebDDvVvMSgXaq84q4-vHt)

The app first opens the main page with a display of the logo for 5 seconds, before transitioning to the Sign-up or Login Selection page with the use Intent and Handler.postDelayed.

In the Sign-up or Login Selection page, users are able to choose if they wish to create an account or log in to their already made account using Intent.

“Let’s Get Started” is an ImageButton while “I Have An Account” is a Button.


**Sign-Up (Create User Page 1)**

![](https://lh4.googleusercontent.com/Mx4rtIs3ZXF_jYzdMb4MzwCJ5767cbD6pMEE_PDrNRNulD8f4uun6nkQBEGJl8J84uhqAnae9oIs0iRxjKHmv0pH_pG74F7Z9n2ws3Bh)

If the user declares their account to be a user account, they will be sent to this Create User Page 1. Users are to enter their Full Name, Username, Password, Re-enter Password, Email, Gender, DOB.

After confirming their user information, users will be directed to the Create User Page 2 using Intent. If the user goes to the next page without entering all the necessary information, a Toast will appear to alert them.

“Full Name” and “Username” uses EditText (Plain Text), “Password” and “Re-enter Password” uses EditText (Password), “Email” uses EditText (Email), “Gender” uses spinner, “DOB” uses EditText (Date). The “Previous Page Button” and the “Next Page Button” are ImageButtons.

All the information entered is recorded into the UserAccounts database using Firebase.


**Sign-Up (Create User Page 2)**

![](https://lh6.googleusercontent.com/3boCj5cSXS6ma3zR0e5BROdROIbr4-p_cnfamH29Z7XBV7t-DrwWyn2zdjkxMjvML0ynJ1cHLzkncSxO9KViz8WDbs4dH4Jy9O70QYPU)

The user will then be asked to add a profile picture for their account. After adding a profile picture, they can press the Next Page Button to go to Create User Page 3.

The profile picture will be added to the UserAccount database using Firebase and SQLite.The “Previous Page Button” and the “Next Page Button” are ImageButtons.

**Sign-Up (Create User Page 3)**

![](https://lh3.googleusercontent.com/kO5mUdsMna1S_0Vm5InDnWpuj0Db7sstq0340aW7F9MEgzN7n3lxCT2yhP0jygl32zj9K0VW-p6roj7PzqshRpS0clbkoRgpp10V5ozL)

The user will then be asked to state their allergies/preferences and their diet (e.g. Halal, Vegetarian, etc.) After stating any information needed to be stated, they can confirm their profile and be redirected to the Personality Questions Page.

The “Previous Page Button” is an ImageButton, “Allergies/Preferences (If Any)” is a TextView, the box to enter their allergies/preferences and the “Diet” input are EditText (Plain Text), “Confirm Profile” is a Button.

**Sign-Up (Personality Questions RecyclerView page)**

Users will be asked to answer some simple questions on their personality type. This will be used to match users with more like-minded people. After picking an answer for each question, the user can proceed to submit the simple questionnaire and be redirected to the main page of the app.  

The options they choose will be translated into a code(matchID) which will be tagged to each user and it will be used to compare with other users’ code tags(matchID). The more similar the code tags, the higher the chance of being matched.

The page is created using RecyclerView where each question has a TextView of the questions and the selected answer, and 2 answer Buttons to choose from. “Submit” is a Button.

An error message toast will be shown if there are questions not answered.

![](https://lh4.googleusercontent.com/SxMGOVulATYLpIIULr_Ibcx1ryio1TpSvmEu5B-gCFN_WIngooaOuEiLoP65pdEPyqbYnK-pf0gyRsd6Qzd8RkNCh9mruydx-tzAmnMU)
![](https://lh4.googleusercontent.com/ELVg7a1zCllFatlFxzmUtvbMwjEO2tSS8qv8Ti5cF0A2a69e3YbALPrB9reHy-x3JRENw3DE88r2fmzzrtmxs82ciHF3c8MFdvlRbUDp)
![](https://lh5.googleusercontent.com/t4X7d_1m3TbsielYqNfG7QLcs1DoYOgK2wyqPNPt8DpI4cFAIU6CC-9bzzE5bIK7_h5Hw_HkhRNshsP4HDFVzPsejCLHl8cAy3lJjWAs)
![](https://lh3.googleusercontent.com/v3R6WbQr8mE0zbXGTDV8JIl2kiJQ_-ANlNVdcArUhueNrgzaZbHJHVF3w-ziEB8EYlgkB6D1Ya5s--LiyJRtahu0ZWNYV2BuhROBcJQg)
![](https://lh5.googleusercontent.com/O18aoldOXTk3TRsNjf5bCIRgfayBw4gcCR8AzQfpoI_CurhGXrPj3fMY9RDMD1IJA11_i1GyzP3LrIf3-xTR_lzob-4M5usgz8Ox6SoR)
  
  

**Login Page**


If the user clicks the “I HAVE AN ACCOUNT” Button, they will be directed to this page. They are requested to enter either their username or email, and their password corresponding to the account.

“Username/Email” is an EditText while “Password” uses EditText(Password). The login arrow is an ImageButton.

All information is checked against the database that was created using Firebase.

If the email or password entered is incorrect, a toast error message is shown.

![](https://lh4.googleusercontent.com/Nie7kOj66vjMzD8OBSuOhECfDfuOiIrRTE6XAxO9vVZmg3pJ5Eez9Ikg2Si4vTH9oUqV3BXjc7-8zBg1eXlq-RMX39GCaccQbnJhnN_u)



**Forget Password (Page 1)**

The user will be asked to enter either their username or email. If the username or email is valid in the database, they will be directed to the next page using Intent.

The small “Let’s Meat Up” icon is an ImageView, “Username/Email” is an EditText (Plain Text), the “Next Page Button” and "Back Arrow Button" is an ImageButton.

![](https://lh4.googleusercontent.com/heFRL07Oj6Fnbwv6T7IIGlEkESVl6RmLhM8NUEAz9PzF7GDXk_U29b2p9jOPwu9qFSsLBwtV1uVp5uxe1ZTAEBNmNPhX8tqQSNdHHpVG)


**Forget Password (Page 2)**

The user will then be asked to enter and re-enter their new password. If the 2 passwords entered are identical, the change will be updated in the database and they will be directed to the next page using Intent.

“Enter Password” and “Re-enter Password” are EditText (Password”, the “Let’s Meat Up” icon is an ImageView, and the “Next Page Button” is an ImageButton.

![](https://lh3.googleusercontent.com/kURxZ8fZ-w2XNOYtkbEoHxF_sJEM3w9Bs616vQhYLUL99LwZN6sab87sh5NJe_W1FSf4HC-jlXt_w5nTtljsi7n1GH_5CVYgC_5hFrl3)


**Forget Password (Page 3)**

This page will show up in the case of a successful change of password in the database. Users will then be allowed to redirect themselves to the Login Page where they are able to login to their account with their new password using Intent.

“Let’s Meat Up” icon is an ImageView, “Change of password successful!” is a TextView, and the “Next Page Button” is an ImageButton.

![](https://lh5.googleusercontent.com/ZpUddna-i1fgJNHNGG7IViZbk6zkE8ZNZDyzWF89U8V81X388lXnJ3-AnZ4DbuB9vslOhsj9Op1g9WIlUkYZA3BRCRAUnhaXl3l_vADB)


**Home (Find new matches/Accept match requests/Profile/Chat/Help Page)**

After the user holding a user account successfully logs in to their account, they will be directed to this page where they can either find new people to match with or accept incoming match requests.

Depending on which option they select, the user will be sent to either the Random User page or the View User Requests page through the use of Intent.

“Pick User”, “Accept User”, “Profile Button” at the top left corner, “Help Button” at the top right corner and "Chat Button" at the bottom right corner are all ImageButtons.

![](https://lh3.googleusercontent.com/hE1ClkLa6VIvGM-hI-ZED_I-uBikSdYXbREVDYFVfDApb51sDd1Ff1QZ5_Coonn53QRVWA6tkJ6YCG9ehCQ15XnwbjtZ759UshN6svBD)


**Pick User (Find New Matches)**

If the user holding a user account selects the &quot;Pick User&quot; Button, they will be directed to this page.

Upon pressing &quot;Start&quot;, a match for the user will be generated based on the users&#39; code tags. When a match is found, the profile picture, name, age, gender and allergies of the matched user will be displayed. The user is then given the option to send a request or ignore the matched user.

If the user selects &quot;Request&quot;, a pop-up will appear to alert the user that the request has been sent. The pop-up also allows the user to choose if they want to return to the home screen or continue searching for more matches.

“Start”, “Request”, “Ignore”, “Profile Button” at the top left corner and “Help Button” at the top right corner are all ImageButtons.

![](https://lh4.googleusercontent.com/sFzv0KDS9eJNfca1TtTnb6GYQAQbxChyRfjLo-tRVU1nYcvf_GW8V2IycWWfkwB2lnFei0uzlEvBz3lQN8ao-p8BqXWNq5sHeH1_VPYj)
![](https://lh5.googleusercontent.com/W-AV_W13QzrATCg3KKQ8tia2s2zDKiRAL1M2Z_mwEH-1EF3yEiKPUiOHdOCU4C3Ho41fk7iVXjpD8in6E_KIX5-HN7keDFjMqlbOzOir)
![](https://lh6.googleusercontent.com/_ynAiVoasp62q2vPa9-YHPESzNxxW_t6OIUqzga9DjkKgKrzCcwGPentdTzdx0jWuV1c75Z2F7HkWISZ-_CEKMUVPcnrytAA2UWbQex2)
![](https://lh6.googleusercontent.com/1mGV8JHra3zGUzyMuaNyDBUCEEMMRk3CwDJyZXWiML92hqyTl6eYB0oPRcnSveLbPnU9K4vOvYSoL2MiPUQlI88DIuY2If8p-vRp56NP)


**View Profile**

If the user selects the &quot;Profile&quot; button, they will be directed to this page.

On this page, the user can view their profile, where their Profile Picture, Username, Name, Age, Gender, Date of Birth, Allergies are shown.

The user can also edit their profile or log out of their account here. By pressing the buttons, they will be taken to the respective pages through the use of Intent.

Both “Edit” and “Sign Out” are Buttons.


**Edit Profile**

If the user selects the "Edit" button, they will be directed to this page.

On this page, the user can edit all their profile details except for email dietary requirements. 

The Username, Name and Allergy fields are EditText(Plain Text) while the Date Of Birth field is EditText(Date). The gender selection is a spinner and the Email field is a Text View. The Profile Picture is an ImageView while "Confirm" and back arrow are Buttons.


**Sign Out**

If the user selects the "Sign Out" button, they will be directed to the Login Page.


**Chat**

If the user selects the "Chat" button, they will be directed to this page.




**View Reservations (User Account)**

If the user holds a user account and chooses to view reservations, they will be directed to this page.

Reservations will be displayed as a numbered list. Each reservation will show the matched user&#39;s username, restaurant name, meeting time and date.


