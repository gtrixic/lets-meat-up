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
    
2.  In-charge of Personality Questions, Accept User Requests Function
    
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

![](https://lh3.googleusercontent.com/BoI1UfoVfktp-dAJEZJS0W8QxyxJBsJ4PZgNQXKBfrxIZ4wZIRVr9yIRyDBP_-NcMzznDwY3GNCsnDeTLGQkimaRcQLOiEt9n0QqEVKW)
![](https://lh6.googleusercontent.com/ppLCzODoyTrK08Q728HKbgu2EMgmxQlWnOY5aH5WUUp2Cs_okbpEGH2cTcuVc5iEuijEUCT136KZALwW4LVebDDvVvMSgXaq84q4-vHt)


**Sign-Up (Create User Page 1)**

Users will be directed to this page, Create User Page 1, if they had chosen to create a new account.
They are then prompted to enter their Full Name, Username, Password, Re-enter Password, Email, Gender, and DOB.

After confirming their information, users will be directed to Create User Page 2 using Intent. If the user tries to go to the next page without entering all the necessary information, a Toast will be shown to alert them.

All the information entered is recorded into the UserAccounts database using Firebase.

“Full Name” and “Username” uses EditText (Plain Text), “Password” and “Re-enter Password” uses EditText (Password), “Email” uses EditText (Email), “Gender” uses spinner, and “DOB” uses EditText (Date). The “Previous Page Button” and the “Next Page Button” are ImageButtons.

![](https://lh6.googleusercontent.com/lohr8Cvh6WkXekMqEed_UIBfmaPyKfBTKpRNEZa3Lmd2ermr49q7sLLwAOBSyRsfDTlLlgdJzQ39u3rAsusVygei1syfEuAW2iICdAaR)


**Sign-Up (Create User Page 2)**

In this page, the user can choose to add a profile picture for their account. They can press the “Next Page” Button to go to Create User Page 3 after doing so.

The profile picture will be added to the UserAccount database using Firebase.

The “Previous Page Button” and the “Next Page Button” are ImageButtons.

![](https://lh6.googleusercontent.com/3boCj5cSXS6ma3zR0e5BROdROIbr4-p_cnfamH29Z7XBV7t-DrwWyn2zdjkxMjvML0ynJ1cHLzkncSxO9KViz8WDbs4dH4Jy9O70QYPU)


**Sign-Up (Create User Page 3)**

Upon being directed to this page, the user will be prompted to state their allergies/preferences and their diet (e.g. Halal, Vegetarian, etc.).

After stating any information needed to be stated, they can confirm their profile and be redirected to the Personality Questions Page.

The “Previous Page Button” is an ImageButton, “Allergies/Preferences (If Any)” is a TextView, the box to enter their allergies/preferences and the “Diet” input are EditText (Plain Text), “Confirm Profile” is a Button.

![](https://lh4.googleusercontent.com/JcduxD01c7j-4e1jymRHJ4LdBJe_FD1p27oEH3QNwyXYdIBw_4yQn7C5OCYG06jKA3o0YIy_yTVB_sd0cRYvOdRSpOgTEGLZLWLu65kd)


**Sign-Up (Personality Questions RecyclerView page)**

Users will be asked to answer some simple personality questions. Upon submitting their responses, the user will be redirected to the main page of the app.

The options chosen will be translated into a code (matchID), which will be tagged to each user. It will be used to compare with other users’ code tags (matchID). The more similar the code tags, the higher the chance of being matched.

This enables the matching of like-minded users.

An error message toast will be shown if there are unanswered questions.

The page is created using RecyclerView where there is a TextView of each question and its selected answer, as well as two answer Buttons to choose from. “Submit” is also a Button.

![](https://lh3.googleusercontent.com/cxoIkMyH9Ck7dZafVZZiCE4njqht4MXS3fdFXu4m0aORo5H3-KrgEVPZtCHsYxjWEj4CxG2mkaoBTsGKCfLmXJZidhYk-Ko0OWLhw_kF)
![](https://lh6.googleusercontent.com/zdttSbF3CoSx9-Ywu9fr71JO6kSz_Zn8UdoOR8pn1aatbVXHUipszVDzuGNKEG15BsSXt9q-ThkSKdXswNv53LLI2H6zkRiEe6IZmfsP)
![](https://lh4.googleusercontent.com/PzLrjGmaVbFrN8xrM_qChsPjzJY3mwp7CxkfW10iD-Mdq4uJVMl4sQyIxK4awGWehLTIBA57QMqSLwmntL3zQxsYhrkf2Q7mF8LjXNx2)
![](https://lh5.googleusercontent.com/dwXPBrGWUQYyv27Wxnzkf5CVGXHD27f62IuiD8HM9gJMEN72Tv4CpxBOF3iIxD4kM6dXLAFRA8zB1S_cPzfi7l1jVXrQ9vHTg2uMfBLn)
![](https://lh5.googleusercontent.com/yM8o6ANfHchQCduoFJTb-QpiZtxFD7t0n83N92uyQHJqjV9GFH3q0jyEUDYqOte_ZuktMSse3-p9__fZe3wEV6M4G4TIJ70BXgyQaDrL)
  

**Login Page**

Users will be directed to this page if they have selected the “I HAVE AN ACCOUNT” Button.
They are required to enter either the username or email and password that corresponds to their account.

All information is checked against the database that was created using Firebase.

If the email or password entered is incorrect, a toast error message will be shown.

“Username/Email” is an EditText, “Password” uses EditText(Password). The login arrow is an ImageButton.

![](https://lh3.googleusercontent.com/UQWtpp-IxtEpFSDNFdAeRg15A4J4e8fO5sS06XJIj2eiERI0nJtiJp8p9SIlzu2upMEBXnsirWYSE5TqdiuNmxLvki7zQnZiTOJ4fuOR)


**Forget Password (Page 1)**

The user will be asked to enter either their email. If the email is valid in the database, they will be directed to the next page using Intent.

The small “Let’s Meat Up” icon is an ImageView, “Email” is an EditText (Plain Text), the “Next Page Button” and "Back Arrow Button" is an ImageButton.

![](https://lh3.googleusercontent.com/NoBrs3iitg6hdTFRwyG3_O9tnhS8RX4jRhJyvDzi2vKl1g3FZ2fV1LMZ0dYNch6oMhZMQtRSO0PsM510I8ZeGHtNwRDTd0OasGGLjrs)


**Forget Password (Page 2)**

After proceeding to this page, the user will be prompted to enter their new password twice.
If the passwords entered are identical, the change will be updated in the database and the user will be directed to the next page using Intent.

“Enter Password” and “Re-enter Password” are EditText(Password), the “Let’s Meat Up” icon is an ImageView, the “Back” button is an ImageView, and the “Next Page” button is an ImageButton.

![](https://lh3.googleusercontent.com/yXOa33oB2EhMfzlN-uVLXR60GtOwsMmdRdNck9SmUuzcN01kXfui45Hz5o4FQEiOzhO4EL93DSA1636IypU04S-fytD65tWtO-6C_tqN)


**Forget Password (Page 3)**

This page will show up in the event of a successful password change in the database.
Users will be allowed to redirect themselves to the Login Page, where they will be able to login to their account with the new password using Intent.

The “Let’s Meat Up” icon is an ImageView, “Change of password successful!” is a TextView, and the “Next Page Button” is an ImageButton.

![](https://lh5.googleusercontent.com/5bAO6Sap9X-YIKIfJ7mYLvE6s62y15QHpS9GMVlc4t47OH2G2Qv6soWoQazy3oyygUwGU2-xenirF_XIdw8tnwm3A-ryAFiSPzTFtgfW)


**Home (Find new matches/Accept match requests/Profile/Chat Page)**

After the user successfully logs in to their account, they will be directed to this page where they can find new people to match with or accept incoming match requests.

Depending on the option they select, the user will either be sent to the Pick User page or the Incoming Requests page through the use of Intent.

The “Pick User”, “Accept User”and “Profile” Buttons at the top left corner are all ImageButtons.

![](https://lh6.googleusercontent.com/LwXQKIhFdwNgg4fZ3TZA8RPX6tlMeedHm5IxqB0QaZVjoDKGsDyS40oXOGWzOMPvx2je0INn1MLZzRTUCnsC1I-on5c8h3ywGhT9oUvh)


**Pick User (Find New Matches)**

The user will be directed here If they select the “Pick User” Button.

Upon pressing “Start”, a match for the user will be generated based on the users’ code tags (matchID). When a match is found, the name, age, gender of the matched user will be displayed. The user is then given the option to send a request or ignore the matched user.

If the user selects “Request”, a pop-up will appear to alert the user that the request has been sent. The pop-up also allows the user to choose if they want to return to the home screen or continue searching for more matches.

The “Start”, “Request”, “Ignore” and “Profile” Buttons at the top left corner are all ImageButtons.

![](https://lh3.googleusercontent.com/rPPBxy-AH700SC_cOiDB9llhpBDxTqg1y9OD0ciGjI5r7u6E3x1kO_ezO0gpbvMsyCHkpGQ4Nj0dxzm3wk1ZcRHsKKnwRmp_A815pbw-)
![](https://lh4.googleusercontent.com/5sxWMNeEqcRpYIwwOd2N9lCuVL3TLfSXaA3whNo17_KZSb6ZmAkXszqkDOLBsbcmv8omUDD16vzhqKjasiEMXUTBCtCIVVqu-DsJ48EQ)
![](https://lh6.googleusercontent.com/3q_L64X5SJsBkTqK5X8IGwAPcec9-0PANTY3WobVoP7dbk-4v76WJchcr-tiKGdY_WGMiKtFy9x2KBtV_u_n0mUwT28uhGlZa5gXe1PS)
![](https://lh6.googleusercontent.com/MSpMScoMjSEk8vZVQKj8NxY7ALHU_TPfb3snRfmHA7Bhj8s4s4X9M2NVbzP80RNV4Vt4cghuqNfcuM8LcbYFxpThFFq3vjQ6D9o9viin)
![](https://lh3.googleusercontent.com/-DEOqLaBv0VNa2L4p2XNt5AlIBJLWTRZM53IxMpQTv2OD96HZ8AAJ4SkxskjysB4NCMQAkyOa1PRuHnVINSMf-qyNi-wHnMFOaecfyR6)


**View Profile**

If the user selects the “Profile” button, they will be directed to this page.

On this page, the user can view their own profile, which consists of their Profile Picture, Username, Name, Age, Gender, Date of Birth and Allergies.

The user can edit their profile information through clicking the “Edit” Button, which will direct them to the Edit Profile Page via Intent.

The user can also log out of their account by clicking the Logout button in the top right corner, which will direct them back to the login page via Intent.

“Edit” is a Button, while the Logout button is an ImageView.

![](https://lh5.googleusercontent.com/nMwE-OO34BtWMk7OQLsnWFasYcBQoeAaD3pXWqjjdPgZ2JjBUsx9YEjewrJSC7ADmwznJoR5OrMTXVnmGABzMtcIHs-3a3ZgPuyg5NiY)


**Edit Profile**

If the user selects the "Edit" button, they will be directed to this page.

Apart from their email and dietary requirements, the user can edit the rest of their profile details on this page.

The Username, Name and Allergy fields are EditText (Plain Text), while the Date Of Birth field is EditText (Date). The gender selection is a spinner, the Email field is a Text View, the Profile Picture is an ImageView, "Confirm" is a Button, and the back arrow is an ImageView.

![](https://lh4.googleusercontent.com/mgP35AhuH1WuG-XKvyVaZnaTa4ZxaWogS3Pm_sTdtfapZh49RKs0axMuoEzUwAU7XDhXpDgKUAECwSssf7YmNqadk2qX835j195q6g2m)


**View All Chats**

This page shows the chats of users that has accepted their request or users that they have accepted. The user can then select a user to begin/continue chatting with.

If the user selects the "Chat" button, they will be directed to this page. If the user confirms a user request, the two users will be able to use this function.

This page is created using RecyclerView where the Profile Picture is an ImageView while the Username, Last Message and Time are TextViews.

![](https://lh4.googleusercontent.com/42WGktc0yuNaCURH9VM7mdCTe2CthQxHTGZhmnQCsM0nVh8YwoJA_FEPUDn9NzKT8MQ4goERzAAEZDdtTpXNVLf2CBPabb3mL1__ikIG)


**Chat**

Users will be directed to this page when the user selects a chat.

This page will show messages of the 2 users via Firebase. Users will enter a message in the EditText box and clicked send, the Message recyclerView will update Real-Time. Their message will pop up in a pink message box while their partner's messages will pop up in a grey message box. Their messages will appear with a Time that follows the time the message is sent.

The user Name is a Textview, backArrow, userProfile, sendButton are ImageButtons, the message is a RecyclerView with ImageView to surround the message and the message and time are TextViews.

![](https://lh5.googleusercontent.com/t-vdCkwYHxnuNIkLizgQP7UZNqy3EV8kkWz9O3k1pHq4GSAwPlZfGHYdH6T2gWq0h4ooKlENbwfKlirpz42eq-QFNKvCkRd0SE0xSNR8)



Users will be directed

**Chat Profile Page**

If the user selects the Profile Button on the top right hand corner of the chat page, they will be directed to this page.

This page shows the profile details of the other user that the user is chatting with. There is also a Suggested Restaurants list feature, where both users can add to it by pressing the Add Button. A random restaurant will be shown to the user, and the user can choose to add it to the list, or reject it.

After adding a restaurant to the Suggested Restaurant list, the user can view the restaurant address by pressing the restaurant name in the list. To get directions to the restaurant, the user can press the Get Directions Button and Google Maps will open with the location.

The Username, Name, Age, Gender, Date of Birth, Allergies and Diet are all TextViews while the Profile Picture is an ImageView. The Suggested Restaurant list is a RecyclerView with Textviews. The back arrow is an ImageButton where Add is a Button w/ Background.

![](https://lh3.googleusercontent.com/MYuKa9pXoAlqDXBb5cH54lwFOw939mUQPYQeS5sv-fJ-OIdd7m_vcpiH8oHPw6GCwrjxpMGi-33MZCyAS1VUFdYzis75aujTy319cf-l)



