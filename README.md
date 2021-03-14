## Project Brief:
Develop a fixed income deals application which will enable users of the app to buy the fixed income deals

## Business Idea:
Today, interest rates provided by banks and governments across the world have reduced. Thus there is huge demand for higher yielding fixed income products/deals. Think about it, wouldn’t you want a safe asset paying 12-15% interest per year? The app intends to meet this demand by being a marketplace for companies/firms to offer these fixed income deals and customers/users to purchase these

## Technical Overview:
The first version of the app would have 3 screens:

### OverView Screen:
This screen will display all the deals available to the users. This screen will make use of the RecyclerView inorder to conserve and reuse resources. The data is fetched from a deal API using Retrofit and gson and stored in the local database. Glide is used to fetch the image. Standard MVVM Architecture will be followed

### Detail Screen:
This screen will display the details of each individual deal. Details will include the Interest rate of the deal, term, total number of tranches, remaining tranches and rating. Details are fetched from the local database.

### Payment screen:
Integrate with a payment gateway in order to make payment. I have used Razorpay payment gateway for this purpose

### Since Kotlin is the flavour of Android, I also decided to write the backend deals API using Kotlin and spring boot. Available at: https://github.com/jayeshnachnani/deals-backend

## Setup for https://github.com/jayeshnachnani/deals-backend API

Instructions for setting up https://github.com/jayeshnachnani/deals-backend are in the deals-backend Readme but briefly:

deals-backend is a Spring boot application, so in order to run locally, compile and then Run DealsApplicationKt

After you see below lines
Tomcat started on port(s): 5000 (http) with context path ''
INFO 15734 --- [           main] com.finance.deals.DealsApplicationKt     : Started DealsApplicationKt in 15.167 seconds (JVM running for 16.548)

in the console, you should be able to access http://localhost:5000/deals and see the deals in JSON format\

Now you would need to add your IP at a couple of places in the Android project.
In order to find your IP, follow the steps in any of the below links
https://www.avast.com/c-how-to-find-ip-address?v=b OR
https://lifehacker.com/how-to-find-your-local-and-external-ip-address-5833108#:~:text=Windows,is%20your%20local%20IP%20address.

Now change 192.168.1.4 to your local IP at the below 2 locations

strings.xml line 30
DealsApiService line 26



