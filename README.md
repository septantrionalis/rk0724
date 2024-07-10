# rk0724

## Programming Demonstration

This is a spring boot application that accomplishes the requested task. The 
main entry point is "Demo5Application" which surfaces a RESTful API to handle 
the checkout functionality. A sample curl statement would look like : <BR>
<BR>
```
curl --location 'localhost:8080/checkout?tool-code=JAKR&rental-day-count=4&checkout-date=07022022&discount-percent=50'
```
<BR>
The request parameters take in required information as detailed in the document.<BR>
<BR>
I've leveraged a simple SpringBoot application architecture to handle the code. <BR>
- Demo5Application.java is the RestController and the start of the app.<BR>
- ToolService.java is the service layer that handles the Java business logic. This will contain the logic needed to calculate dates, discounts, etc.<BR>
- ToolRepository.java will construct the data and return the data as needed by the user. A real spring boot app would go to a database, but for the purposes of this exercice, the data is hard coded.<BR>
- RentalAgreement.java is the end result. <BR>
- Test cases are in the test folder. Currently, the main unit test is "Demo5ApplicationTests".
<BR><BR>
When hitting the endpoint, I output the data in two places. Once for the console and once for the request response.<BR>
<BR>

## Design decision

Charge days is "Count of chargeable days, from day after checkout through and including due
date". This excludes the day the tool is checked out, but includes the day the item is checked in. The way I do my
calculation is the checkout day is a free day. All days afterward and including the due date can be charged.
For example:<BR>
Checkout date: 01/01/2024<BR>
Rental day count: 4<BB>
Charge days will be calculated on 01/02/2024, 01/03/2024, 01/04/2024, and 01/05/2024<BR>

## Output

Sample Console output:
```
Tool Code: JAKR
Tool Type: Jackhammer
Tool Brand: Ridgid
Rental Days: 4
Checkout Date: 07/02/22
Due Date: 07/05/22
Daily Rental Charge: $2.99
Charge Days: 1
Prediscount Charge: $2.99
Discount Percent: 50%
Discount Amount: $1.50
Final Charge: $1.49
```

Sample REST Response:<BR>
```
{
    "tool": {
        "toolCode": "JAKR",
        "toolType": "JACKHAMMER",
        "brand": "Ridgid"
    },
    "rentalDays": 4,
    "checkoutDate": "07/02/2022",
    "dueDate": "07/05/2022",
    "dailyRentalCharge": 2.99,
    "chargeDays": 1,
    "prediscountCharge": 2.99,
    "discountPercent": 50,
    "discountAmount": 1.50,
    "finalCharge": 1.49
}
```

