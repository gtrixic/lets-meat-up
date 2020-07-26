*Welcome to Let’s Meat Up!*

**Team members:**

1.  Benjamin Lai Zhe Wei (S10193638)
    
2.  Cammy Lim Zhi Yu (S10196467)
    
3.  Cher Gek Teng (S10196753)
    
4.  Tan Jia Wen (S10196330)
    

  

**App Description:**

Make new friends or potential partners with Let's Meat Up! and get recommendations on restaurants to have your first meal together.

An assignment for partial fulfilment of the coursework of Mobile Applications Development AY2020/21 in Ngee Ann Polytechnic.

Let's Meat Up! is a social/food app to help you find food buddies or potential partners. 
Love food? Let's Meat Up! helps you match with others that

What Let's Meat Up Has
- Personality Questions Test
- Chat
- Suggested Restaurants

The Personality Questions Test is to help you find a partner with similar personalities.
  
  

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

If the user declares their account to be a user account, they will be sent to this Create User Page 1. Users are to enter their Full Name, Username, Password, Re-enter Password, Email, Gender, DOB.

After confirming their user information, users will be directed to the Create User Page 2 using Intent. If the user goes to the next page without entering all the necessary information, a Toast will appear to alert them.

“Full Name” and “Username” uses EditText (Plain Text), “Password” and “Re-enter Password” uses EditText (Password), “Email” uses EditText (Email), “Gender” uses spinner, “DOB” uses EditText (Date). The “Previous Page Button” and the “Next Page Button” are ImageButtons.

All the information entered is recorded into the UserAccounts database using Firebase.

![](https://lh6.googleusercontent.com/lohr8Cvh6WkXekMqEed_UIBfmaPyKfBTKpRNEZa3Lmd2ermr49q7sLLwAOBSyRsfDTlLlgdJzQ39u3rAsusVygei1syfEuAW2iICdAaR)


**Sign-Up (Create User Page 2)**

The user will then be asked to add a profile picture for their account. After adding a profile picture, they can press the Next Page Button to go to Create User Page 3.

The profile picture will be added to the UserAccount database using Firebase and SQLite.The “Previous Page Button” and the “Next Page Button” are ImageButtons.

![](https://lh6.googleusercontent.com/3boCj5cSXS6ma3zR0e5BROdROIbr4-p_cnfamH29Z7XBV7t-DrwWyn2zdjkxMjvML0ynJ1cHLzkncSxO9KViz8WDbs4dH4Jy9O70QYPU)


**Sign-Up (Create User Page 3)**

The user will then be asked to state their allergies/preferences and their diet (e.g. Halal, Vegetarian, etc.) After stating any information needed to be stated, they can confirm their profile and be redirected to the Personality Questions Page.

The “Previous Page Button” is an ImageButton, “Allergies/Preferences (If Any)” is a TextView, the box to enter their allergies/preferences and the “Diet” input are EditText (Plain Text), “Confirm Profile” is a Button.

![](https://lh4.googleusercontent.com/JcduxD01c7j-4e1jymRHJ4LdBJe_FD1p27oEH3QNwyXYdIBw_4yQn7C5OCYG06jKA3o0YIy_yTVB_sd0cRYvOdRSpOgTEGLZLWLu65kd)


**Sign-Up (Personality Questions RecyclerView page)**

Users will be asked to answer some simple questions on their personality type. This will be used to match users with more like-minded people. After picking an answer for each question, the user can proceed to submit the simple questionnaire and be redirected to the main page of the app.  

The options they choose will be translated into a code(matchID) which will be tagged to each user and it will be used to compare with other users’ code tags(matchID). The more similar the code tags, the higher the chance of being matched.

The page is created using RecyclerView where each question has a TextView of the questions and the selected answer, and 2 answer Buttons to choose from. “Submit” is a Button.

An error message toast will be shown if there are questions not answered.

![](https://lh3.googleusercontent.com/cxoIkMyH9Ck7dZafVZZiCE4njqht4MXS3fdFXu4m0aORo5H3-KrgEVPZtCHsYxjWEj4CxG2mkaoBTsGKCfLmXJZidhYk-Ko0OWLhw_kF)
![](https://lh6.googleusercontent.com/zdttSbF3CoSx9-Ywu9fr71JO6kSz_Zn8UdoOR8pn1aatbVXHUipszVDzuGNKEG15BsSXt9q-ThkSKdXswNv53LLI2H6zkRiEe6IZmfsP)
![](https://lh4.googleusercontent.com/PzLrjGmaVbFrN8xrM_qChsPjzJY3mwp7CxkfW10iD-Mdq4uJVMl4sQyIxK4awGWehLTIBA57QMqSLwmntL3zQxsYhrkf2Q7mF8LjXNx2)
![](https://lh5.googleusercontent.com/dwXPBrGWUQYyv27Wxnzkf5CVGXHD27f62IuiD8HM9gJMEN72Tv4CpxBOF3iIxD4kM6dXLAFRA8zB1S_cPzfi7l1jVXrQ9vHTg2uMfBLn)
![](https://lh5.googleusercontent.com/yM8o6ANfHchQCduoFJTb-QpiZtxFD7t0n83N92uyQHJqjV9GFH3q0jyEUDYqOte_ZuktMSse3-p9__fZe3wEV6M4G4TIJ70BXgyQaDrL)
  

**Login Page**

If the user clicks the “I HAVE AN ACCOUNT” Button, they will be directed to this page. They are requested to enter either their username or email, and their password corresponding to the account.

“Username/Email” is an EditText while “Password” uses EditText(Password). The login arrow is an ImageButton.

All information is checked against the database that was created using Firebase.

If the email or password entered is incorrect, a toast error message is shown.

![](https://lh3.googleusercontent.com/UQWtpp-IxtEpFSDNFdAeRg15A4J4e8fO5sS06XJIj2eiERI0nJtiJp8p9SIlzu2upMEBXnsirWYSE5TqdiuNmxLvki7zQnZiTOJ4fuOR)


**Forget Password (Page 1)**

The user will be asked to enter either their username or email. If the username or email is valid in the database, they will be directed to the next page using Intent.

The small “Let’s Meat Up” icon is an ImageView, “Username/Email” is an EditText (Plain Text), the “Next Page Button” and "Back Arrow Button" is an ImageButton.

![](https://lh5.googleusercontent.com/VYy32A42Wr6WWLRqJULJmKxtHiwXLd7rR2yslHJOgyBd4USNhuXu0XQVdAHnU0Tw0QqOoBIngqrvO4dzMftqj474V1pmDcWXA0eryvOC)


**Forget Password (Page 2)**

The user will then be asked to enter and re-enter their new password. If the 2 passwords entered are identical, the change will be updated in the database and they will be directed to the next page using Intent. The account must have been logged in to at least once for the change to happen.

“Enter Password” and “Re-enter Password” are EditText (Password”, the “Let’s Meat Up” icon is an ImageView, and the “Next Page Button” is an ImageButton.

![](https://lh6.googleusercontent.com/7ffxRbTzFn0XNmPbFb0asKKcCc8-NWusSQzGjSKy3tQTaLt3DJxuybHL0SaRK5QG5zM_9GcKoUPyAThpr2sQBkeDHdv225YzBfzKiUhp)


**Forget Password (Page 3)**

This page will show up in the case of a successful change of password in the database. Users will then be allowed to redirect themselves to the Login Page where they are able to login to their account with their new password using Intent.

“Let’s Meat Up” icon is an ImageView, “Change of password successful!” is a TextView, and the “Next Page Button” is an ImageButton.

![](https://lh5.googleusercontent.com/5bAO6Sap9X-YIKIfJ7mYLvE6s62y15QHpS9GMVlc4t47OH2G2Qv6soWoQazy3oyygUwGU2-xenirF_XIdw8tnwm3A-ryAFiSPzTFtgfW)


**Home (Find new matches/Accept match requests/Profile/Chat/Help Page)**

After the user holding a user account successfully logs in to their account, they will be directed to this page where they can either find new people to match with or accept incoming match requests.

Depending on which option they select, the user will be sent to either the Random User page or the View User Requests page through the use of Intent.

“Pick User”, “Accept User”, “Profile Button” at the top left corner, “Help Button” at the top right corner and "Chat Button" at the bottom right corner are all ImageButtons.

![](https://lh6.googleusercontent.com/LwXQKIhFdwNgg4fZ3TZA8RPX6tlMeedHm5IxqB0QaZVjoDKGsDyS40oXOGWzOMPvx2je0INn1MLZzRTUCnsC1I-on5c8h3ywGhT9oUvh)


**Pick User (Find New Matches)**

If the user holding a user account selects the &quot;Pick User&quot; Button, they will be directed to this page.

Upon pressing &quot;Start&quot;, a match for the user will be generated based on the users&#39; code tags. When a match is found, the profile picture, name, age, gender and allergies of the matched user will be displayed. The user is then given the option to send a request or ignore the matched user.

If the user selects &quot;Request&quot;, a pop-up will appear to alert the user that the request has been sent. The pop-up also allows the user to choose if they want to return to the home screen or continue searching for more matches.

“Start”, “Request”, “Ignore”, “Profile Button” at the top left corner and “Help Button” at the top right corner are all ImageButtons.

![](https://lh3.googleusercontent.com/rPPBxy-AH700SC_cOiDB9llhpBDxTqg1y9OD0ciGjI5r7u6E3x1kO_ezO0gpbvMsyCHkpGQ4Nj0dxzm3wk1ZcRHsKKnwRmp_A815pbw-)
![](https://lh4.googleusercontent.com/5sxWMNeEqcRpYIwwOd2N9lCuVL3TLfSXaA3whNo17_KZSb6ZmAkXszqkDOLBsbcmv8omUDD16vzhqKjasiEMXUTBCtCIVVqu-DsJ48EQ)
![](https://lh6.googleusercontent.com/3q_L64X5SJsBkTqK5X8IGwAPcec9-0PANTY3WobVoP7dbk-4v76WJchcr-tiKGdY_WGMiKtFy9x2KBtV_u_n0mUwT28uhGlZa5gXe1PS)
![](https://lh6.googleusercontent.com/MSpMScoMjSEk8vZVQKj8NxY7ALHU_TPfb3snRfmHA7Bhj8s4s4X9M2NVbzP80RNV4Vt4cghuqNfcuM8LcbYFxpThFFq3vjQ6D9o9viin)
![](https://lh3.googleusercontent.com/-DEOqLaBv0VNa2L4p2XNt5AlIBJLWTRZM53IxMpQTv2OD96HZ8AAJ4SkxskjysB4NCMQAkyOa1PRuHnVINSMf-qyNi-wHnMFOaecfyR6)


**View Profile**

If the user selects the &quot;Profile&quot; button, they will be directed to this page.

On this page, the user can view their profile, where their Profile Picture, Username, Name, Age, Gender, Date of Birth, Allergies are shown.

The user can also edit their profile or log out of their account here. By pressing the buttons, they will be taken to the respective pages through the use of Intent.

Both “Edit” and “Sign Out” are Buttons.

![](https://lh5.googleusercontent.com/nMwE-OO34BtWMk7OQLsnWFasYcBQoeAaD3pXWqjjdPgZ2JjBUsx9YEjewrJSC7ADmwznJoR5OrMTXVnmGABzMtcIHs-3a3ZgPuyg5NiY)


**Edit Profile**

If the user selects the "Edit" button, they will be directed to this page.

On this page, the user can edit all their profile details except for email dietary requirements. 

The Username, Name and Allergy fields are EditText(Plain Text) while the Date Of Birth field is EditText(Date). The gender selection is a spinner and the Email field is a Text View. The Profile Picture is an ImageView while "Confirm" and back arrow are Buttons.

![](https://lh4.googleusercontent.com/mgP35AhuH1WuG-XKvyVaZnaTa4ZxaWogS3Pm_sTdtfapZh49RKs0axMuoEzUwAU7XDhXpDgKUAECwSssf7YmNqadk2qX835j195q6g2m)


**Sign Out**

If the user selects the "Sign Out" button, they will be directed to the Login Page.


**Chat**

If the user selects the "Chat" button, they will be directed to this page.

![](https://lh4.googleusercontent.com/42WGktc0yuNaCURH9VM7mdCTe2CthQxHTGZhmnQCsM0nVh8YwoJA_FEPUDn9NzKT8MQ4goERzAAEZDdtTpXNVLf2CBPabb3mL1__ikIG)
![](https://lh3.googleusercontent.com/MYuKa9pXoAlqDXBb5cH54lwFOw939mUQPYQeS5sv-fJ-OIdd7m_vcpiH8oHPw6GCwrjxpMGi-33MZCyAS1VUFdYzis75aujTy319cf-l)
![](https://lh5.googleusercontent.com/t-vdCkwYHxnuNIkLizgQP7UZNqy3EV8kkWz9O3k1pHq4GSAwPlZfGHYdH6T2gWq0h4ooKlENbwfKlirpz42eq-QFNKvCkRd0SE0xSNR8)


**View Reservations (User Account)**

If the user holds a user account and chooses to view reservations, they will be directed to this page.

Reservations will be displayed as a numbered list. Each reservation will show the matched user&#39;s username, restaurant name, meeting time and date.


