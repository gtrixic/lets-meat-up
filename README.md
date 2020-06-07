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

The app first opens the main page with a display of the logo for 5 seconds, before transitioning to the Sign-up or Login Selection page with the use Intent and Handler.postDelayed.

  

In the Sign-up or Login Selection page, users are able to choose if they wish to create an account or log in to their already made account using Intent.

  

“Let’s Get Started” is an ImageButton while “I Have An Account” is a Button.

  

![](https://lh4.googleusercontent.com/M-YpQ6pQZpOXVlani3n2mvWKStG2wolOi36Y6qPvfaxywcX0VsAJf-xq34tlBrnE_DCRnc8bzMX2dVIsD_ysqJEorKn5-ouXyZyum0uC)
![](https://lh4.googleusercontent.com/x8mlm101elDoF3-7JRdZ3rJJMmJ9a13g1SFcIQ4R7dKTCWBWaDMnTRO8s7W13hHjI_NDCFF_dwPTTsd-XSntXT9bOZb0LepZSA7amyQt)
  

**Sign-Up (Declare Account Type Page)**

If the user chooses the sign-up, they will be directed to this page where users are to declare whether they are signing up as a user or a restaurant account.

  

Both “Create User Account” and “Create Business Account” are ImageButtons.

  

Depending on which account they are signing up as, they will be sent to either the User Sign Up page or theBusiness Sign Up Page using Intent.

![](https://lh5.googleusercontent.com/Db6e8Qu1p9uNKhYu_z14OdytSp1io-hh27CFKB1kFO7krDvVwwIiVWHf0OBOCExSbl-chdz8wazKYxDAMxCfZtCFKoEvvUOlLgG523ph)

  

**Sign-Up (Create User Page 1)**

If the user declares their account to be a user account, they will be sent to this Create User Page 1. Users are to enter their Full Name, Username, Password, Re-enter Password, Email, Gender, DOB, Sexual Preference.

  

After confirming their user information, users will be directed to the Create User Page 2 using Intent. If the user goes to the next page without entering all the necessary information, a Toast will appear to alert them.

  

“Full Name”, “Username” and “Sexual Preference” uses EditText (Plain Text), “Password” and “Re-enter Password” uses EditText (Password), “Email” uses EditText (Email), “Gender” uses spinner, “DOB” uses EditText (Date). The “Previous Page Button” and the “Next Page Button” are ImageButtons.

  

All the information entered is recorded into the UserAccounts database using SQLite.

  

![](https://lh3.googleusercontent.com/82Pf03xGYF2U7BbN26Yl7EGuyxusFNkHOcmRYZIJlR6RdKPkBSeD8-_zybXofK5HXPpLNefYCUkTZ6cbHbVTlNHAv_fOm_WZ4pcBC4jb)

  

**Sign-Up (Create User Page 2)**

The user will then be asked to add a profile picture for their account. After adding a profile picture, they can press the Next Page Button to go to Create User Page 3.

  

The profile picture will be added to the UserAccount database using Firebase and SQLite.The “Previous Page Button” and the “Next Page Button” are ImageButtons.

  

![](https://lh5.googleusercontent.com/xrysfV-tMqdsybkU-cGU71D0qasSHZlLjCrVmrOOoxvAWTMa2MPDdm0mV8K91GzJSsWoYSkFovlmSfsng141T3vzGQrEI2no-jzLS4ec)

  

**Sign-Up (Create User Page 3)**

  

The user will then be asked to state their allergies/preferences and their diet (e.g. Halal, Vegetarian, etc.) After stating any information needed to be stated, they can confirm their profile and be redirected to the Personality Questions Page.

  

The “Previous Page Button” is an ImageButton, “Allergies/Preferences (If Any)” is a TextView, the box to enter their allergies/preferences and the “Diet” input are EditText (Plain Text), “Confirm Profile” is a Button.

![](https://lh3.googleusercontent.com/C6-Oc4mLqfRSM9GsVR4DaZpTAV34BXFUybYeCMjLOwPQvBQiDXWDo66iza2aoD2l-yPmOp8Fb6juu2IsiSQFs6ZMEbHn2kdx47xmc7ah)

  

**Sign-Up (Personality Questions RecyclerView page)**

Users will be asked to answer some simple questions on their personality type. This will be used to match users with more like-minded people. After picking an answer for each question, the user can proceed to submit the simple questionnaire and be redirected to the main page of the app.

  

The options they choose will be translated into a code which will be tagged to each user and it will be used to compare with other users’ code tags. The more similar the code tags, the higher the chance of being matched.

  

The page is created using RecyclerView where each question has a TextView of the question and 2 Buttons to choose. “Submit” is a Button.

![](https://lh4.googleusercontent.com/SxMGOVulATYLpIIULr_Ibcx1ryio1TpSvmEu5B-gCFN_WIngooaOuEiLoP65pdEPyqbYnK-pf0gyRsd6Qzd8RkNCh9mruydx-tzAmnMU)
![](https://lh4.googleusercontent.com/ELVg7a1zCllFatlFxzmUtvbMwjEO2tSS8qv8Ti5cF0A2a69e3YbALPrB9reHy-x3JRENw3DE88r2fmzzrtmxs82ciHF3c8MFdvlRbUDp)
![](https://lh5.googleusercontent.com/t4X7d_1m3TbsielYqNfG7QLcs1DoYOgK2wyqPNPt8DpI4cFAIU6CC-9bzzE5bIK7_h5Hw_HkhRNshsP4HDFVzPsejCLHl8cAy3lJjWAs)
![](https://lh3.googleusercontent.com/v3R6WbQr8mE0zbXGTDV8JIl2kiJQ_-ANlNVdcArUhueNrgzaZbHJHVF3w-ziEB8EYlgkB6D1Ya5s--LiyJRtahu0ZWNYV2BuhROBcJQg)
![](https://lh5.googleusercontent.com/O18aoldOXTk3TRsNjf5bCIRgfayBw4gcCR8AzQfpoI_CurhGXrPj3fMY9RDMD1IJA11_i1GyzP3LrIf3-xTR_lzob-4M5usgz8Ox6SoR)
  

**Sign-Up (Create Restaurant Page 1)**

If the user declares their account to be a restaurant account, they will be sent to this Create Restaurant Page 1. Users are to enter their Restaurant Name, Address, Password, Re-enter Password and Email.

  

After confirming their restaurant information, users will be directed to the Create Restaurant Page 2 using Intent. If the user goes to the next page without entering all the necessary information, a Toast will appear to alert them.

  

“Restaurant Name” and “Address” uses EditText (Plain Text), “Password” and “Re-enter Password” uses EditText (Password), and “Email” uses EditText (Email). The “Previous Page Button” and the “Next Page Button” are ImageButtons.

  

All the information entered is recorded into the RestaurantAccounts database using SQLite.

![](https://lh3.googleusercontent.com/Z9zFPLbmhTqsNeHRnU20qI9fI710726dLiAOGiwlTDT_LeQUPXQmCUrUUDSKtH5OTIyUREmT4V-H05E1L8sZdN0OlmNFN2lDHqUMYOtt)

  

**Sign-Up (Create Restaurant Page 2)**

The user will then be asked to add a profile picture for their account. After adding a profile picture, they can press the Next Page Button to go to Create User Page 3.

  

The profile picture will be added to the RestaurantAccount database using Firebase and SQLite. “Confirm Profile” is a Button.

![](https://lh6.googleusercontent.com/fmHJvM0O8n4RLbl-ByRXjlDUnP5S9paae5WkoyAbg1GW-jzjH0AOml9h_LMrrtklA3FO_1nIjZKL4ou_cfWJPL5pC8dkvtUgP_NIAJEp)

  

**Login Page**

  
  

If the user clicks the “I HAVE AN ACCOUNT” Button, they will be directed to this page. They are requested to enter either their username or email, and their password corresponding to the account.

“Username/Email” is an EditText while “Password” uses EditText(Password). The arrow is an ImageButton.

All information is checked against the database that was created using SQLite.

  

![](https://lh4.googleusercontent.com/Nie7kOj66vjMzD8OBSuOhECfDfuOiIrRTE6XAxO9vVZmg3pJ5Eez9Ikg2Si4vTH9oUqV3BXjc7-8zBg1eXlq-RMX39GCaccQbnJhnN_u)



**Forget Password (Page 1)**

The user will be asked to enter either their username or email. If the username or email is valid in the database, they will be directed to the next page using Intent.

  

The small “Let’s Meat Up” icon is an ImageView, “Username/Email” is an EditText (Plain Text), the “Next Page Button” is an ImageButton.

![](https://lh4.googleusercontent.com/heFRL07Oj6Fnbwv6T7IIGlEkESVl6RmLhM8NUEAz9PzF7GDXk_U29b2p9jOPwu9qFSsLBwtV1uVp5uxe1ZTAEBNmNPhX8tqQSNdHHpVG)

**Forget Password (Page 2)**

The user will then be asked to enter and re-enter their password. If the 2 passwords entered are identical, the change will be updated in the database and they will be directed to the next page using Intent.

  

“Enter Password” and “Re-enter Password” are EditText (Password”, the “Let’s Meat Up” icon is an ImageView, and the “Next Page Button” is an ImageButton.

  

![](https://lh3.googleusercontent.com/kURxZ8fZ-w2XNOYtkbEoHxF_sJEM3w9Bs616vQhYLUL99LwZN6sab87sh5NJe_W1FSf4HC-jlXt_w5nTtljsi7n1GH_5CVYgC_5hFrl3)

**Forget Password (Page 3)**

This page will show up in the case of a successful change of password in the database. Users will then be allowed to redirect themselves to the Login Page where they are able to login to their account with their new password using Intent.

  

“Let’s Meat Up” icon is an ImageView, “Change of password successful!” is a TextView, and the “Next Page Button” is an ImageButton.

![](https://lh5.googleusercontent.com/ZpUddna-i1fgJNHNGG7IViZbk6zkE8ZNZDyzWF89U8V81X388lXnJ3-AnZ4DbuB9vslOhsj9Op1g9WIlUkYZA3BRCRAUnhaXl3l_vADB)

**Home (Find new matches/Accept match requests Page)**

After the user holding a user account successfully logs in to their account, they will be directed to this page where they can either find new people to match with or accept incoming match requests.

Depending on which option they select, the user will be sent to either the Random User page or the Incoming Requests page through the use of Intent.

“Pick User”, “Accept User”, “Profile Button” at the top left corner and “Help Button” at the top right corner are all ImageButtons.

![](https://lh3.googleusercontent.com/hE1ClkLa6VIvGM-hI-ZED_I-uBikSdYXbREVDYFVfDApb51sDd1Ff1QZ5_Coonn53QRVWA6tkJ6YCG9ehCQ15XnwbjtZ759UshN6svBD)

**Random User (Find New Matches)**

If the user holding a user account selects the &quot;Pick User&quot; Button, they will be directed to this page.

Upon pressing &quot;Start&quot;, a match for the user will be generated based on the users&#39; code tags. When a match is found, the name, age, gender and sexual preference of the matched user will be displayed. The user is then given the option to send a request or ignore the matched user.

If the user selects &quot;Request&quot;, a pop-up will appear to alert the user that the request has been sent. The pop-up also allows the user to choose if they want to return to the home screen or continue searching for more matches.

“Start”, “Request”, “Ignore”, “Profile Button” at the top left corner and “Help Button” at the top right corner are all ImageButtons.

![](https://lh4.googleusercontent.com/sFzv0KDS9eJNfca1TtTnb6GYQAQbxChyRfjLo-tRVU1nYcvf_GW8V2IycWWfkwB2lnFei0uzlEvBz3lQN8ao-p8BqXWNq5sHeH1_VPYj)
![](https://lh5.googleusercontent.com/W-AV_W13QzrATCg3KKQ8tia2s2zDKiRAL1M2Z_mwEH-1EF3yEiKPUiOHdOCU4C3Ho41fk7iVXjpD8in6E_KIX5-HN7keDFjMqlbOzOir)
![](https://lh6.googleusercontent.com/_ynAiVoasp62q2vPa9-YHPESzNxxW_t6OIUqzga9DjkKgKrzCcwGPentdTzdx0jWuV1c75Z2F7HkWISZ-_CEKMUVPcnrytAA2UWbQex2)
![](https://lh6.googleusercontent.com/1mGV8JHra3zGUzyMuaNyDBUCEEMMRk3CwDJyZXWiML92hqyTl6eYB0oPRcnSveLbPnU9K4vOvYSoL2MiPUQlI88DIuY2If8p-vRp56NP)

**Profile**

If the user selects the &quot;Profile&quot; button, they will be directed to this page.

On this page, the user can either view all their reservations or go to settings.

Both “View Reservations” and “Settings” are Buttons.

**View Reservations (User Account)**

If the user holds a user account and chooses to view reservations, they will be directed to this page.

Reservations will be displayed as a numbered list. Each reservation will show the matched user&#39;s username, restaurant name, meeting time and date.

**View Reservations (Restaurant Account)**

If the user holds a restaurant account and chooses to view reservations, they will be directed to this page.

Reservations will be displayed as a numbered list. Each reservation will show the matched users&#39; usernames, contact numbers, meeting time and date. Under each reservation is an option for the restaurant account holder to reject the reservation.

“Reject Reservation” is a Button.
