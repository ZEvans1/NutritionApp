# _Meal Maker_
## _(under construction)_

#### _A mobile app intended to let users create and share meals using the nutritionix API. Android week 2 @ Epicodus, Mar. 23, 2018._

#### By _**Zach Evans**_

## Idea/Explanation

_This app is intended for users who would like to obtain nutritional information about meals they are going to make or have eaten._


## Future work
* An additional API call is needed to get food details. A GET request is made and then a POST request needs to be made using a property from the GET request. I have chosen to get details this way to ensure users are submitting the right queries to the API.

* Implement Firebase so users can store/save foods in their account.


## Setup/Installation Requirements

* Clone repository to your machine from: https://github.com/ZEvans1/NutritionApp
* _Open cloned repository in Android Studio_
* _Run app on an Android emulator (designed for Nexus 6)_
* _Create a gradle.properties file in the app directory and add the following lines of code:_

    _xAppId = "APP ID HERE"_
    
    _xAppKey = "API KEY HERE"_
    
* _You will need a nutritionix developer account to obtain the app ID and API key. After you obtain the ID and key, paste them to their appropriate locations in the above code._

App instructions:
* _Enter text into the text field and click the search button._
* _Browse results within a list (if results are returned) and select an item from the list to go to a detail view_


## Known Bugs / Issues

* _Food details are not shown (second API call is in progress on another branch at the moment.)_
* _Image sizes / quality for the detail view are not correctly sized._
* _Default image is not appearing for foods that do not have an image returned from the API._

## Support and contact details

_Direct questions or comments to: zte.zachary@gmail.com_

## Technologies / Languages Used

* _Java_
* _Android_
* _Android Studio_


### License
Copyright (C) 2018, Zach Evans
