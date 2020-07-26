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

![](https://lh4.googleusercontent.com/wu2g-MOkjNcDwl1tHx04lvv8YySWx89VQ4cLzSvu7z4OvTWIGaFoiizfB0rl9G1CD31ANwdcY6389bPCZKzHBJodwKXsVl3m7kBMXQzw)
![](https://lh4.googleusercontent.com/asrLyQV5CaO03ae4rQoH2mOdnvxNQa-JXKKMHyUY0fLpf7GpvPMyohMg3yz46PLh6ZzIZkNqxCU1Euif9YSUYgzO3S06L53bERHRu0pF)
![](https://lh6.googleusercontent.com/bYNeYR-f5Yb12LQX-Vgl9mNcVCW61t7SWDMapcQvVTjUh1D3T5BwIzvnOwR6DZLLgoYyhSy-__U7HnTfVlwldJBJ9N8_aQdxPxGJabP_)
![](https://lh6.googleusercontent.com/-t47nAcXxWJemnBWIvJ3GqY3sq-BACJNkrWepvjTuQObxt7wud3W6rAbW4Xz1ZBgPYZR8-onMkXSWnQT8cSlrhyDeRDUByd44SDJ4xmP)
![]()https://lh3.googleusercontent.com/JPQXyuKv4agRAHrt04fbWttoIGNvcT4p__o5uYit9XHvkFzZbc_FOVqbQOnuA8SLZC0UsZPCa75SAOh3yv9fJEf3-QBlEd9R0PKseeLK

Users will be asked to answer some simple personality questions. Upon submitting their responses, the user will be redirected to the main page of the app.

The options chosen will be translated into a code(matchID), which will be tagged to each user. It will be used to compare with other users’ code tags(matchID).
The more similar the code tags, the higher the chance of being matched. This enables like-minded users to be matched.

The page is created using RecyclerView where there is a TextView of each question and its selected answer, as well as two answer Buttons to choose from. “Submit” is also a Button.

An error message toast will be shown if there are unanswered questions.


**Login Page**

![](https://lh6.googleusercontent.com/OKwlGv-9W4iHODMsKD28-1JE65zWSMoFf8DPNoRlNUc8vtQfJ8JkBQhrhSNNiaCGD0KgaQf-0AhPV4L15192AJC6RcE4aQFC1TuTp-qI)

Users will be directed to this page if they have selected the “I HAVE AN ACCOUNT” Button.
They are then required to enter either the username or email and password that corresponds to their account.

“Username/Email” is an EditText, “Password” uses EditText(Password). The login arrow is an ImageButton.

All information is checked against the database that was created using Firebase.

If the email or password entered is incorrect, a toast error message will be shown.


**Forget Password (Page 1)**

![](https://lh3.googleusercontent.com/2_8eUYqahM_6-BOYWKdq5hF2msyxRGBEXORctpJuWRe8Hr3D99huNmfzG_tAH88FB2pLUEeH7o05XUTgQo0OehzB4CRipImbDZF142FI)

The user will be asked to enter either their username or email.
If the username or email is valid in the database, the user will be directed to the next page using Intent.

The small “Let’s Meat Up” icon is an ImageView, “Username/Email” is an EditText (Plain Text), the “Next Page Button” and "Back Arrow Button" are ImageButtons.


**Forget Password (Page 2)**

![](https://lh5.googleusercontent.com/B9iNSEUwXo4sjzV-19on1RtVxwFrv70QI3LUSrFEq6qigpDxNXX28fj5WLkvAG6ifEnUpDPGgpRQxbpTvdkk12p1iTDuYsfiyAPg8kGZ)

The user will then be prompted to enter their new password twice.
If the passwords entered are identical, the change will be updated in the database and the user will be directed to the next page using Intent.

“Enter Password” and “Re-enter Password” are EditText(Password), the “Let’s Meat Up” icon is an ImageView, and the “Next Page Button” is an ImageButton.


**Forget Password (Page 3)**

![](https://lh4.googleusercontent.com/kFHhnH5ZmVdK3qVp8rTPgcy8rAyr2gXIfoYexzB6HeCmNl51b_oizT1kPskwYwn95n7Dk3jnIfIsrKJR0BoTQbVYXyZJo1dP-4cC9UW_)

This page will show up in the event of a successful password change in the database.
Users will then be allowed to redirect themselves to the Login Page, where they will be able to login to their account with the new password using Intent.

The “Let’s Meat Up” icon is an ImageView, “Change of password successful!” is a TextView, and the “Next Page Button” is an ImageButton.


**Home (Find new matches/Accept match requests Page)**

![](https://lh4.googleusercontent.com/jaLSwt6YGrOmSgeqJ4NPbSLTr7tK9XTWpfKPQhd0AJzHeJGXUmjclPl4T6N0v0ELhPg3GHZ0PzYt-RaYp1IwTIiuOJNXTm0J0U5K1y1R)

After the user successfully logs in to their account, they will be directed to this page where they can either find new people to match with or accept incoming match requests.

Depending on which option they select, the user will be sent to either the Random User page or the View User Requests page through the use of Intent.

“Pick User”, “Accept User”, “Profile Button” at the top left corner and the “Help" Button at the top right corner are all ImageButtons.


**Pick User (Find New Matches)**

The user will be directed to this page if they select the "Pick User" Button.

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


**View Reservations (User Account)**

If the user holds a user account and chooses to view reservations, they will be directed to this page.

Reservations will be displayed as a numbered list. Each reservation will show the matched user&#39;s username, restaurant name, meeting time and date.


