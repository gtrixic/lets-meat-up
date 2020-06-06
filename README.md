*Welcome to Let’s Meat Up!*

**Team members:**

1.  Benjamin Lai Zhe Wei (S10193638)
    
2.  Cammy Lim Zhi Yu (S10196467)
    
3.  Cher Gek Teng (S10196753)
    
4.  Tan Jia Wen (S10196330)
    

  

**App Description:**

Make new friends or potential partners with this application with someone random and get recommendations on restaurants to have your first meal together.

  

**Roles & Contributions of Members:**

Benjamin (Group Leader)
    

1.  Made most application function ideas
    
2.  In-charge of user and restaurant database creation
    
3.  In-charge of CreateUser and CreateRestaurant Activities
    
4.  Implemented Yelp.API
    
 Cammy
    
1.  Created most of the app design using Figma
    
2.  In-charge of Login and ForgetPassword Activities and XMLs
    
3.  Assisted on other Figma-to-XML implementations
    

Gek Teng
    

1.  Assisted in app design using Figma
    
2.  In-charge of PersonalityQuestions Activity and XMLs
    
3.  In-charge of CreateUser and CreateRestaurant XMLs
    

Jia Wen
    

1.  Assisted in app design using Figma
    
2.  Created most of the base outlines for the functions
    

  

User Guide

  

![](https://lh3.googleusercontent.com/cs3d_j9_vHkd_Op_eJSdfyFaQwtEJCaNEaS6om0Dskt2eGPYlP47T0ZiCxaLjEX7Hed-zKIHwUpXIFsSm7JtvoMq5gl7qEsuYI7LnaG9yBjFPdpRzaQFDelRSGtL4ECQCgPB8cw)

  

*Welcome to Let’s Meat Up. This will be the User Guide for the application.*

*Font used in Application: Josefin Sans*

**Start Up (Main Page & Sign-up/Login Page)**

The app first opens the main page with a display of the logo for 5 seconds, before transitioning to the Sign-up or Login Selection page with the use Intent and Handler.postDelayed.

  

In the Sign-up or Login Selection page, users are able to choose if they wish to create an account or log in to their already made account using Intent.

  

“Let’s Get Started” is an ImageButton while “I Have An Account” is a Button.

  

![](https://lh3.googleusercontent.com/5gBeCUH-FM6xdYF3m9kyqIzOAaJ_DTghMVkEYLKwpxazGma8HmlB2kSiSRVfETPPH3GJsJ4XzLg3VEL9DLfwIrt6OKzIIBeEQTB4yyPFbTPWk46-h6H3pcCo--HwVpKSG78FmPg)![](https://lh5.googleusercontent.com/Fv0AKzwaunCeZ7QHPxvauDxfxPwMkVbcogNG83tJLn7c1vHWoi0eKBdWPB5r-1VoEfFZKQdhUk48KXjhXkIOVFTlrL46H8RRIJ3vAj7_QvxNUynbQXh24kRW51RmuO9HQQtCnD0)

  

**Sign-Up (Declare Account Type Page)**

If the user chooses the sign-up, they will be directed to this page where users are to declare whether they are signing up as a user or a restaurant account.

  

Both “Create User Account” and “Create Business Account” are ImageButtons.

  

Depending on which account they are signing up as, they will be sent to either the User Sign Up page or theBusiness Sign Up Page using Intent.

![](https://lh4.googleusercontent.com/PkkXR9sRMvHhd39wS6twJuPRK6lepog2jLXABwbTFFMoSrfmI71VbbH3tSCL2VvOLttg-GysjkE5kn-daTXTBz9rTpOwrnKUyyPlyeCKV1E2J1p3PXcp5TeyYb9lNohwSLxAZos)

  

**Sign-Up (Create User Page 1)**

If the user declares their account to be a user account, they will be sent to this Create User Page 1. Users are to enter their Full Name, Username, Password, Re-enter Password, Email, Gender, DOB, Sexual Preference.

  

After confirming their user information, users will be directed to the Create User Page 2 using Intent. If the user goes to the next page without entering all the necessary information, a Toast will appear to alert them.

  

“Full Name”, “Username” and “Sexual Preference” uses EditText (Plain Text), “Password” and “Re-enter Password” uses EditText (Password), “Email” uses EditText (Email), “Gender” uses spinner, “DOB” uses EditText (Date). The “Previous Page Button” and the “Next Page Button” are ImageButtons.

  

All the information entered is recorded into the UserAccounts database using SQLite.

  

![](https://lh3.googleusercontent.com/nbSRU0uFpDU7eym-CRJampAkD9Xlsbme4KWLEE2cvt71qv845fYRYYq5DMWM0gccGRCoFtDg0VzkfBFXxtQcwlkAhbjqWeXW9m9h7HG1bj40g6k7iXqzIDgNdI7M_kOwrl1hu3Y)

  

**Sign-Up (Create User Page 2)**

The user will then be asked to add a profile picture for their account. After adding a profile picture, they can press the Next Page Button to go to Create User Page 3.

  

The profile picture will be added to the UserAccount database using Firebase and SQLite.The “Previous Page Button” and the “Next Page Button” are ImageButtons.

  

![](https://lh4.googleusercontent.com/JVZ2HKtiFy0qzYcM5uCHW4bYSt-6lZ87GpNjGAnNnNDDuOxvMKp42FPivqboLjaDLfFahae7AomBE2mGIJe0K_e_MNMOHSF4xLqiGfN2Rog7iD8qHT07eEr-Q6bfTzjaD4lGtMw)

  

**Sign-Up (Create User Page 3)**

  

The user will then be asked to state their allergies/preferences and their diet (e.g. Halal, Vegetarian, etc.) After stating any information needed to be stated, they can confirm their profile and be redirected to the Personality Questions Page.

  

The “Previous Page Button” is an ImageButton, “Allergies/Preferences (If Any)” is a TextView, the box to enter their allergies/preferences and the “Diet” input are EditText (Plain Text), “Confirm Profile” is a Button.

![](https://lh6.googleusercontent.com/_NZ2m16xZBYXJ2V6Tt3JIT1A-vkD3TI5Puo_hpXaUwFyVVQ5KHwutlALb_ic_3WaIVM-XXSCE6op73tI7xM-pVcDH7pSPESEUNfE1M9k8t3pLuBEVRwJ1m0_ba_2BYhBXqCRhcM)

  

**Sign-Up (Personality Questions RecyclerView page)**

Users will be asked to answer some simple questions on their personality type. This will be used to match users with more like-minded people. After picking an answer for each question, the user can proceed to submit the simple questionnaire and be redirected to the main page of the app.

  

The options they choose will be translated into a code which will be tagged to each user and it will be used to compare with other users’ code tags. The more similar the code tags, the higher the chance of being matched.

  

The page is created using RecyclerView where each question has a TextView of the question and 2 Buttons to choose. “Submit” is a Button.

![](https://lh4.googleusercontent.com/wdfZ0s2pPpfsatu4xozsE6dqUMmak2GrrBSEFbh9UGYtqUI6VyXotr65q9gmW69G_mbeyIFK0hZRplNjWkMKr4oBJOIzd8cfGFB_nIvamGRU22-YEdkYZCYrHoTpcATOQAGzOeI)![](https://lh3.googleusercontent.com/egG3tSgJ7wOq4eZxhcnIp7EqfaC9KOKTckLq402kp_69t5ACYPYFk6cc03Zro22wCrewhc5KmdY5W6gMQXQm38LzPB-e7p1odmo-2bEX-qm-SOVvoV5rY1M-axPZjS_Y11fWqF4)

  

**Sign-Up (Create Restaurant Page 1)**

If the user declares their account to be a restaurant account, they will be sent to this Create Restaurant Page 1. Users are to enter their Restaurant Name, Address, Password, Re-enter Password and Email.

  

After confirming their restaurant information, users will be directed to the Create Restaurant Page 2 using Intent. If the user goes to the next page without entering all the necessary information, a Toast will appear to alert them.

  

“Restaurant Name” and “Address” uses EditText (Plain Text), “Password” and “Re-enter Password” uses EditText (Password), and “Email” uses EditText (Email). The “Previous Page Button” and the “Next Page Button” are ImageButtons.

  

All the information entered is recorded into the RestaurantAccounts database using SQLite.

![](https://lh5.googleusercontent.com/HsTV3cRZ7lATNUdzYud_DH1sv2lR9XKp6vswSKBcX4gPg8DvCqc-6tXw_Ig-iRVPbTysSGMXeiwvUgq_eV8Oud2eREkYCRDTTEKU5Tvq-gbCufqAS55SnHXuoatS6FmE82430bU)

  

**Sign-Up (Create Restaurant Page 2)**

The user will then be asked to add a profile picture for their account. After adding a profile picture, they can press the Next Page Button to go to Create User Page 3.

  

The profile picture will be added to the RestaurantAccount database using Firebase and SQLite. “Confirm Profile” is a Button.

  

**Login Page**

  
  

If the user clicks the “I HAVE AN ACCOUNT” Button, they will be directed to this page. They are requested to enter either their username or email, and their password corresponding to the account.

“Username/Email” is an EditText while “Password” uses EditText(Password). The arrow is an ImageButton.

All information is checked against the database that was created using SQLite.

  

![](https://lh3.googleusercontent.com/em5gm8IiYRIu1wT00lE0GnJMrfpo2ULGBXK_ok1xboQA9YkZiQ_bmzrnxwc8lhruIBFZWv7mH32-mw54Qw4L4VriTfybkfvMwnm13rtn09n6cW72OoakfWT3fsCqjNAT-UA8g08)

  

**Forget Password (Page 1)**

The user will be asked to enter either their username or email. If the username or email is valid in the database, they will be directed to the next page using Intent.

  

The small “Let’s Meat Up” icon is an ImageView, “Username/Email” is an EditText (Plain Text), the “Next Page Button” is an ImageButton.

![](https://lh3.googleusercontent.com/TlKitmYGSkcF7Od4jNE8YCUcEAnM0gSb9pQQuUI8eSX9mryvctGRF5xNDSbZEzGKVlhwnP-eRDfiabluDm1-bFx8o_tyBsfVor8JZYBkpzeBJ4SUDus9CARqYVAuwc45UwBG5Iw)

**Forget Password (Page 2)**

The user will then be asked to enter and re-enter their password. If the 2 passwords entered are identical, the change will be updated in the database and they will be directed to the next page using Intent.

  

“Enter Password” and “Re-enter Password” are EditText (Password”, the “Let’s Meat Up” icon is an ImageView, and the “Next Page Button” is an ImageButton.

  

![](https://lh6.googleusercontent.com/mdjRDLdwHRRb70cbYu5QVZePCVdiD-pseqVBJjpNc6bp3FRqqxyhQN5McpktWn8qhhZDEgGQ2rHkphQ0cy9Afm5MPOKxGPq6y76UVcvJnNUZN7Z3-F_KxQ6KykgWkuSdIUuntog)

**Forget Password (Page 3)**

This page will show up in the case of a successful change of password in the database. Users will then be allowed to redirect themselves to the Login Page where they are able to login to their account with their new password using Intent.

  

“Let’s Meat Up” icon is an ImageView, “Change of password successful!” is a TextView, and the “Next Page Button” is an ImageButton.

![](https://lh3.googleusercontent.com/1NI_qp0FlcM-ORyXzzpYZWkfN_0_2i_O_YIMPo4QxxEDh7hhFq_LWIE0qkQviVqtTSnw6tFpI9ufcmIQoWNArQYapHyMz-G9zsspFm5KR10NiiVP4WQfT7BdxGjWpqB8ldb3Nr0)
